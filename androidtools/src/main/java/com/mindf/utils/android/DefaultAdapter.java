package com.mindf.utils.android;

import android.content.Context;
import android.view.View;

public abstract class DefaultAdapter extends RecyclerAdapter {

    protected DefaultAdapter(Context context, int size, int layout) {
        super(context, size, layout);
    }

    @Override public abstract void adaptView(int position, View view);
}
