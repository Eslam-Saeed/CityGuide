package com.mti.cityguide.restaurants;

import com.mti.cityguide.base.BaseView;

public interface RestaurantsView extends BaseView {
    void onRestaurantsLoadedSuccessfully();

    void showEmptyView();

    void showErrorMessage(String errorMessage);
}
