package com.mindf.utils.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;

public abstract class RecyclerListView {

    private Context context;
    private RecyclerView recyclerView;
    private int layout;
    private int size;
    private RecyclerView.LayoutManager layoutManager;
    private View view;

    protected abstract void adaptView(int position, View layoutView);

    protected RecyclerListView(RecyclerView recyclerView, @NonNull Context context, int size, int layout) {
        this.size = size;
        this.layout = layout;
        this.context = context;
        this.recyclerView = new RecyclerView(context);
        this.recyclerView = recyclerView;
        init();
    }

    private void init() {
        setLayoutManager();
        setAdapter();
    }

    private void setLayoutManager() {
        if (layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager);
        } else if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }

    private void setAdapter() {
        RecyclerAdatper recyclerAdatper = new RecyclerAdatper(context, size, layout) {
            @Override
            public void adaptView(int position, View view) {
                RecyclerListView.this.view = view;
                RecyclerListView.this.adaptView(position, view);
            }
        };
        recyclerView.setAdapter(recyclerAdatper);
    }
}
