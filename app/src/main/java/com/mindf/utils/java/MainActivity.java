package com.mindf.utils.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;

import com.mindf.utils.android.DateTime;
import com.mindf.utils.android.ViewTools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        String date = DateTime.calendarToStringDate(calendar);
        ViewTools.logv(date);
    }
}
