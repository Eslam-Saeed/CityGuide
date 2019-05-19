package com.mti.cityguide.hotels.listing;

import com.mti.cityguide.base.BaseView;

public interface HotelsView extends BaseView {
    void showErrorMessage(String errorMessage);

    void onHotelsLoadedSuccessfully();

    void showEmptyView();

    void onFilterClicked();

    boolean onSearchClicked(int actionId);

    void handleSearchCLicked();

    void callSearch(String searchKeyword);
}
