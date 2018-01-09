package com.flc.first.ui.widget.dropdowncategory.framegridview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.flc.first.App;
import com.flc.first.R;
import com.flc.first.ui.widget.dropdowncategory.IDropDownCategoryProvider;

/**
 * Created by channagihong on 09/01/2018.
 */

public class FrameGridView extends FrameLayout {

    private final int COLUMN_COUNT = 4;
    private final int DP_SIZE_ITEM_TEXT = 14;
    private final int COLOR_TEXT = ContextCompat.getColor(App.getInstance(), R.color.black);
    private final int COLOR_HIGHLIGHT_TEXT = ContextCompat.getColor(App.getInstance(), R.color.highlight_red);

    private IDropDownCategoryProvider dataProvider;

    public FrameGridView(@NonNull Context context) {
        super(context);
        init();
    }

    public FrameGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrameGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FrameGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //============================ init ================================================
    private void init() {
    }

    private void initVars() {

    }

    private void onCreateView() {

    }
}
