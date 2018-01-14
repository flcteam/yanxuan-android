package com.flc.first.ui.widget.dropdowncategory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flc.first.R;
import com.flc.framework.processor.ScreenProcessor;
import com.flc.framework.processor.SizeProcessor;
import com.flc.framework.utils.SingletonFactory;

/**
 * Created by channagihong on 09/01/2018.
 */

public class DropDownCategory extends FrameLayout {

    private final int DP_SIZE_ARROW = 35;
    private final int DP_PADDING_ARROW = 5;
    private final int DP_HEIGHT = 30;
    private final int SP_TITLE_SIZE = 15;
    private final int DP_SIZE_BASE_BAR_HEIGHT = 40;

    private int arrowSize;
    private int arrowPadding;
    private int screenWidth;
    private int baseBarHeight;

    private TextView titleTextView;
    private ImageView arrow;
    private HorizontalScrollCategory scrollCategory;
    private FrameGridView dropCategory;

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
        onCreateScrollCategory();
        onCreateArrow();
        onCreateTitle();
        onCreateDropCategory();
    }

    private void initVars() {
        SizeProcessor sizeProcessor = SingletonFactory.getSingleton(SizeProcessor.class);
        arrowSize = sizeProcessor.dp2px(DP_SIZE_ARROW);
        arrowPadding = sizeProcessor.dp2px(DP_PADDING_ARROW);
        baseBarHeight = sizeProcessor.dp2px(DP_SIZE_BASE_BAR_HEIGHT);

        screenWidth = SingletonFactory.getSingleton(ScreenProcessor.class).getPortraitScreenWidth();
    }

    private void onCreateScrollCategory() {
        scrollCategory = new HorizontalScrollCategory(getContext());
        scrollCategory.construct(dataProvider);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth - arrowSize, baseBarHeight);
        addView(scrollCategory, params);
    }

    private void onCreateArrow() {
        arrow = new ImageView(getContext());
        arrow.setImageResource(R.mipmap.grey_arrow_down);
        arrow.setPadding(arrowPadding, arrowPadding, arrowPadding, arrowPadding);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(arrowSize, arrowSize);
        params.leftMargin = screenWidth - arrowSize;
        params.topMargin = (baseBarHeight - arrowSize) / 2;
        addView(arrow, params);
    }

    private void onCreateTitle() {
        titleTextView = new TextView(getContext());
        titleTextView.setGravity(Gravity.CENTER_VERTICAL);
        titleTextView.setVisibility(View.GONE);
        titleTextView.setText(null != dataProvider ? dataProvider.getTitle() : null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, baseBarHeight);
        addView(titleTextView, params);
    }

    private void onCreateDropCategory() {
        dropCategory = new FrameGridView(getContext());
        dropCategory.construct(dataProvider);
        dropCategory.setTranslationY(-dropCategory.getPreMeasuredSize()[1]);
        dropCategory.setVisibility(View.GONE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = baseBarHeight;
        addView(dropCategory, params);
    }
}
