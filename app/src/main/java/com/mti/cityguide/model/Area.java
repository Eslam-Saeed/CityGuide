package com.mti.cityguide.model;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("area_id")
    private int id;
    @SerializedName("area_name")
    private String name;
    @SerializedName("area_city_id")
    private String cityId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCityId() {
        return cityId;
    }
}
