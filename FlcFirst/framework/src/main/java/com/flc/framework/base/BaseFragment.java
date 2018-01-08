package com.flc.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.flc.framework.dialog.LoadingDialog;

/**
 * 陈毅康
 * 1/3/18
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected P presenter;

    public abstract P getPresenter();

    protected LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.setContext(getContext());
        presenter.setView(this);
    }

    @Override
    public void showLoading() {
        showLoading(null);
    }

    @Override
    public void showLoading(@Nullable String tips) {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(getContext());
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show(tips);
    }

    @Override
    public void dismissLoading() {
        if (null == loadingDialog) return;
        if (!loadingDialog.isShowing()) return;
        loadingDialog.dismiss();
    }
}
