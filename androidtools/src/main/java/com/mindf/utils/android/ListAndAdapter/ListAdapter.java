package com.mindf.utils.android.ListAndAdapter;

import android.content.Context;
import android.view.View;

public abstract class ListAdapter extends DefaultListAdapter {

    protected ListAdapter(Context context, int size, int layout) {
        super(context, size, layout);
    }

    @Override public abstract View adaptView(int position, View view); //todo check the return
}
