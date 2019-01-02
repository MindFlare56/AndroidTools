package com.mindf.utils.android.Expirimental.Login;

import android.util.Pair;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.OnClick;
import lombok.Getter;
import lombok.Setter;

public class LoginFields {

    @Getter private EditText username;
    @Getter private EditText password;
    @Getter private CheckBox checkBox;
    @Getter private Button button;
    @Getter private String url;
    @Getter private String userColumnName;
    @Getter private String passwordColumnName;

    public LoginFields(EditText username, EditText password, CheckBox checkBox, Button button) {
        this.username = username;
        this.password = password;
        this.checkBox = checkBox;
        this.button = button;
    }

    public String getUsernameText() {
        return username.getText().toString();
    }

    public String getPasswordText() {
        return password.getText().toString();
    }

    @SafeVarargs
    private final String formatParams(Pair<String, Object>... pairs) {
        if (pairs.length > 0) {
            StringBuilder params = new StringBuilder();
            for (Pair<String, Object> pair : pairs) {
                params.append(pair.first).append("=").append(pair.second).append("&");
            }
            return params.toString().substring(0, params.length() - 1);
        }
        return "";
    }

    public void setRequestFields(String url, String userColumnName, String passwordColumnName) {
        this.url = url;
        this.userColumnName = userColumnName;
        this.passwordColumnName = passwordColumnName;
    }
}
