package com.mti.cityguide.restaurants;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.RestaurantCategoriesResponse;
import com.mti.cityguide.helpers.DTO.RestaurantHotelFilter;
import com.mti.cityguide.helpers.DTO.RestaurantResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Restaurant;
import com.mti.cityguide.model.RestaurantCategory;

import java.util.ArrayList;

public class RestaurantsPresenter extends BasePresenter {
    private Context context;
    private RestaurantsView view;
    private ArrayList<Restaurant> listRestaurants;
    private RestaurantHotelFilter restaurantHotelFilter;
    private ArrayList<RestaurantCategory> listRestaurantCategories;

    RestaurantsPresenter(Context context, RestaurantsView view, int cityId, int areaId, int countryId) {
        this.context = context;
        this.view = view;
        this.restaurantHotelFilter = new RestaurantHotelFilter(countryId, cityId, areaId);
        this.listRestaurants = new ArrayList<>();
        this.listRestaurantCategories = new ArrayList<>();
    }

    void loadCategories(){
        ServicesHelper.getInstance(context).getRestaurantsCategories(context, getRestaurantsCategoriesSuccessListener, getErrorSuccessListener);
    }

    void loadData() {
        view.showProgress(true);
        ServicesHelper.getInstance(context).getRestaurants(context, restaurantHotelFilter,
                getRestaurantsSuccessListener, getErrorSuccessListener);
    }

    private Response.Listener<RestaurantResponse> getRestaurantsSuccessListener = response -> {
        view.showProgress(false);
        if (response.getListRestaurants() != null && !response.getListRestaurants().isEmpty()) {
            listRestaurants.clear();
            listRestaurants.addAll(response.getListRestaurants());
            view.onRestaurantsLoadedSuccessfully();
        } else {
            view.showEmptyView();
        }
    };

    private Response.Listener<RestaurantCategoriesResponse> getRestaurantsCategoriesSuccessListener = response -> {
        view.showProgress(false);
        if (response.getListRestaurantCategories() != null && !response.getListRestaurantCategories().isEmpty()) {
            listRestaurantCategories.clear();
            listRestaurantCategories.add(new RestaurantCategory(-1, context.getString(R.string.all)));
            listRestaurantCategories.addAll(response.getListRestaurantCategories());
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

    RestaurantHotelFilter getRestaurantHotelFilter() {
        return restaurantHotelFilter;
    }

    public void setRestaurantHotelFilter(RestaurantHotelFilter restaurantHotelFilter) {
        this.restaurantHotelFilter = restaurantHotelFilter;
    }

    ArrayList<RestaurantCategory> getListRestaurantCategories() {
        return listRestaurantCategories;
    }

    public void setListRestaurantCategories(ArrayList<RestaurantCategory> listRestaurantCategories) {
        this.listRestaurantCategories = listRestaurantCategories;
    }

    ArrayList<String> getListRestaurantCategoriesTitles() {
        ArrayList<String> listRestaurantCategoriesTitles = new ArrayList<>();
        for (int i = 0; i < listRestaurantCategories.size(); i++) {
            if (!TextUtils.isEmpty(listRestaurantCategories.get(i).getCategoryName()))
                listRestaurantCategoriesTitles.add(listRestaurantCategories.get(i).getCategoryName());
        }
        return listRestaurantCategoriesTitles;
    }
}
