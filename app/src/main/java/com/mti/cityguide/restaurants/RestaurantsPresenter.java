package com.mti.cityguide.restaurants;

import android.content.Context;

import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.model.Restaurant;

import java.util.ArrayList;

public class RestaurantsPresenter extends BasePresenter {
    private Context context;
    private RestaurantsView view;
    private int cityId, areaId, countryId;
    private ArrayList<Restaurant> listRestaurants;

    public RestaurantsPresenter(Context context, RestaurantsView view, int cityId, int areaId, int countryId) {
        this.context = context;
        this.view = view;
        this.cityId = cityId;
        this.areaId = areaId;
        this.countryId = countryId;
        this.listRestaurants = new ArrayList<>();
    }


    public ArrayList<Restaurant> getListRestaurants() {
        return listRestaurants;
    }

    public void setListRestaurants(ArrayList<Restaurant> listRestaurants) {
        this.listRestaurants = listRestaurants;
    }

    public void loadData() {

    }
}
