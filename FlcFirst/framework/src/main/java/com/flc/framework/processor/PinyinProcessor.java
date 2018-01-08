package com.flc.framework.processor;

import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Created by channagihong on 28/12/2017.
 */

public class PinyinProcessor {

    public String toShortPinyin(String string) {
        try {
            return PinyinHelper.getShortPinyin(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}