package com.mindf.utils.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;

public abstract class Dialog {

    protected abstract void onPositiveButton();
    protected void onNegativeButton() {}

    //setTitleColor
    //setDividerColor

    public AlertDialog.Builder createConfirmationDialog(Activity activity, String confirmationMessage, String title) {
        return new CustomDialogBuilder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setIconColor(activity, R.attr.colorPrimary)
                .setTitle(title)
                .setTitleColor(activity, R.attr.colorPrimary)//todo refactor method cuz context already pass
                .setMessage(confirmationMessage)
                .setDividerColor(ViewTools.getAttrColorString(activity, R.attr.colorPrimary))
                .setPositiveButton("Yes", createPositiveButtonListener())
                .setNegativeButton("No", createNegativeButtonListener());
    }

    public void createConfirmationDialog(Activity activity, String confirmationMessage, String title, String positionName, String negativeName) {
        new AlertDialog.Builder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(confirmationMessage)
                .setPositiveButton("Yes", createPositiveButtonListener())
                .setNegativeButton("No", createNegativeButtonListener())
                .show();
    }

    public void createConfirmationDialog(Activity activity, String confirmationMessage, String title, String positionName, String negativeName, int dialogIcon) {
        new AlertDialog.Builder(activity)
                .setIcon(dialogIcon)
                .setTitle(title)
                .setMessage(confirmationMessage)
                .setPositiveButton(positionName, createPositiveButtonListener())
                .setNegativeButton(negativeName, createNegativeButtonListener())
                .show();
    }

    private DialogInterface.OnClickListener createPositiveButtonListener() {
        return new DialogInterface.OnClickListener() { //do not turn into lambda it cause crash
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPositiveButton();
            }

        };
    }

    private DialogInterface.OnClickListener createNegativeButtonListener() {
        return new DialogInterface.OnClickListener() { //do not turn into lambda it cause crash
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onNegativeButton();
            }
        };
    }
}
