package com.mindf.utils.android.Expirimental.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.mindf.utils.android.Expirimental.Http.HttpRequest;
import com.mindf.utils.android.Expirimental.Http.HttpResponseListener;
import lombok.Getter;
import lombok.Setter;

public abstract class LoginHandler {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private Activity activity;
    private Boolean saveLogin;
    private String usernameText;
    private String passwordText;
    private DataRequest dataRequest;
    private Button submit;
    @Setter private EditText username;
    @Setter private EditText password;
    @Setter private CheckBox rememberMe;
    @Getter private String result;
    @Setter private String noResultErrorMessage = "User not found !";
    @Setter private String resultErrorMessage = "An error occurred with the data request !";
    @Setter private String requestErrorMessage = "An error occurred during the data request !";
    @Setter private String welcomeMessage = "Welcome";
    @Setter private String noNetworkError = "No internet connection !";

    public void onLogRequestEnd() {}

    protected LoginHandler(Activity activity, LoginFields loginFields) {
        this.activity = activity;
        username = loginFields.getUsername();
        password = loginFields.getPassword();
        rememberMe = loginFields.getCheckBox();
        submit = loginFields.getButton();
        initPreferences();
        handleRememberedUser();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUser(noNetworkError, loginFields);
            }
        });
    }

    public void log() {
        if (!username.getText().toString().equalsIgnoreCase("")) {
            submit.callOnClick();
        }
    }

    private void logUser(String noNetworkMessage, LoginFields loginFields) {
        usernameText = this.username.getText().toString();
        passwordText = this.password.getText().toString();
        dataRequest = new DataRequest(
                loginFields.getUrl(),
                new Pair(loginFields.getUserColumnName(), usernameText),
                new Pair(loginFields.getPasswordColumnName(), passwordText)
        );
        if (HttpRequest.hasConnection(activity)) {
            if (dataRequest.getUrl().equalsIgnoreCase("")) {
                onLogRequestEnd();
            } else {
                createHttpRequest(activity, dataRequest, requestErrorMessage);
            }
        } else {
            Toast.makeText(activity, noNetworkMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void createHttpRequest(Activity activity, DataRequest dataRequest, String errorMessage) {
        final Activity ref = activity;
        HttpRequest request = new HttpRequest(new HttpResponseListener() {
            @Override
            public void onResponse(String result) {
                onRequestSuccess(result);
            }
            @Override
            public void onError(int code) {
                Toast.makeText(ref, errorMessage + " " + code, Toast.LENGTH_LONG).show();
            }
        });
        request.get(dataRequest.getUrl(), DataRequest.formatParams(dataRequest.getParams()));
    }

    private void setRememberedUser() {
        sharedPreferencesEditor.putBoolean("saveLogin", true);
        sharedPreferencesEditor.putString("username", usernameText);
        sharedPreferencesEditor.putString("password", passwordText);
        sharedPreferencesEditor.commit();
    }

    private void removeRemeberedUser() {
        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.commit();
    }

    private void onRequestSuccess(String result) {
        if (result.equalsIgnoreCase("[]")) {
            Toast.makeText(activity, noResultErrorMessage, Toast.LENGTH_SHORT).show();
        } else {
            if (rememberMe.isChecked()) {
                setRememberedUser();
                Toast.makeText(activity, welcomeMessage + " " + username.getText().toString() + " !", Toast.LENGTH_SHORT).show();
            } else {
                removeRemeberedUser();
            }
            onLogRequestEnd();
        }
    }

    private void initPreferences() {
        sharedPreferences = activity.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.apply();
        saveLogin = sharedPreferences.getBoolean("saveLogin", false);
    }

    private void handleRememberedUser() {
        if (saveLogin) {
            username.setText(sharedPreferences.getString("username", ""));
            password.setText(sharedPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }
    }
}
