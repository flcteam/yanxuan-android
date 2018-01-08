package com.flc.framework.utils;

import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by channagihong on 27/12/2017.
 */

public class ImageUtils {

    public static void show(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) return;
        if (null == imageView) return;
        if (null == imageView.getContext()) return;

        GlideApp.with(imageView.getContext()).load(url).fitCenter().into(imageView);
    }

}
