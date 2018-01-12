package com.flc.first.ui.widget.dropdowncategory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flc.framework.processor.ScreenProcessor;
import com.flc.framework.processor.SizeProcessor;
import com.flc.framework.utils.SingletonFactory;

/**
 * Created by channagihong on 09/01/2018.
 */

public class DropDownCategory extends FrameLayout {

    private final int DP_WIDTH_ARROW = 25;
    private final int DP_HEIGHT = 30;

    private RecyclerView recyclerView;
    private TextView gridTitle;
    private ImageView arrow;

    private int dpWidthArrow;

    public DropDownCategory(@NonNull Context context) {
        super(context);
        init();
    }

    public DropDownCategory(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DropDownCategory(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DropDownCategory(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

    }

    private void initVars() {
        dpWidthArrow = SingletonFactory.getSingleton(SizeProcessor.class).dp2px(DP_WIDTH_ARROW);
    }

    private void onCreateView() {
        int screenWidth = SingletonFactory.getSingleton(ScreenProcessor.class).getPortraitScreenWidth();

        recyclerView = new RecyclerView(getContext());
//        FrameLayout.LayoutParams params = new FrameLayout()
    }
}
