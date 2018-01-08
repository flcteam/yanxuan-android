package com.flc.framework.processor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by channagihong on 28/12/2017.
 */

public class MapIterateProcessor {

    public <KEY, VALUE> void iterateMap(@Nullable Map<KEY, VALUE> map, @NonNull IterateCallback<KEY, VALUE> callback) {
        if (null == map || null == callback) return;

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<KEY, VALUE> entry = (Map.Entry<KEY, VALUE>) iterator.next();
            callback.iterate(entry.getKey(), entry.getValue());
        }
    }

    public interface IterateCallback<KEY, VALUE> {

        public void iterate(KEY key, VALUE value);

    }

}
