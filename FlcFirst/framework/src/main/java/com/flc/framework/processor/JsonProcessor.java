package com.flc.framework.processor;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by channagihong on 26/12/2017.
 */

public class JsonProcessor {

    public <T> T fromJson(String jsonString, Class<T> clz) {
        return new Gson().fromJson(jsonString, clz);
    }

    public <T> String toJson(T t) {
        return new Gson().toJson(t);
    }

    public <T> T fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

}
