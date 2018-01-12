package com.flc.first.ui.widget.dropdowncategory.framegridview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flc.first.App;
import com.flc.first.R;
import com.flc.first.ui.widget.dropdowncategory.IDropDownCategoryProvider;
import com.flc.framework.processor.DrawableColorProcessor;
import com.flc.framework.processor.ScreenProcessor;
import com.flc.framework.processor.SizeProcessor;
import com.flc.framework.utils.SingletonFactory;

import timber.log.Timber;

/**
 * Created by channagihong on 09/01/2018.
 */

public class FrameGridView extends FrameLayout {

    private final int COLUMN_COUNT = 4;
    private final int SP_SIZE_ITEM_TEXT = 14;
    private final int COLOR_TEXT = ContextCompat.getColor(App.getInstance(), R.color.black);
    private final int COLOR_HIGHLIGHT_TEXT = ContextCompat.getColor(App.getInstance(), R.color.highlight_red);

    private int colorText, colorHighlightText;
    private int itemHeight, itemWidth;
    private int horizontalPaddingSize, verticalPaddingSize;
    private int textHorizontalPaddingSize, textVerticalPaddingSize;
    private int ROW_COUNT;
    private int screenWidth;
    private int measuredWidth, measuredHeight;
    private IDropDownCategoryProvider dataProvider;
    private Drawable frame, highlightFrame;

    public FrameGridView(@NonNull Context context) {
        super(context);
    }

    public FrameGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FrameGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //============================ must called before init ================================================
    public void construct(IDropDownCategoryProvider provider) {
        this.dataProvider = provider;
        init();
    }

    //============================ init ================================================
    private void init() {
        setBackgroundColor(Color.YELLOW);
        initVars();
        onCreateView();
    }

    private void initVars() {
        colorText = ContextCompat.getColor(App.getInstance(), R.color.c333333);
        colorHighlightText = ContextCompat.getColor(App.getInstance(), R.color.highlight_red);
        horizontalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(12);
        verticalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(8);
        textHorizontalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(4);
        textVerticalPaddingSize = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(3);

        frame = SingletonFactory.getSingleton(DrawableColorProcessor.class).colorDrawable(R.drawable.round_10, R.color.c333333);
        highlightFrame = SingletonFactory.getSingleton(DrawableColorProcessor.class).colorDrawable(R.drawable.round_10, R.color.highlight_red);

        Paint paint = new Paint();
        paint.setTextSize(SingletonFactory.getSingleton(SizeProcessor.class).dp2px(SP_SIZE_ITEM_TEXT));
        Rect rect = new Rect();
        paint.getTextBounds("我", 0, 1, rect);
        itemHeight = rect.height() + 2 * textHorizontalPaddingSize;

        screenWidth = SingletonFactory.getSingleton(ScreenProcessor.class).getPortraitScreenWidth();
        itemWidth = (screenWidth - horizontalPaddingSize) / COLUMN_COUNT - horizontalPaddingSize;
        ROW_COUNT = (int) Math.ceil((float) getCount() / COLUMN_COUNT);

        measuredWidth = screenWidth;
        measuredHeight = itemHeight * ROW_COUNT + verticalPaddingSize * (ROW_COUNT + 1);
    }

    private void onCreateView() {
        int size = null == dataProvider.getDropDownCategoryData() ? 0 : dataProvider.getDropDownCategoryData().size();
        for (int i = 0; i < size; i++) {
            addItemGridView(dataProvider.getDropDownCategoryData().get(i), i);
        }
    }

    private void addItemGridView(String content, int position) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(SP_SIZE_ITEM_TEXT);
        textView.setGravity(Gravity.CENTER);
//        textView.setBackground(frame);
        textView.setTextColor(colorText);
        textView.setText(content);

        int x = position % COLUMN_COUNT;
        int y = (int) Math.floor(position / (float) COLUMN_COUNT);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(itemWidth, itemHeight);
        params.leftMargin = (x + 1) * horizontalPaddingSize + x * itemWidth;
        params.topMargin = (y + 1) * verticalPaddingSize + y * itemHeight;
        addView(textView, params);
    }

    //============================ override from View/ViewGroup ================================================

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Timber.d("onMeasure(%d, %d)", measuredWidth, measuredHeight);
        setMeasuredDimension(measuredWidth, measuredHeight);
        getLayoutParams().width = measuredWidth;
        getLayoutParams().height = measuredHeight;
    }

    //============================ getters and setters ================================================
    private int getCount() {
        return null == dataProvider || null == dataProvider.getDropDownCategoryData() ? 0 : dataProvider.getDropDownCategoryData().size();
    }
}
