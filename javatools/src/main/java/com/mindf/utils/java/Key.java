package com.mindf.utils.java;

import android.support.annotation.Nullable;

import java.util.List;

import lombok.Getter;

class Key {

    @Getter private String key;
    @Getter private String primaryKey;
    @Getter private List<String> secondaryKeys = null;

    public Key(String primaryKey) {
        this.primaryKey = primaryKey;
        key = primaryKey;
    }

    public Key(String primaryKey, @Nullable List<String> secondaryKeys) {
        this.primaryKey = primaryKey;
        if (secondaryKeys != null) {
            this.secondaryKeys = secondaryKeys;
            key = primaryKey + formatSecondaryKeys();
        }
    }

    private String formatSecondaryKeys() {
        StringBuilder result = new StringBuilder();
        for (String secondaryKey : secondaryKeys) {
            result.append(secondaryKey).append(',');
        }
        return result.substring(0, result.length() - 1);
    }
}
