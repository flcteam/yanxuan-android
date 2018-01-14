package com.flc.first.ui.widget.dropdowncategory;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flc.first.App;
import com.flc.first.R;
import com.flc.framework.callback.Action;
import com.flc.framework.callback.OnItemClickListener;
import com.flc.framework.processor.ScreenProcessor;
import com.flc.framework.processor.SizeProcessor;
import com.flc.framework.utils.SingletonFactory;

import timber.log.Timber;

/**
 * Created by channagihong on 14/01/2018.
 */

public class HorizontalCategory extends FrameLayout {

    private final long DURATION_INDICATOR_ANIMATION = 250;
    private final int SP_SIZE_ITEM_TEXT = 14;
    private final int DP_WIDTH_INDICATOR = 40;
    private final int DP_HEIGHT_INDICATOR = 2;

    private int colorText, colorHighlightText;
    private int itemHeight, itemWidth;
    private int horizontalPaddingSize, verticalPaddingSize;
    private int textHorizontalPaddingSize, textVerticalPaddingSize;
    private int screenWidth;
    private int measuredWidth, measuredHeight;
    private int indicatorWidth, indicatorHeight;
    private IDropDownCategoryProvider dataProvider;

    private View indicator;
    private int highlightIndex = 0;

    private OnItemClickListener<String> onItemClickListener;
    private Action<Integer> onHighlightEdgeCallback;
    private ValueAnimator currentIndicatorAnimator;

    public HorizontalCategory(Context context) {
        super(context);
    }

    public HorizontalCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HorizontalCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //============================ must called before init ================================================
    public void construct(IDropDownCategoryProvider provider) {
        this.dataProvider = provider;
        init();
    }

    //============================ init ================================================
    private void init() {
        initVars();
        onCreateView();
        onCreateIndicator();
    }

    private void initVars() {
        colorText = ContextCompat.getColor(App.getInstance(), R.color.c333333);
        colorHighlightText = ContextCompat.getColor(App.getInstance(), R.color.highlight_red);
        horizontalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(12);
        verticalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(0);
        textHorizontalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(4);
        textVerticalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(3);
        indicatorWidth = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(DP_WIDTH_INDICATOR);
        indicatorHeight = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(DP_HEIGHT_INDICATOR);

        Paint paint = new Paint();
        paint.setTextSize(SingletonFactory.getSingleton(SizeProcessor.class).dp2px(SP_SIZE_ITEM_TEXT));
        Rect rect = new Rect();
        paint.getTextBounds("我", 0, 1, rect);
        itemHeight = rect.height() + 2 * textHorizontalPaddingSize;

        screenWidth = SingletonFactory.getSingleton(ScreenProcessor.class).getPortraitScreenWidth();
    }

    private void onCreateView() {
        int size = getCount();
        int left = 0;
        for (int i = 0; i < size; i++) {
            left = addItemGridView(left, dataProvider.getDropDownCategoryData().get(i), i);
        }
    }

    private int addItemGridView(int left, String content, int position) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(SP_SIZE_ITEM_TEXT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(colorText);
        textView.setText(content);
        textView.setPadding(textHorizontalPaddingSize, textVerticalPaddingSize, textHorizontalPaddingSize, textVerticalPaddingSize);
        textView.setOnClickListener(v -> callClickListener(position, content));

        int[] measuredSize = SingletonFactory.getSingleton(SizeProcessor.class).measureView(textView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(measuredSize[0], measuredSize[1]);
        params.leftMargin = left + horizontalPaddingSize;
        params.topMargin = verticalPaddingSize;
        addView(textView, params);

        left += horizontalPaddingSize * 2 + measuredSize[0];

        if (position == getCount() - 1) {
            measuredWidth = left;
            measuredHeight = measuredSize[1] + verticalPaddingSize * 2;
        }
        return left;
    }

    private void onCreateIndicator() {
        View leftView = getChildAt(0);
        int leftViewWidth = leftView.getMeasuredWidth();
        int indicatorRelativeLeft = (leftViewWidth - indicatorWidth) / 2;
        indicator = new View(getContext());

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(indicatorWidth, indicatorHeight);
        params.topMargin = measuredHeight - indicatorHeight;
        params.leftMargin = horizontalPaddingSize + indicatorRelativeLeft;
        addView(indicator, params);
        indicator.setBackgroundColor(colorHighlightText);
    }

    //============================ override from View/ViewGroup ================================================
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
        getLayoutParams().width = measuredWidth;
        getLayoutParams().height = measuredHeight;

    }

    //============================ private business ================================================
    private void callClickListener(int position, String content) {
        setHighlight(position);
        if (null != onItemClickListener) onItemClickListener.onItemClick(position, content);
    }

    private void callHighlightEdge(int offset) {
        if (null != onHighlightEdgeCallback) onHighlightEdgeCallback.action(offset);
    }

    //============================ public business ================================================
    public void setHighlight(int index) {
        this.highlightIndex = index;
        int count = getCount();
        View highlight = null;
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(i == index ? colorHighlightText : colorText);
            }
            if (i == index) highlight = view;
        }

        if (null == highlight) return;
        if (null == getParent()) return;

        int left = ((LayoutParams) highlight.getLayoutParams()).leftMargin;
        int width = highlight.getMeasuredWidth();
        int indicatorWidth = indicator.getWidth();
        int indicatorRelativeLeft = (width - indicatorWidth) / 2;

        int fromIndicatorLeft = ((LayoutParams) indicator.getLayoutParams()).leftMargin;
        int toIndicatorLeft = left + indicatorRelativeLeft;
        animateIndicator(fromIndicatorLeft, toIndicatorLeft);

        int parentWidth = ((View) getParent()).getWidth();
        int[] location = new int[2];
        highlight.getLocationOnScreen(location);
        Timber.d("location(%d, %d), categoryWidth: %d", location[0], location[1], parentWidth);
        if (location[0] > 0 && location[0] + width < parentWidth) return;

        if (location[0] < 0) {
            callHighlightEdge(location[0]);
        } else {
            callHighlightEdge(location[0] - parentWidth + width);
        }
    }

    public void animateIndicator(int from, int to) {
        if (null != currentIndicatorAnimator && currentIndicatorAnimator.isRunning()) {
            currentIndicatorAnimator.cancel();
        }
        currentIndicatorAnimator = ValueAnimator.ofInt(from, to);
        currentIndicatorAnimator.setDuration(DURATION_INDICATOR_ANIMATION);
        currentIndicatorAnimator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            LayoutParams params = (LayoutParams) indicator.getLayoutParams();
            params.leftMargin = value;
            updateViewLayout(indicator, params);
        });
        currentIndicatorAnimator.start();
    }

    //============================ getters and setters ================================================
    private int getCount() {
        return null == dataProvider || null == dataProvider.getDropDownCategoryData() ? 0 : dataProvider.getDropDownCategoryData().size();
    }

    public HorizontalCategory setOnItemClickListener(OnItemClickListener<String> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public HorizontalCategory setOnHighlightEdgeCallback(Action<Integer> onHighlightEdgeCallback) {
        this.onHighlightEdgeCallback = onHighlightEdgeCallback;
        return this;
    }
}
