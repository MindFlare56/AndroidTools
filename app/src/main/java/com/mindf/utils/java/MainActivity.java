package com.mindf.utils.java;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mindf.utils.android.Nfc.NfcFragment;
import com.mindf.utils.android.ViewTools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private NfcFragment fragment;
    @BindView(R.id.nfc_test_view) TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragment = new SomeFragment();
        ViewTools.changeFragment(this, R.id.main_frame, fragment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        String message = fragment.resolveIntent(intent);
        textView.setText(message);
    }
}
