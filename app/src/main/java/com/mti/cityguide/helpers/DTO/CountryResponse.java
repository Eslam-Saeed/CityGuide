package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.Country;

import java.util.ArrayList;

public class CountryResponse extends ApiResponse{
    @SerializedName("result")
    private ArrayList<Country> listCountries;

    public ArrayList<Country> getListCountries() {
        return listCountries;
    }

    public void setListCountries(ArrayList<Country> listCountries) {
        this.listCountries = listCountries;
    }
}
