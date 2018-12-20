package com.mindf.utils.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mindf.utils.android.Translator;
import com.mindf.utils.android.ViewTools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Translator("hello world I am Dave and it's nice to meet y'all") {
            @Override
            public void onResult(String result) {
                ViewTools.logv(result);
            }
        };
    }
}
