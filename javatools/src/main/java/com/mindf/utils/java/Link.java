package com.mindf.utils.java;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Link<Key, Value> {

    @Getter @Setter private Key key;
    @Getter @Setter private Value value;

    public Link(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
