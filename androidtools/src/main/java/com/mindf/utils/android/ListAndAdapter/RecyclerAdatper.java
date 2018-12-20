package com.mindf.utils.android.ListAndAdapter;

import android.content.Context;
import android.view.View;

public abstract class RecyclerAdatper extends DefaultRecyclerAdapter {

    protected RecyclerAdatper(Context context, int size, int layout) {
        super(context, size, layout);
    }

    @Override public abstract void adaptView(int position, View view);
}
