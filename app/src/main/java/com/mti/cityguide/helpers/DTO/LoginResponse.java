package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.User;

public class LoginResponse {
    @SerializedName("result")
    private User user;

    public User getUser() {
        return user;
    }
}
