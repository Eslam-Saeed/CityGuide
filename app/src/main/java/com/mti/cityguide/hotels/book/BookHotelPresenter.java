package com.mti.cityguide.hotels.book;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.ConnectionDetector;
import com.mti.cityguide.helpers.DTO.ApiResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Hotel;
import com.mti.cityguide.model.Reservation;
import com.mti.cityguide.model.User;

public class BookHotelPresenter extends BasePresenter {
    private Context context;
    private BookHotelView view;
    private Hotel hotel;
    private Reservation reservation;

    public BookHotelPresenter(Context context, BookHotelView view, Hotel hotel) {
        this.context = context;
        this.view = view;
        this.hotel = hotel;
        this.reservation = new Reservation();
        this.reservation.setHotelId(Integer.parseInt(hotel.getHotelId()));
        this.reservation.setUserId(User.getLoggedInUser(context).getId());
    }

    void book() {
        if (ConnectionDetector.isConnected(context)) {
            view.showProgress(true);
            ServicesHelper.getInstance(context).book(context, reservation, bookSuccessListener, bookErrorListener);
        } else {
            view.showMessage(context.getString(R.string.noInternet));
        }
    }

    private Response.Listener<ApiResponse> bookSuccessListener = new Response.Listener<ApiResponse>() {
        @Override
        public void onResponse(ApiResponse response) {
            view.showProgress(false);
            if (response != null) {
                view.showMessage(context.getString(R.string.booked_success));
                view.onBookSuccess();
            } else
                view.showMessage(context.getString(R.string.somethingWrong));
        }
    };

    private Response.ErrorListener bookErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            view.showProgress(false);
            view.showMessage(VolleyErrorHandler.getErrorMessage(context, error));
        }
    };

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
