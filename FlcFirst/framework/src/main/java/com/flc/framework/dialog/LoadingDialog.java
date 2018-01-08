package com.flc.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flc.framework.R;

/**
 * Created by channagihong on 7/1/17
 */

public class LoadingDialog extends Dialog {

    private Context context;

    private View contentView;
    private ProgressBar progressBar;
    private TextView textView;
    private LoadingBackPressedListener listener;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.center_content_dialog);
        this.context = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        initViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView);
        setCanceledOnTouchOutside(false);
    }

    private void initViews() {
        progressBar = contentView.findViewById(R.id.dialog_loading_progress);
        textView = contentView.findViewById(R.id.dialog_loading_text);
    }

    public void show(String text) {
        if (null != textView) {
            textView.setText(text);
            textView.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        }
        show();
    }

    @Override
    public void onBackPressed() {
        if (null != listener) listener.onLoadingBackPressed();
        super.onBackPressed();
    }

    public interface LoadingBackPressedListener {
        void onLoadingBackPressed();
    }

}
