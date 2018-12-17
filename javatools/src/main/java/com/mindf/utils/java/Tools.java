package com.mindf.utils.java;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tools {

    public static String capitalize(String string) {
        string = string.toLowerCase();
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static <T> List<T> mapToList(Map<?, ?> map) {
        Object[] objects = map.values().toArray();
        List<T> castedObject = new ArrayList<>();
        for (Object object : objects) {
            castedObject.add((T) object);
        }
        return castedObject;
    }

    public static List<Link> mapToLinkList(Map<?, ?> map) {
        Object[] values = map.values().toArray();
        Object[] keys = map.keySet().toArray();
        List<Link> link = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            link.add(new Link(keys[i], values[i]));
        }
        return link;
    }
}