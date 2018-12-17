package com.mindf.utils.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;

import com.mindf.utils.android.ViewTools;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkedMap<Pair<String, String>, Bob> map = new LinkedMap<>();
        List<String> secondKeys = new ArrayList<>();
        secondKeys.add("b1");
        secondKeys.add("b2");
        List<Bob> list = new ArrayList<>();
        list.add(new Bob(1));
        list.add(new Bob(2));
        map.putAll(map.<String, String>buildComposedKeys("bob", secondKeys), list);
        ViewTools.logv(map.getKeys().get(0).first);
        ViewTools.logv(map.getKeys().get(0).second);
        ViewTools.logv(map.getKeys().get(1).first);
        ViewTools.logv(map.getKeys().get(1).second);
    }

    public class Bob {
        int someInt;
        public Bob(int someInt) {
            this.someInt = someInt;
        }
    }
}
