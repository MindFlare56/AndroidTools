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

    public AlertDialog.Builder createConfirmationDialog(Activity activity, String confirmationMessage, String title, String positionName, String negativeName) {
        return new CustomDialogBuilder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setIconColor(activity, R.attr.colorPrimary)
                .setTitle(title)
                .setTitleColor(activity, R.attr.colorPrimary)//todo refactor method cuz context already pass
                .setMessage(confirmationMessage)
                .setDividerColor(ViewTools.getAttrColorString(activity, R.attr.colorPrimary))
                .setPositiveButton(positionName, createPositiveButtonListener())
                .setNegativeButton(negativeName, createNegativeButtonListener());
    }

    public AlertDialog.Builder createConfirmationDialog(Activity activity, String confirmationMessage, String title, String positionName, String negativeName, int dialogIcon) {
        return new CustomDialogBuilder(activity)
                .setIcon(dialogIcon)
                .setIconColor(activity, R.attr.colorPrimary)
                .setTitle(title)
                .setTitleColor(activity, R.attr.colorPrimary)//todo refactor method cuz context already pass
                .setMessage(confirmationMessage)
                .setDividerColor(ViewTools.getAttrColorString(activity, R.attr.colorPrimary))
                .setPositiveButton(positionName, createPositiveButtonListener())
                .setNegativeButton(negativeName, createNegativeButtonListener());
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
