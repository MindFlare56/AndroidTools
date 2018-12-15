package com.mindf.utils.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.mindf.utils.android.Dialog;
import com.mindf.utils.android.ViewTools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewTools.logv(DateTime.getADateMilliSecondsValue("2018-12-14"));
    }
}
