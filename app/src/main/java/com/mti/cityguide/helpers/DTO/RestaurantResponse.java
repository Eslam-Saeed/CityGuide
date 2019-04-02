package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.Restaurant;

import java.util.ArrayList;

public class RestaurantResponse extends ApiResponse {
    @SerializedName("result")
    private ArrayList<Restaurant> listRestaurants;

    public ArrayList<Restaurant> getListRestaurants() {
        return listRestaurants;
    }

    public void setListRestaurants(ArrayList<Restaurant> listRestaurants) {
        this.listRestaurants = listRestaurants;
    }
}
