package com.rahmacom.rimesyarifix.ui.login;

import android.text.TextUtils;

public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUsernameValid() {
        return !TextUtils.isEmpty(username);
    }

    public boolean isPasswordValid() {
        return password.length() >= 8 && password.matches("[A-Za-z0-9]");
    }
}
