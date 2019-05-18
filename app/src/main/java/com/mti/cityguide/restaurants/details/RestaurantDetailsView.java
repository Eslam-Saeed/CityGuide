package com.mti.cityguide.restaurants.details;

import com.mti.cityguide.base.BaseView;
import com.mti.cityguide.model.Restaurant;

public interface RestaurantDetailsView extends BaseView {
    void showErrorMessage(String errorMessage);

    void onMenuLoadedSuccessfully();

    void showEmptyMenuView();

    void showRestaurantData(Restaurant restaurant);
}
