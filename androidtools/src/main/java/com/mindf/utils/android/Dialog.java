package com.mindf.utils.android;

import android.app.Activity;
import android.app.AlertDialog;

public abstract class Dialog {

    protected abstract void onPositiveButton();
    protected void onNegativeButton() {}

    public void createConfirmationDialog(Activity activity) {
        new AlertDialog.Builder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", (dialog, which) -> onPositiveButton())
                .setNegativeButton("No", ((dialog, which) -> onNegativeButton()))
                .show();
    }
}
