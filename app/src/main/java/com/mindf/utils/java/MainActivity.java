package com.mindf.utils.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.mindf.utils.android.Dialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Dialog() {
            @Override
            protected void onPositiveButton() {
                Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
            }
        }.createConfirmationDialog(this, "Are you sure?", "title").show();
    }
}
