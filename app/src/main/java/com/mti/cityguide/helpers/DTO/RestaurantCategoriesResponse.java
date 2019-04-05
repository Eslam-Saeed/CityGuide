package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.RestaurantCategory;

import java.util.ArrayList;

public class RestaurantCategoriesResponse extends ApiResponse {
    @SerializedName("result")
    private ArrayList<RestaurantCategory> listRestaurantCategories;

    public ArrayList<RestaurantCategory> getListRestaurantCategories() {
        return listRestaurantCategories;
    }

    public void setListRestaurantCategories(ArrayList<RestaurantCategory> listRestaurantCategories) {
        this.listRestaurantCategories = listRestaurantCategories;
    }
}
