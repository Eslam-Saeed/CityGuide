package com.mti.cityguide.hotels;

import android.content.Context;

import com.android.volley.Response;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.HotelResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Hotel;
import com.mti.cityguide.model.User;

import java.util.ArrayList;

public class HotelsPresenter extends BasePresenter {
    private Context context;
    private HotelsView view;
    private int cityId, areaId;
    private ArrayList<Hotel> listHotels;

    public HotelsPresenter(Context context, HotelsView view, int cityId, int areaId) {
        this.context = context;
        this.view = view;
        this.cityId = cityId;
        this.areaId = areaId;
        this.listHotels = new ArrayList<>();
    }

    public void loadData() {
        view.showProgress(true);
        ServicesHelper.getInstance(context).getHotels(context, User.getLoggedInUser(context).getCountry(), cityId, areaId,
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

    public ArrayList<Hotel> getListHotels() {
        return listHotels;
    }
}
