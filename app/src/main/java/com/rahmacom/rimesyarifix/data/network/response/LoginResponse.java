package com.rahmacom.rimesyarifix.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.rahmacom.rimesyarifix.data.entity.User;

public class LoginResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private int expiresIn;

    @SerializedName("user")
    private User user;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public User getUser() {
        return user;
    }
}