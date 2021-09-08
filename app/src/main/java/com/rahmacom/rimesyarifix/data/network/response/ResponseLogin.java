package com.rahmacom.rimesyarifix.data.network.response;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private int expiresIn;

    @SerializedName("expires_until")
    private int expiresUntil;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public int getExpiresUntil() {
        return expiresUntil;
    }
}