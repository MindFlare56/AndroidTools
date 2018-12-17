package com.mindf.utils.java;

import android.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

public class LinkedMap<Key, Value> extends LinkedHashMap<Key, Value> implements Map<Key, Value> {

    @Getter @Setter private Value defaultValue = null;
    private LinkedHashMap<Key, Value> linkedHashMap = new LinkedHashMap<>();
    private List<Link> list;

    public LinkedMap() {
        list = new ArrayList<>();
    }

    public LinkedMap(List<Link> list) {
        this.list = new ArrayList<>();
        addAll(list);
    }

    public LinkedMap(LinkedMap<Key, Value> linkedMap) {
        linkedHashMap = linkedMap;
        list = new ArrayList<>(((LinkedMap<Object, Object>) linkedHashMap).getList());
    }

    public LinkedMap(List<Key> keys, List<Value> values) {
        list = new ArrayList<>();
        putAll(keys, values);
    }

    public Link makeLink(Key key, Value value) {
        return new Link<>(key, value);
    }

    public Value put(Key key, Value value) {
        linkedHashMap.put(key, value);
        list.add(new Link<>(key, value));
        return value;
    }

    public List<Link> add(Link link) {
        put((Key) link.getKey(), (Value) link.getValue());
        return list;
    }

    public Value putIfAbsent(Key key, Value value) {
        if (!contain(key)) {
            put(key, value);
            return value;
        }
        return null;
    }

    public List<Link> addIfAbsent(Link link) {
        if (!contain((Key) link.getKey())) {
            add(link);
        }
        return list;
    }

    public List<Value> putAll(List<Key> keys, List<Value> values) {
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                Key key = keys.get(i);
                Value value = values.get(i);
                linkedHashMap.put(key, value);
                values.add(values.get(i));
                list.add(makeLink(key, value));
            }
            return values;
        } else {
            return null;
        }
    }

    public void addAll(List<Link> list) {
        for (Link link : list) {
            add(link);
        }
    }

    @Override
    public void putAll(@android.support.annotation.NonNull Map<? extends Key, ? extends Value> linkedMap) {
        LinkedMap tempMap = new LinkedMap((LinkedMap) linkedMap);
        tempMap.keySet().removeAll(linkedHashMap.keySet());
        linkedHashMap.putAll(tempMap);
        list = Tools.mapToLinkList(linkedHashMap);
    }

    public boolean contains(LinkedMap linkedMap) {
        return contains(linkedMap.getList());
    }

    public boolean contain(Key key) {
        return linkedHashMap.get(key) != null;
    }

    public boolean contains(List<Link> list) {
        for (Link link : list) {
           if (!contain(link)) {
               return false;
           }
        }
        return true;
    }

    public boolean contain(Link link) {
        for (Link currentLink : list) {
            if (currentLink.getValue() == link.getValue() && currentLink.getKey() == link.getKey()) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(List<Link> list) {
        return this.list.equals(list);
    }

    public Value get(String key) {
        return linkedHashMap.get(key);
    }

    public Value get(int index) {
        return (Value) list.get(index).getValue();
    }

    public Key getKey(int index) {
        return (Key) list.get(index).getKey();
    }

    public Link gets(int index) {
        return list.get(index);
    }

    public Value getOrDefaultValue(Key key) {
        return linkedHashMap.containsKey(key) ? linkedHashMap.get(key) : defaultValue;
    }

    public List<Value> getValues() {
        List<Value> values = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            values.add((Value)this.list.get(i).getValue());
        }
        return values;
    }

    public List<Key> getKeys() {
        List<Key> keys = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            keys.add((Key) this.list.get(i).getKey());
        }
        return keys;
    }

    public void replace(Link link) {
        int index = getIndex(link.getKey());
        list.remove(index);
        list.add(index, link);
        linkedHashMap.replace((Key) link.getKey(), (Value) link.getValue());
    }

    public Value replace(Key key, Value newValue) {
        int index = getIndex(key);
        list.remove(index);
        list.add(index, new Link(key, newValue));
        linkedHashMap.replace(key, newValue);
        return newValue;
    }

    public int getIndex(Object key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public List<Link> getList() {
        return new ArrayList<>(this.list);
    }

    public Value remove(Object key) {
        list.remove(getIndex(key));
        return linkedHashMap.remove(key);
    }

    public void remove(int index) {
        Object key = list.remove(index).getKey();
        linkedHashMap.remove(key);
    }

    public void removeAll() {
        list = new ArrayList<>();
        linkedHashMap.clear();
    }

    public boolean removeAll(LinkedMap linkedMap) {
        if (contains(linkedMap)) {
            List<Link> list = linkedMap.getList();
            for (int i = 0; i < list.size(); i++) {
                remove(list.get(i).getKey());
            }
            return true;
        }
        return false;
    }

    public boolean removeAll(List<Link> list) {
        if (contains(list)) {
            for (int i = 0; i < list.size(); i++) {
                remove(list.get(i).getKey());
            }
            return true;
        }
        return false;
    }

    public <T, T2> List<Pair<T, T2>> buildComposedKeys(Object first, List<?> seconds) {
        //first must not to the same as second
        List<Pair<T, T2>> keys = new ArrayList<>();
        for (Object second : seconds) {
            keys.add(new Pair(first, second));
        }
        return keys;
    }
}
