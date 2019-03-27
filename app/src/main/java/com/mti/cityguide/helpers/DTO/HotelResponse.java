package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.Hotel;

import java.util.ArrayList;

public class HotelResponse extends ApiResponse {
    @SerializedName("result")
    private ArrayList<Hotel> listHotels;

    public ArrayList<Hotel> getListHotels() {
        return listHotels;
    }

    public void setListHotels(ArrayList<Hotel> listHotels) {
        this.listHotels = listHotels;
    }
}
