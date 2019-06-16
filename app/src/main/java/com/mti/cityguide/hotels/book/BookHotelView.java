package com.mti.cityguide.hotels.book;

import com.mti.cityguide.base.BaseView;

public interface BookHotelView extends BaseView {
    void showMessage(String message);

    void onBookSuccess();
}
