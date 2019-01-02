package com.mindf.utils.android.Expirimental.Login;

import android.support.annotation.Nullable;
import android.util.Pair;

import com.mindf.utils.android.Expirimental.Controller.Database.Query;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class DataRequest extends Query {

    @Getter @Setter private String url;
    @Getter @Setter private Pair[] params;

    @SafeVarargs
    public DataRequest(String url, @Nullable Pair<String, Object>... pairs) {
        this.url = url;
        this.params = pairs;
    }

    @Override
    protected void getJSONArray() {

    }

    public static Pair param(String column, Object data) {
        Object resultData;
        if (data instanceof Boolean) {
            if ((Boolean) data) {
                resultData = 1;
            } else {
                resultData = 0;
            }
        } else {
            resultData = data;
        }
        return new Pair(column, resultData);
    }

    @SafeVarargs
    public static String formatParams(Pair<String, Object> ... pairs) {
        if (pairs.length > 0) {
            StringBuilder params = new StringBuilder();
            for (Pair<String, Object> pair : pairs) {
                params.append(pair.first).append("=").append(pair.second).append("&");
            }
            return params.toString().substring(0, params.length() - 1);
        }
        return "";
    }
}
