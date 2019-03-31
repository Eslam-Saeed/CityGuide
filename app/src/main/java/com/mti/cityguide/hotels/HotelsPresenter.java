package com.mti.cityguide.hotels;

import android.content.Context;

import com.android.volley.Response;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.HotelResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Hotel;

import java.util.ArrayList;

class HotelsPresenter extends BasePresenter {
    private Context context;
    private HotelsView view;
    private int cityId, areaId, countryId;
    private ArrayList<Hotel> listHotels;

    HotelsPresenter(Context context, HotelsView view, int countryId, int cityId, int areaId) {
        this.context = context;
        this.view = view;
        this.countryId = countryId;
        this.cityId = cityId;
        this.areaId = areaId;
        this.listHotels = new ArrayList<>();
    }

    void loadData() {
        view.showProgress(true);
        ServicesHelper.getInstance(context).getHotels(context, countryId, cityId, areaId,
                getHotelsSuccessListener, getErrorSuccessListener);
    }

    private Response.Listener<HotelResponse> getHotelsSuccessListener = response -> {
        view.showProgress(false);
        if (response.getListHotels() != null && !response.getListHotels().isEmpty()) {
            listHotels.addAll(response.getListHotels());
            view.onHotelsLoadedSuccessfully();
        } else {
            view.showEmptyView();
        }
    };

    private Response.ErrorListener getErrorSuccessListener = error -> {
        view.showProgress(false);
        view.showErrorMessage(VolleyErrorHandler.getErrorMessage(context, error));
    };

    ArrayList<Hotel> getListHotels() {
        return listHotels;
    }
}
