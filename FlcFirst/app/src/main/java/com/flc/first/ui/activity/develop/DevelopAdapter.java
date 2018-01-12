package com.flc.first.ui.activity.develop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flc.first.R;
import com.flc.framework.callback.OnItemClickListener;

import java.util.List;

/**
 * Created by channagihong on 09/01/2018.
 */

public class DevelopAdapter extends RecyclerView.Adapter<DevelopViewHolder> {

    private List<String> data;
    private OnItemClickListener<String> onItemClickListener;

    @Override
    public DevelopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_develop, parent, false);
        return new DevelopViewHolder(view).bindAdapter(this);
    }

    @Override
    public void onBindViewHolder(DevelopViewHolder holder, int position) {
        holder.bindPosition(getItemAt(position), position);
    }

    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }

    //============================ business ================================================
    private String getItemAt(int position) {
        if (null == data) return null;
        if (position >= data.size()) return null;
        return data.get(position);
    }

    //============================ getters and setters ================================================
    public List<String> getData() {
        return data;
    }

    public DevelopAdapter setData(List<String> data) {
        this.data = data;
        return this;
    }

    public OnItemClickListener<String> getOnItemClickListener() {
        return onItemClickListener;
    }

    public DevelopAdapter setOnItemClickListener(OnItemClickListener<String> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }
}
