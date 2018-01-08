package com.flc.framework.utils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public final class SingletonFactory {

    private static final Map<Class, Object> singletonMap = new HashMap<>();

    private SingletonFactory() {
        throw new UnsupportedOperationException();
    }

    public static <T> T getSingleton(Class<T> key) {
        synchronized (key) {
            T t = (T) singletonMap.get(key);
            if (t == null) {
                t = createInstance(key);
                singletonMap.put(key, t);
            }
            return t;
        }
    }

    private static <T> T createInstance(Class<T> clz) {
        try {
            //将构造方法置Accessible为true
            Constructor<?>[] constructors = clz.getConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
            }
            return clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
