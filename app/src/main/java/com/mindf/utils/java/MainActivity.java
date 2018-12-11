package com.mindf.utils.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.mindf.utils.android.RecyclerListView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.test_textview) TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        new RecyclerListView(recyclerView, this, strings.size(), R.layout.test_view) {
            @Override
            protected void adaptView(int position, View view) {
                textView.setText(strings.get(position));
            }
        };
    }
}
