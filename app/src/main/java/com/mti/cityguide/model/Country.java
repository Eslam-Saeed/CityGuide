package com.mti.cityguide.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country_id")
    private int id;
    @SerializedName("country_name")
    private String name;
    @SerializedName("country_code")
    private String code;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
