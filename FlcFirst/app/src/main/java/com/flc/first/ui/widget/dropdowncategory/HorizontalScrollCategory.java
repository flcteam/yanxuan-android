package com.flc.first.ui.widget.dropdowncategory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.flc.framework.callback.Action;
import com.flc.framework.callback.OnItemClickListener;

import timber.log.Timber;

/**
 * Created by channagihong on 14/01/2018.
 */

public class HorizontalScrollCategory extends HorizontalScrollView {

    private HorizontalCategory category;
    private OnItemClickListener<String> onItemClickListener;
    private IDropDownCategoryProvider dataProvider;

    public HorizontalScrollCategory(Context context) {
        super(context);
    }

    public HorizontalScrollCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalScrollCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HorizontalScrollCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //============================ must called before init ================================================
    public void construct(IDropDownCategoryProvider provider) {
        dataProvider = provider;
        initCategory();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
    }

    //============================ init ================================================
    private void initCategory() {
        category = new HorizontalCategory(getContext());
        HorizontalScrollView.LayoutParams params = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        addView(category, params);
        category.construct(dataProvider);
        category.setOnItemClickListener(onItemClickListener);
        category.setOnHighlightEdgeCallback(integer -> {
            Timber.d("highlightEdgeCallback(%d)", integer);
            smoothScrollBy(integer, 0);
        });
    }

    //============================ business ================================================
    public void setOnItemClickListener(OnItemClickListener<String> listener) {
        if (null != category) {
            category.setOnItemClickListener(listener);
        }
        this.onItemClickListener = listener;
    }

    private int current = 0;
    private boolean forward = true;

    public void start() {
        category.setHighlight(current);
        if (forward) {
            current++;
        } else {
            current--;
        }
        int count = null == dataProvider || null == dataProvider.getDropDownCategoryData() ? 0 : dataProvider.getDropDownCategoryData().size();
        if (current <= 0) forward = true;
        if (current >= count - 1) forward = false;
        postDelayed(this::start, 1000);
    }

}
