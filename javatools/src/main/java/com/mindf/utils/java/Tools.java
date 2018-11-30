package com.mindf.utils.java;

import java.lang.reflect.Type;
import java.util.Map;

public class Tools {

    public static Type[] mapToArray(Map<String, Type> map) {
        Object[] objects = map.values().toArray();
        Type[] castedObject = new Type[objects.length];
        for (int i = 0; i < objects.length; i++) {
            castedObject[i] = (Type) objects[i];
        }
        return castedObject;
    }
}
