package com.rahmacom.rimesyarifix.data.network.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    @Override
    public String toString() {
        return
                "ResponseLogin{" +
                        "access_token = '" + accessToken + '\'' +
                        ",token_type = '" + tokenType + '\'' +
                        ",expires_in = '" + expiresIn + '\'' +
                        "}";
    }
}