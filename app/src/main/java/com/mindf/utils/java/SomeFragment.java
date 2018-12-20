package com.mindf.utils.java;

import android.view.View;
import android.widget.Toast;

import com.mindf.utils.android.Nfc.NfcFragment;

public class SomeFragment extends NfcFragment {

    private View view;

    @Override
    public int setLayout() {
        return R.layout.some_fragment;
    }

    @Override
    public void onCreate(View view) {
        this.view = view;
    }

    @Override
    public void handleMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
