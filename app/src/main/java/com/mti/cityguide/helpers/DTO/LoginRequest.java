package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user_phone")
    private String phone;
    @SerializedName("user_password")
    private String password;

    public LoginRequest(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
