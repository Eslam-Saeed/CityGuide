package com.mti.cityguide.home;

import com.mti.cityguide.base.BaseView;

import java.util.ArrayList;

public interface HomeView extends BaseView {
    void showBackground(int backgroundId);

    void setData(String title, String description);

    void navigateToHotelsScreen(int countryId,int cityId, int areaId);

    void navigateToRestaurantsScreen(int countryId,int cityId, int areaId);

    void enableArea(boolean enable);

    void enableCity(boolean enable);

    void onCityClicked();

    void onCityClicked(ArrayList<String> listCities);

    void showErrorMessage(String errorMessage);

    void onAreaClicked(ArrayList<String> listAreas);

    void onFilterClicked();
}
