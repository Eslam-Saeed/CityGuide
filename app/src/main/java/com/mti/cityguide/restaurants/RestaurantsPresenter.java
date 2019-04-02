package com.mti.cityguide.restaurants;

import android.content.Context;

import com.android.volley.Response;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.RestaurantResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Restaurant;

import java.util.ArrayList;

public class RestaurantsPresenter extends BasePresenter {
    private Context context;
    private RestaurantsView view;
    private int cityId, areaId, countryId;
    private ArrayList<Restaurant> listRestaurants;

    RestaurantsPresenter(Context context, RestaurantsView view, int cityId, int areaId, int countryId) {
        this.context = context;
        this.view = view;
        this.cityId = cityId;
        this.areaId = areaId;
        this.countryId = countryId;
        this.listRestaurants = new ArrayList<>();
    }

    void loadData() {
        view.showProgress(true);
        ServicesHelper.getInstance(context).getRestaurants(context, countryId, cityId, areaId,
                getRestaurantsSuccessListener, getErrorSuccessListener);
    }

    private Response.Listener<RestaurantResponse> getRestaurantsSuccessListener = response -> {
        view.showProgress(false);
        if (response.getListRestaurants() != null && !response.getListRestaurants().isEmpty()) {
            listRestaurants.addAll(response.getListRestaurants());
            view.onRestaurantsLoadedSuccessfully();
        } else {
            view.showEmptyView();
        }
    };

    private Response.ErrorListener getErrorSuccessListener = error -> {
        view.showProgress(false);
        view.showErrorMessage(VolleyErrorHandler.getErrorMessage(context, error));
    };

    ArrayList<Restaurant> getListRestaurants() {
        return listRestaurants;
    }

    public void setListRestaurants(ArrayList<Restaurant> listRestaurants) {
        this.listRestaurants = listRestaurants;
    }
}
