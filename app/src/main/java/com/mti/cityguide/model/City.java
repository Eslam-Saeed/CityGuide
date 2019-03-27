package com.mti.cityguide.model;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("city_id")
    private int id;
    @SerializedName("city_name")
    private String name;
    @SerializedName("city_code")
    private String code;
    @SerializedName("city_country_id")
    private String countryid;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCountryid() {
        return countryid;
    }
}
