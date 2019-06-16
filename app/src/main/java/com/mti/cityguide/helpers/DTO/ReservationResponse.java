package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.Reservation;

import java.util.ArrayList;

public class ReservationResponse extends ApiResponse {
    @SerializedName("result")
    private ArrayList<Reservation> listReservations;

    public ArrayList<Reservation> getListReservations() {
        return listReservations;
    }

    public void setListReservations(ArrayList<Reservation> listReservations) {
        this.listReservations = listReservations;
    }
}
