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

    private NfcFragment nfcFragment;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nfcFragment = new SomeFragment();
        ViewTools.changeFragment(this, R.id.main_frame, nfcFragment);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        String nfcTagData = nfcFragment.resolveIntent(intent);
    }
}
