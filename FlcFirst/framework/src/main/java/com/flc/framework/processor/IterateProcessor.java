package com.flc.framework.processor;

import android.support.annotation.Nullable;

import com.flc.framework.callback.Action;

import java.util.Iterator;

/**
 * Created by channagihong on 07/01/2018.
 */

public class IterateProcessor {

    public <TYPE> void iterate(@Nullable Iterable<TYPE> iterable, @Nullable Action<TYPE> action) {
        if (null == iterable || null == action) return;

        Iterator<TYPE> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            TYPE value = iterator.next();
            action.action(value);
        }
    }

}
