package com.mindf.utils.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialogBuilder extends AlertDialog.Builder{

    private View mDialogView;
    private TextView mTitle;
    private ImageView mIcon;
    private TextView mMessage;
    private View mDivider;

    public CustomDialogBuilder(Context context) {
        super(context);
        mDialogView = View.inflate(context, R.layout.qustom_dialog_layout, null);
        setView(mDialogView);
        mTitle = mDialogView.findViewById(R.id.alertTitle);
        mMessage = mDialogView.findViewById(R.id.message);
        mIcon = mDialogView.findViewById(R.id.icon);
        mDivider = mDialogView.findViewById(R.id.titleDivider);
    }

    public CustomDialogBuilder setDividerColor(String colorString) {
        mDivider.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }

    @Override
    public CustomDialogBuilder setTitle(CharSequence text) {
        mTitle.setText(text);
        return this;
    }

    public CustomDialogBuilder setTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public CustomDialogBuilder setTitleColor(Activity activity, int colorInt) {
        setTitleColor(ViewTools.getAttrColorString(activity, colorInt));
        return this;
    }

    @Override
    public CustomDialogBuilder setMessage(int textResId) {
        mMessage.setText(textResId);
        return this;
    }

    @Override
    public CustomDialogBuilder setMessage(CharSequence text) {
        mMessage.setText(text);
        return this;
    }

    @Override
    public CustomDialogBuilder setIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }

    public CustomDialogBuilder setIconColor(Activity activity, int tint) {
        mIcon.setColorFilter(ViewTools.getAttrColor(activity, tint));
        return this;
    }

    @Override
    public CustomDialogBuilder setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
        return this;
    }

    public CustomDialogBuilder setCustomView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        ((FrameLayout)mDialogView.findViewById(R.id.customPanel)).addView(customView);
        return this;
    }

    @Override
    public AlertDialog show() {
        if (mTitle.getText().equals("")) mDialogView.findViewById(R.id.topPanel).setVisibility(View.GONE);
        return super.show();
    }
}
//Thanks to https://github.com/danoz73/QustomDialog