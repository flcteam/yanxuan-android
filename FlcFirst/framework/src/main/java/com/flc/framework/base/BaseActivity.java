package com.flc.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.flc.framework.dialog.LoadingDialog;

/**
 * Created by channagihong on 26/12/2017.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;

    public abstract P getPresenter();

    protected LoadingDialog loadingDialog;
    private boolean initialized = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.setContext(this);
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (initialized) {
            onComeBack();
        } else {
            initialized = !initialized;
        }
    }

    public void onComeBack() {
    }

    //============================ IBaseView ================================================
    @Override
    public void showLoading() {
        showLoading(null);
    }

    @Override
    public void showLoading(@Nullable String tips) {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(this);
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
