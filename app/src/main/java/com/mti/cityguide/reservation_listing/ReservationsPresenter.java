package com.mti.cityguide.reservation_listing;

import android.content.Context;

import com.android.volley.Response;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.ReservationResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Reservation;
import com.mti.cityguide.model.User;

import java.util.ArrayList;

public class ReservationsPresenter extends BasePresenter {
    private Context context;
    private ReservationsView view;
    private ArrayList<Reservation> listReservations;

    public ReservationsPresenter(Context context, ReservationsView view) {
        this.context = context;
        this.view = view;
        this.listReservations = new ArrayList<>();
    }

    void loadData() {
        view.showProgress(true);
        ServicesHelper.getInstance(context).getReservations(context, User.getLoggedInUser(context).getId(),
                getReservationsSuccessListener, getReservationsErrorSuccessListener);
    }

    private Response.Listener<ReservationResponse> getReservationsSuccessListener = response -> {
        view.showProgress(false);
        if (response.getListReservations() != null && !response.getListReservations().isEmpty()) {
            listReservations.clear();
            listReservations.addAll(response.getListReservations());
            view.onReservationsLoadedSuccessfully();
        } else {
            view.showMessage(context.getString(R.string.somethingWrong));
        }
    };

    private Response.ErrorListener getReservationsErrorSuccessListener = error -> {
        view.showProgress(false);
        view.showMessage(VolleyErrorHandler.getErrorMessage(context, error));
    };


    public ArrayList<Reservation> getListReservations() {
        return listReservations;
    }

    public void setListReservations(ArrayList<Reservation> listReservations) {
        this.listReservations = listReservations;
    }
}
