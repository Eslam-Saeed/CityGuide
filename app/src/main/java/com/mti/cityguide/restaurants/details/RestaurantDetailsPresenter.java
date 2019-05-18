package com.mti.cityguide.restaurants.details;

import android.content.Context;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.DTO.MenuResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Menu;
import com.mti.cityguide.model.Restaurant;

import java.util.ArrayList;

public class RestaurantDetailsPresenter extends BasePresenter {
    private Context context;
    private RestaurantDetailsView view;
    private Restaurant restaurant;
    private ArrayList<Menu> listMenuBreakfast,listMenuLunch,listMenuDinner;

    public RestaurantDetailsPresenter(Context context, RestaurantDetailsView view, Restaurant restaurant) {
        this.context = context;
        this.view = view;
        this.restaurant = restaurant;
        this.listMenuBreakfast = new ArrayList<>();
        this.listMenuLunch = new ArrayList<>();
        this.listMenuDinner = new ArrayList<>();
    }

    public void callGetMenu() {
        view.showProgress(true);
        ServicesHelper.getInstance(context).getMenus(context, Integer.parseInt(restaurant.getRestaurantId()),
                getMenuSuccessListener, getErrorSuccessListener);
    }

    private Response.Listener<MenuResponse> getMenuSuccessListener = new Response.Listener<MenuResponse>() {
        @Override
        public void onResponse(MenuResponse response) {
            view.showProgress(false);
            if (response.getListMenus() != null && !response.getListMenus().isEmpty()) {
                for (Menu item:response.getListMenus()) {
                    if(item.getDishTypeId() == Constants.GeneralKeys.BREAKFAST){
                        listMenuBreakfast.add(item);
                    }else if(item.getDishTypeId() == Constants.GeneralKeys.LUNCH){
                        listMenuLunch.add(item);
                    }else{
                        listMenuDinner.add(item);
                    }
                }
                view.onMenuLoadedSuccessfully();
            } else {
                view.showEmptyMenuView();
            }
        }
    };

    private Response.ErrorListener getErrorSuccessListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            view.showProgress(false);
            view.showErrorMessage(VolleyErrorHandler.getErrorMessage(context, error));
        }
    };

    public ArrayList<Menu> getListMenuBreakfast() {
        return listMenuBreakfast;
    }

    public ArrayList<Menu> getListMenuLunch() {
        return listMenuLunch;
    }

    public ArrayList<Menu> getListMenuDinner() {
        return listMenuDinner;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void loadRestaurantData() {
        view.showRestaurantData(restaurant);
    }
}
