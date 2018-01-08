package com.flc.framework.processor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by channagihong on 27/12/2017.
 */

public class MapToListProcessor {

    public <KEY, VALUE> List<VALUE> toValueList(Map<KEY, VALUE> map) {
        List<VALUE> list = new LinkedList<>();
        if (map.isEmpty()) return list;

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<KEY, VALUE> entry = (Map.Entry<KEY, VALUE>) iterator.next();
            list.add(entry.getValue());
        }
        return list;
    }

}
