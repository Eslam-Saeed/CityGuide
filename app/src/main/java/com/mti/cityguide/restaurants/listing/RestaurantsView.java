package com.mti.cityguide.restaurants.listing;

import com.mti.cityguide.base.BaseView;

import java.util.ArrayList;

public interface RestaurantsView extends BaseView {
    void onRestaurantsLoadedSuccessfully();

    void showEmptyView();

    void showErrorMessage(String errorMessage);

    void onCategoryClicked(ArrayList<String> listCategories);

    void onFilterClicked();

    boolean onSearchClicked(int actionId);

    void handleSearchCLicked();

    void callSearch(String searchKeyword);
}
