package com.flc.first.ui.widget.dropdowncategory;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flc.first.R;
import com.flc.framework.processor.DrawableColorProcessor;
import com.flc.framework.processor.ScreenProcessor;
import com.flc.framework.processor.SizeProcessor;
import com.flc.framework.utils.SingletonFactory;

/**
 * Created by channagihong on 09/01/2018.
 */

public class DropDownCategory extends FrameLayout {

    private final long DURATION_ANIMATION_TOGGLE = 250;
    private final int DP_SIZE_ARROW = 23;
    private final int DP_HEIGHT = 30;
    private final int SP_TITLE_SIZE = 15;
    private final int DP_SIZE_BASE_BAR_HEIGHT = 40;
    private final int DP_WIDTH_GRADIENT_SHELTER = 50;
    private final int COLOR_RES_BACKGROUND = R.color.white;
    private final int COLOR_RES_TITLE_TEXT = R.color.c333333;

    private int arrowSize;
    private int screenWidth;
    private int baseBarHeight;
    private int gradientShelterWidth;

    private FrameLayout categoryBar;
    private TextView titleTextView;
    private ImageView arrow;
    private HorizontalScrollCategory scrollCategory;

    private FrameGridView dropCategory;

    private ValueAnimator dropdownAnimator;
    private IDropDownCategoryProvider dataProvider;

    public DropDownCategory(@NonNull Context context) {
        super(context);
    }

    public DropDownCategory(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DropDownCategory(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DropDownCategory(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //============================ must called before init ================================================
    public void construct(IDropDownCategoryProvider dataProvider) {
        this.dataProvider = dataProvider;
        init();
    }

    //============================ init ================================================
    private void init() {
        initVars();
        onCreateDropCategory();
        onCreateBaseBar();
        initListeners();
    }

    private void initVars() {
        SizeProcessor sizeProcessor = SingletonFactory.getSingleton(SizeProcessor.class);
        arrowSize = sizeProcessor.dp2px(DP_SIZE_ARROW);
        baseBarHeight = sizeProcessor.dp2px(DP_SIZE_BASE_BAR_HEIGHT);
        gradientShelterWidth = sizeProcessor.dp2px(DP_WIDTH_GRADIENT_SHELTER);

        screenWidth = SingletonFactory.getSingleton(ScreenProcessor.class).getPortraitScreenWidth();
    }

    private void onCreateDropCategory() {
        dropCategory = new FrameGridView(getContext());
        dropCategory.construct(dataProvider);
        dropCategory.setTranslationY(-dropCategory.getPreMeasuredSize()[1]);
        dropCategory.setVisibility(View.GONE);
        dropCategory.setBackgroundResource(COLOR_RES_BACKGROUND);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = baseBarHeight;
        addView(dropCategory, params);
    }

    private void onCreateBaseBar() {
        categoryBar = new FrameLayout(getContext());
        categoryBar.setBackgroundResource(COLOR_RES_BACKGROUND);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, baseBarHeight);
        addView(categoryBar, params);

        onCreateScrollCategory();
        onCreateArrow();
        onCreateGradientShelter();
        onCreateTitle();
    }

    private void onCreateScrollCategory() {
        scrollCategory = new HorizontalScrollCategory(getContext());
        scrollCategory.construct(dataProvider);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth - arrowSize - gradientShelterWidth / 2, baseBarHeight);
        categoryBar.addView(scrollCategory, params);
    }

    private void onCreateGradientShelter() {
        View shelter = new View(getContext());
        shelter.setBackground(SingletonFactory.getSingleton(DrawableColorProcessor.class).horizontalGradientDrawable(R.color.transparent, R.color.white));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(gradientShelterWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin = screenWidth - baseBarHeight - gradientShelterWidth;
        categoryBar.addView(shelter, params);
    }

    private void onCreateArrow() {
        arrow = new ImageView(getContext());
        arrow.setImageResource(R.mipmap.grey_arrow_down);
        int padding = (baseBarHeight - arrowSize) / 2;
        arrow.setPadding(padding, padding, padding, padding);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(baseBarHeight, baseBarHeight);
        params.leftMargin = screenWidth - baseBarHeight;
        categoryBar.addView(arrow, params);
    }

    private void onCreateTitle() {
        titleTextView = new TextView(getContext());
        titleTextView.setGravity(Gravity.CENTER_VERTICAL);
        titleTextView.setPadding(30, 0, 0, 0);
        titleTextView.setTextColor(ContextCompat.getColor(getContext(), COLOR_RES_TITLE_TEXT));
        titleTextView.setVisibility(View.GONE);
        titleTextView.setText(null != dataProvider ? dataProvider.getTitle() : null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth - arrowSize, baseBarHeight);
        categoryBar.addView(titleTextView, params);
    }

    private void initListeners() {
        arrow.setOnClickListener(v -> toggle());
    }

    private void toggle() {
        toggle(scrollCategory.getVisibility() == View.VISIBLE);
    }

    private void toggle(boolean dropdown) {
        scrollCategory.setVisibility(dropdown ? View.GONE : View.VISIBLE);
        titleTextView.setVisibility(dropdown ? View.VISIBLE : View.GONE);
        dropCategory.setVisibility(View.VISIBLE);

        if (null != dropdownAnimator && dropdownAnimator.isRunning()) {
            dropdownAnimator.cancel();
        }

        dropdownAnimator = ValueAnimator.ofInt(0, 100);
        dropdownAnimator.setDuration(DURATION_ANIMATION_TOGGLE);
        dropdownAnimator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();

            float progress = value / 100f;

            /*
                ARROW
                dropdown:
                    rotation from 0 to 180
                    progress from 0 to 100
                !dropdown:
                    rotation from -180 to 0
                    progress from 100 to 0
             */
            float rotationProgress = dropdown ? progress : 1 - progress;
            int baseRotation = dropdown ? 180 : -180;
            int rotation = (int) (baseRotation * rotationProgress);
            arrow.setRotation(rotation);

            /*
                DROPCATEGORY
                dropdown:
                    translationY from -height to 0
                    progress from 100 to 0
                !dropdown:
                    translationY from 0 to -height
                    progress from 0 to 100
             */
            float translationProgress = dropdown ? 1 - progress : progress;
            int translationY = (int) (-dropCategory.getPreMeasuredSize()[1] * translationProgress);
            dropCategory.setTranslationY(translationY);
        });
        dropdownAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!dropdown) dropCategory.setVisibility(View.GONE);
            }
        });
        dropdownAnimator.start();
    }
}
