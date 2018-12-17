package com.mindf.utils.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

public class LinkedMap<Key, Value> extends LinkedHashMap<Key, Value> implements Map<Key, Value> {

    @Getter @Setter private Value defaultValue = null;
    private LinkedHashMap<Key, Value> linkedHashMap = new LinkedHashMap<>();
    private List<Link> links;

    public LinkedMap() {
        links = new ArrayList<>();
    }

    public LinkedMap(List<Link> links) {
        this.links = new ArrayList<>();
        addAll(links);
    }

    public LinkedMap(LinkedMap<Key, Value> linkedMap) {
        linkedHashMap = linkedMap;
        links = new ArrayList<>(((LinkedMap<Object, Object>) linkedHashMap).getLinks());
    }

    public LinkedMap(List<Key> keys, List<Value> values) {
        links = new ArrayList<>();
        putAll(keys, values);
    }

    public Link makeLink(Key key, Value value) {
        return new Link<>(key, value);
    }

    public Value put(Key key, Value value) {
        linkedHashMap.put(key, value);
        links.add(new Link<>(key, value));
        return value;
    }

    public List<Link> add(Link link) {
        put((Key) link.getKey(), (Value) link.getValue());
        return links;
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
        return links;
    }

    public List<Value> putAll(List<Key> keys, List<Value> values) {
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                Key key = keys.get(i);
                Value value = values.get(i);
                linkedHashMap.put(key, value);
                values.add(values.get(i));
                links.add(makeLink(key, value));
            }
            return values;
        } else {
            return null;
        }
    }

    public void addAll(List<Link> links) {
        for (Link link : links) {
            add(link);
        }
    }

    @Override
    public void putAll(@android.support.annotation.NonNull Map<? extends Key, ? extends Value> linkedMap) {
        LinkedMap tempMap = new LinkedMap((LinkedMap) linkedMap);
        tempMap.keySet().removeAll(linkedHashMap.keySet());
        linkedHashMap.putAll(tempMap);
        links = Tools.mapToLinkList(linkedHashMap);
    }

    public boolean contains(LinkedMap linkedMap) {
        return contains(linkedMap.getLinks());
    }

    public boolean contain(Key key) {
        return linkedHashMap.get(key) != null;
    }

    public boolean contains(List<Link> links) {
        for (Link link : links) {
           if (!contain(link)) {
               return false;
           }
        }
        return true;
    }

    public boolean contain(Link link) {
        for (Link currentLink : links) {
            if (currentLink.getValue() == link.getValue() && currentLink.getKey() == link.getKey()) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(List<Link> links) {
        return this.links.equals(links);
    }

    public Value get(String key) {
        return linkedHashMap.get(key);
    }

    public Value get(int index) {
        return (Value) links.get(index).getValue();
    }

    public Key getKey(int index) {
        return (Key) links.get(index).getKey();
    }

    public Link gets(int index) {
        return links.get(index);
    }

    public Value getOrDefaultValue(Key key) {
        return linkedHashMap.containsKey(key) ? linkedHashMap.get(key) : defaultValue;
    }

    public List<Value> getValues() {
        List<Value> values = new ArrayList<>();
        for (int i = 0; i < this.links.size(); i++) {
            values.add((Value)this.links.get(i).getValue());
        }
        return values;
    }

    public List<Key> getKeys() {
        List<Key> keys = new ArrayList<>();
        for (int i = 0; i < this.links.size(); i++) {
            keys.add((Key) this.links.get(i).getKey());
        }
        return keys;
    }

    public void replace(Link link) {
        int index = getIndex(link.getKey());
        links.remove(index);
        links.add(index, link);
        linkedHashMap.replace((Key) link.getKey(), (Value) link.getValue());
    }

    public Value replace(Key key, Value newValue) {
        int index = getIndex(key);
        links.remove(index);
        links.add(index, new Link(key, newValue));
        linkedHashMap.replace(key, newValue);
        return newValue;
    }

    public int getIndex(Object key) {
        for (int i = 0; i < links.size(); i++) {
            if (links.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public List<Link> getLinks() {
        return new ArrayList<>(this.links);
    }

    public Value remove(Object key) {
        links.remove(getIndex(key));
        return linkedHashMap.remove(key);
    }

    public void remove(int index) {
        Object key = links.remove(index).getKey();
        linkedHashMap.remove(key);
    }

    public void removeAll() {
        links = new ArrayList<>();
        linkedHashMap.clear();
    }

    public boolean removeAll(LinkedMap linkedMap) {
        if (contains(linkedMap)) {
            List<Link> links = linkedMap.getLinks();
            for (int i = 0; i < links.size(); i++) {
                remove(links.get(i).getKey());
            }
            return true;
        }
        return false;
    }

    public boolean removeAll(List<Link> links) {
        if (contains(links)) {
            for (int i = 0; i < links.size(); i++) {
                remove(links.get(i).getKey());
            }
            return true;
        }
        return false;
    }
}
