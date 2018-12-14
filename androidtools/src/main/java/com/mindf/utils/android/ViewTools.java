package com.mindf.utils.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.mindf.utils.java.DateTime;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewTools {

    private static ArrayList<View> layoutViews;

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1;
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static int getColor(Context context, int id, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColor(context.getResources(), id, theme);
    }

    public static int getColor(Context context, int id) {
        return ResourcesCompat.getColor(context.getResources(), id, null);
    }

    public static Fragment changeFragment(AppCompatActivity appCompatActivity, int frameId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(fragment, tag);
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
        return fragment;
    }

    public static Fragment changeFragment(Activity activity, int frameId, Fragment fragment, String tag) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(fragment, tag);
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
        return fragment;
    }

    public static Fragment changeFragment(AppCompatActivity appCompatActivity, int frameId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
        return fragment;
    }

    public static Fragment changeFragment(Activity activity, int frameId, Fragment fragment) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
        return fragment;
    }

    public static void changeScreen(Activity activity, Class desiredClass) {
        Explode explode = new Explode();
        explode.setDuration(500);
        activity.getWindow().setExitTransition(explode);
        activity.getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(activity);
        changeActivity(activity, desiredClass);
    }

    public static void loadLayout(android.support.v4.app.Fragment fragment,  AppCompatActivity activity, int frameId) {
        android.support.v4.app.Fragment newFragment;
        android.support.v4.app.FragmentTransaction transaction;
        transaction = activity.getSupportFragmentManager().beginTransaction();
        newFragment = fragment;
        transaction.replace(frameId, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void setAsLastChild(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.removeView(view);
        viewGroup.addView(view, viewGroup.getChildCount());
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }
            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }
        }
        return views;
    }

    public static void logv(Context context, Object data) {
        Log.v("| Debug | " + ": From: " + context.getClass().getName() + DateTime.getCurrentTime() + " -> ", String.valueOf(data));
    }

    public static void logi(Context context, String info, Object data) {
        Log.i(info + ": From: " + context.getClass().getName() + " | " + DateTime.getCurrentTime() + " -> ", String.valueOf(data));
    }

    public static void biglogv(Context context, Object data) {
        logv(context, "\n\n" + data + "\n\n");
    }

    public static void logv(Object data) {
        Log.v("Debug | " + DateTime.getCurrentTime() + " -> ", String.valueOf(data));
    }

    public static void logi(String info, Object data) {
        Log.i(info + " | " + DateTime.getCurrentTime() + " -> ", String.valueOf(data));
    }

    public static void logv(Object data, Object data2) {
        Log.v("Debug | " + DateTime.getCurrentTime() + " -> ", String.valueOf(data + " | " + data2));
    }

    public static void biglogv(Object data) {
        logv("\n\n" + data + "\n\n");
    }

    public static ArrayList<View> getViewContent(View view, ConstraintLayout layout) {
        layoutViews = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); ++i) {
            int id = layout.getChildAt(i).getId();
            addCastedView(view, id);
        }
        return layoutViews;
    }

    public static ArrayList<View> getViewContent(View view, LinearLayout layout) {
        layoutViews = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); ++i) {
            int id = layout.getChildAt(i).getId();
            addCastedView(view, id);
        }
        return layoutViews;
    }

    public static ArrayList<View> getViewContent(View view, RelativeLayout layout) {
        layoutViews = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); ++i) {
            int id = layout.getChildAt(i).getId();
            addCastedView(view, id);
        }
        return layoutViews;
    }

    public static ArrayList<View> getViewContent(View view, FrameLayout layout) {
        layoutViews = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); ++i) {
            int id = layout.getChildAt(i).getId();
            addCastedView(view, id);
        }
        return layoutViews;
    }

    private static void addCastedView(View view, int id) {
        View child = view.findViewById(id);
        if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            layoutViews.add(imageView);
        } else if (child instanceof TextView) {
            TextView textView = (TextView) child;
            layoutViews.add(textView);
        } else if (child instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) child;
            layoutViews.add(linearLayout);
        } else if (child instanceof ConstraintLayout) {
            ConstraintLayout constraintLayout = (ConstraintLayout) child;
            layoutViews.add(constraintLayout);
        } else if (child instanceof RelativeLayout) {
            RelativeLayout relativeLayout = (RelativeLayout) child;
            layoutViews.add(relativeLayout);
        } else if (child != null) {
            layoutViews.add(child);
        }
    }

    public static void changeActivity(Activity activity, Class desiredClass) {
        Intent intent = new Intent(activity, desiredClass);
        activity.startActivity(intent);//sometime create crash with oc2 parameter ?
        activity.finish();
    }

    public static void changeActivity(Activity activity, Class desiredClass, Bundle bundle) {
        Intent intent = new Intent(activity, desiredClass);
        intent.putExtras(bundle);
        activity.startActivity(intent);//sometime create crash with oc2 parameter ?
        activity.finish();
    }


    public static void changeScreenOnUi(Activity targetActivity, Class desiredClass) {
        targetActivity.runOnUiThread(
                () -> {
                    createExplosion(targetActivity);
                    changeActivity(targetActivity, desiredClass);
                }
        );
    }

    public static void changeScreenOnUi(Activity targetActivity, Class desiredClass, Bundle bundle) {
        targetActivity.runOnUiThread(
                () -> {
                    createExplosion(targetActivity);
                    changeActivity(targetActivity, desiredClass, bundle);
                }
        );
    }

    private static void createExplosion(Activity activity) {
        Explode explode = new Explode();
        explode.setDuration(500);
        activity.getWindow().setExitTransition(explode);
        activity.getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(activity);
    }

    public static void changeParent(View view, FrameLayout newParent) {
        ViewGroup viewParent = (ViewGroup) view.getParent();
        viewParent.removeView(view);
        newParent.addView(view);
    }

    public static void updateBasicSpinner(Activity activity, Spinner spinner, ArrayList<String> items) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public static void updateBasicSpinner(Activity activity, Spinner spinner, int size) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(String.valueOf(i));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public static void updateSpinner() {
        //todo make an overridable method that send params
    }

    public static ImageView createImage(Context context, int id) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(id);
        return imageView;
    }

    public static List<ImageView> createImages(Context context, int ... ids) {
        List<ImageView> images = new ArrayList<>();
        for (int id : ids) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(id);
            images.add(imageView);
        }
        return images;
    }

    public static String logList(List<?> list) {
        String log = Arrays.toString(list.toArray());
        logv(log);
        return log;
    }

    public static String logMap(Map<?, ?> map) {
        String log = Arrays.toString(map.keySet().toArray());
        logv(log);
        return log;
    }

    public static int getAttrColor(Context context, int res) {
        TypedArray a = context.obtainStyledAttributes(new TypedValue().data, new int[] { res });
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public static String getAttrColorString(Context context, int res) {
        return String.format("#%06X", 0xFFFFFF & getAttrColor(context, res));
    }
}

