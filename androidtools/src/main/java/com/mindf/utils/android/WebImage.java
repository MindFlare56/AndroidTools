package com.mindf.utils.android;

import android.app.Activity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class WebImage {

    public static void setImageView(Activity activity, ImageView image, String url, int width, int height) {
        Glide.with(activity).load(url).apply(new RequestOptions().override(width, height)).into(image);
    }
	
	public static void setImageView(Activity activity, ImageView image, String url) {
        Glide.with(activity).load(url).into(image);
    }
}
