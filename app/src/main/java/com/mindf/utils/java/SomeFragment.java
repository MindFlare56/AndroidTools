package com.mindf.utils.java;

import android.view.View;
import android.widget.Toast;

import com.mindf.utils.android.Nfc.NfcFragment;

public class SomeFragment extends NfcFragment {
    @Override
    public int setLayout() {
        return R.layout.some_fragment;//set your fragment layout here
    }
    @Override
    public void onCreate(View view) {
        //get fragment built view here
    }
    @Override
    public void handleMessage(String message) {
        //get message here
    }
}
