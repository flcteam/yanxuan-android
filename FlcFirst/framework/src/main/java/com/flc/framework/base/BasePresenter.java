package com.flc.framework.base;

import android.content.Context;

public class BasePresenter<V extends IBaseView> {

    protected V mView;
    protected Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    public BasePresenter setView(V v) {
        this.mView = v;
        this.onAttached();
        return this;
    }

    public V getView() {
        return mView;
    }

    public void onAttached() {
    }

}
