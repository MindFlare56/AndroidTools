package com.mindf.utils.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tools {

    @SuppressWarnings("unchecked")
    public static <T> List<T> mapToList(Map<String, ?> map) {
        Object[] objects = map.values().toArray();
        List<T> castedObject = new ArrayList<>();
        for (Object object : objects) {
            castedObject.add((T) object);
        }
        return castedObject;
    }

    public static String capitalize(String string) {
        string = string.toLowerCase();
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
}