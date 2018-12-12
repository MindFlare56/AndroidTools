package com.mindf.utils.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

abstract class DefaultRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private int size;
    private int layout;
    private RecyclerView.LayoutManager layoutManager;

    DefaultRecyclerAdapter(Context context, int size, int layout) {
        this.context = context;
        this.layout = layout;
        this.size = size;
    }

    void adaptView(int position, View layoutView) {}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        adaptView(position, holder.itemView);
    }

    @Override
    public int getItemCount() {
        return size;
    }

class ViewHolder extends RecyclerView.ViewHolder {
    ViewHolder(View itemView) {
        super(itemView);
    }
}
}
