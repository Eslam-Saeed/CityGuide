package com.mti.cityguide.model;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.helpers.AppPreferences;
import com.mti.cityguide.helpers.Constants;

public class User {
    @SerializedName("user_id")
    @Expose
    private int id;
    @SerializedName("user_name")
    @Expose
    private String name;
    @SerializedName("user_email")
    @Expose
    private String email;
    @SerializedName("user_phone")
    @Expose
    private String phone;
    @SerializedName("user_password")
    @Expose
    private String password;


    public static void initUser(Context context, User user, String password) {
        AppPreferences.setInt(context, Constants.User.ID, user.getId());
        AppPreferences.setString(context, Constants.User.EMAIL, user.getEmail());
        AppPreferences.setString(context, Constants.User.PHONE, user.getPhone());
        AppPreferences.setString(context, Constants.User.NAME, user.getName());
        AppPreferences.setString(context, Constants.User.PASSWORD, password);
    }

    public static User getLoggedInUser(Context context) {
        User user = new User();
        user.setId(AppPreferences.getInt(context, Constants.User.ID, -1));
        user.setEmail(AppPreferences.getString(context, Constants.User.EMAIL, ""));
        user.setPhone(AppPreferences.getString(context, Constants.User.PHONE, ""));
        user.setName(AppPreferences.getString(context, Constants.User.NAME, ""));
        user.setPassword(AppPreferences.getString(context, Constants.User.PASSWORD, ""));

        return user;
    }

    public static void clearUserData(Context context) {
        AppPreferences.setInt(context, Constants.User.ID, -1);
        AppPreferences.setString(context, Constants.User.EMAIL, null);
        AppPreferences.setString(context, Constants.User.PHONE, null);
        AppPreferences.setString(context, Constants.User.NAME, null);
        AppPreferences.setString(context, Constants.User.PASSWORD, null);
    }

    public static boolean isLoggedInUser(Context context) {
        return !TextUtils.isEmpty(AppPreferences.getString(context, Constants.User.PHONE, ""));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
