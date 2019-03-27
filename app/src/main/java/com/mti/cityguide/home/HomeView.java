package com.mti.cityguide.home;

import com.mti.cityguide.base.BaseView;

public interface HomeView extends BaseView {
    void showBackground(int backgroundId);

    void setData(String title, String description);

    void navigateToHotelsScreen(int cityId, int areaId);

    void navigateToRestaurantsScreen(int cityId, int areaId);

    void enableArea(boolean enable);
}
