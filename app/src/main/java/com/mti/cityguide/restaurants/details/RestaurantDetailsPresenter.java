package com.mti.cityguide.restaurants.details;

import android.content.Context;

import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.model.Menu;
import com.mti.cityguide.model.Restaurant;

import java.util.ArrayList;

public class RestaurantDetailsPresenter extends BasePresenter {
    private Context context;
    private RestaurantDetailsView view;
    private Restaurant restaurant;
    private ArrayList<Menu> listMenu;

    public RestaurantDetailsPresenter(Context context, RestaurantDetailsView view, Restaurant restaurant) {
        this.context = context;
        this.view = view;
        this.restaurant = restaurant;
        this.listMenu = new ArrayList<>();
    }

    void getMenu(){

    }


    public ArrayList<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }
}
