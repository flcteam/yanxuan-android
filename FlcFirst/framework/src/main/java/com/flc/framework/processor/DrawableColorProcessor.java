package com.flc.framework.processor;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import com.flc.framework.BaseApp;

/**
 * 陈毅康
 * 1/1/18
 */

public class DrawableColorProcessor {

    public Drawable frameDrawable(@ColorRes int colorRes, int cornersPx, int strokeWidthPx) {
        Context context = BaseApp.getInstance();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(strokeWidthPx, ContextCompat.getColor(context, colorRes));
        drawable.setCornerRadius(cornersPx);
        return drawable;
    }

    public Drawable colorDrawable(@DrawableRes int drawableRes, @ColorRes int colorRes) {
        Context context = BaseApp.getInstance();
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        return colorDrawable(drawable, colorRes);
    }

    public Drawable colorDrawable(Drawable drawable, @ColorRes int colorRes) {
        if (null == drawable) return null;

        Context context = BaseApp.getInstance();
        if (drawable instanceof ShapeDrawable) {
            ShapeDrawable shapeDrawable = (ShapeDrawable) drawable;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(context, colorRes));
        } else if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            gradientDrawable.setColor(ContextCompat.getColor(context, colorRes));
        } else if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            colorDrawable.setColor(ContextCompat.getColor(context, colorRes));
        }
        return drawable;
    }

}
