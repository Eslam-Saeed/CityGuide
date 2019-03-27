package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.City;

import java.util.ArrayList;

public class CityResponse extends ApiResponse {
    @SerializedName("result")
    private ArrayList<City> listCities;

    public ArrayList<City> getListCities() {
        return listCities;
    }

    public void setListCities(ArrayList<City> listCities) {
        this.listCities = listCities;
    }
}
