package com.flc.first.ui.activity.develop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flc.first.R;

/**
 * Created by channagihong on 09/01/2018.
 */

public class DevelopViewHolder extends RecyclerView.ViewHolder {

    private int index;
    private DevelopAdapter adapter;
    private String text;

    public DevelopViewHolder(View itemView) {
        super(itemView);
    }

    public DevelopViewHolder bindAdapter(DevelopAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public void bindPosition(String text, int index) {
        this.index = index;
        this.text = text;
        ((TextView) itemView.findViewById(R.id.item_develop_text)).setText(text);
        itemView.setOnClickListener(v -> {
            if (null != adapter && null != adapter.getOnItemClickListener()) {
                adapter.getOnItemClickListener().onItemClick(index, text);
            }
        });
    }
}
