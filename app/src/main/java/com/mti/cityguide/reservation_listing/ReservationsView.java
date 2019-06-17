package com.mti.cityguide.reservation_listing;

import com.mti.cityguide.base.BaseView;

public interface ReservationsView extends BaseView {
    void showMessage(String message);

    void onReservationsLoadedSuccessfully();
}
