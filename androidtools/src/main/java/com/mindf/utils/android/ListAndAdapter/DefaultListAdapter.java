package com.mindf.utils.android.ListAndAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

abstract class DefaultListAdapter extends BaseAdapter {

    private Context context;
    private int size;
    private int layout;
    private ViewHolder viewHolder;

    DefaultListAdapter(Context context, int size, int layout) {
        this.context = context;
        this.size = size;
        this.layout = layout;
    }

    View adaptView(int position, View view) {
        return view;
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int position) {
        return adaptView(position, viewHolder.itemView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            viewHolder = new DefaultListAdapter.ViewHolder(convertView);
        }
        getItem(position);
        return convertView;
    }

class ViewHolder extends RecyclerView.ViewHolder{
    ViewHolder(View itemView) {
        super(itemView);
    }
}
}
