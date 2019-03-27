package com.mti.cityguide.hotels;

import com.mti.cityguide.base.BaseView;

public interface HotelsView extends BaseView {
    void showErrorMessage(String errorMessage);

    void onHotelsLoadedSuccessfully();

    void showEmptyView();
}
