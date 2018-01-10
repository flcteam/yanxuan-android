package com.flc.framework.processor;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * Created by channagihong on 02/01/2018.
 */

public class DrawableSizeProcessor {

    public Drawable resize(@Nullable Drawable drawable, int width, int height) {
        return resize(drawable, 0, 0, width, height);
    }

    private Drawable resize(@Nullable Drawable drawable, @Nullable Rect rect) {
        if (null == drawable) return null;
        if (null == rect) return drawable;

        drawable.setBounds(rect);
        return drawable;
    }

    private Drawable resize(@Nullable Drawable drawable, int left, int top, int right, int bottom) {
        if (null == drawable) return null;

        drawable.setBounds(left, top, right, bottom);
        return drawable;
    }

}
