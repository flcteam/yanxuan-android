package com.flc.first.ui.activity.develop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flc.first.ui.widget.dropdowncategory.DropDownCategory;
import com.flc.first.ui.widget.dropdowncategory.IDropDownCategoryProvider;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by channagihong on 13/01/2018.
 */

public class DropdownCategoryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(layout, params);
        List<String> data = new LinkedList<>();
        data.add("我");
        data.add("你");
        data.add("我的");
        data.add("你的");
        data.add("我的你");
        data.add("你的我");
        data.add("我们的你们");
        data.add("你们的我们");
        data.add("Abcd");
        data.add("dcba");
        data.add("1234");
        data.add("431");
        data.add("321");
        IDropDownCategoryProvider provider = new IDropDownCategoryProvider() {
            @Override
            public List<String> getDropDownCategoryData() {
                return data;
            }

            @Override
            public String getTitle() {
                return "我就是标题";
            }
        };
        DropDownCategory category = new DropDownCategory(this);
        category.construct(provider);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(category, params1);

    }
}
