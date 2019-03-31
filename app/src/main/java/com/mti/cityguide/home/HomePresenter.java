package com.mti.cityguide.home;

import android.content.Context;

import com.android.volley.Response;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.DTO.AreaResponse;
import com.mti.cityguide.helpers.DTO.CityResponse;
import com.mti.cityguide.helpers.DTO.CountryResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.model.Area;
import com.mti.cityguide.model.City;
import com.mti.cityguide.model.Country;

import java.util.ArrayList;

public class HomePresenter extends BasePresenter {
    private Context context;
    private HomeView view;
    private int type;
    private ArrayList<Country> listCountries;
    private ArrayList<City> listCities;
    private ArrayList<Area> listAreas;
    private int selectedCountryId = Constants.GeneralKeys.ALL, selectedCityId = Constants.GeneralKeys.ALL, selectedAreaId = Constants.GeneralKeys.ALL;

    public HomePresenter(Context context, HomeView view, int type) {
        this.context = context;
        this.view = view;
        this.type = type;
        this.listCountries = new ArrayList<>();
        this.listCities = new ArrayList<>();
        this.listAreas = new ArrayList<>();
    }

    public void loadData() {
        getListCountriesCloud();
        if (type == Constants.Types.HOTELS) {
            view.showBackground(R.drawable.background_hotels);
            view.setData(context.getString(R.string.hotels), context.getString(R.string.hotels_desc));
        } else {
            view.showBackground(R.drawable.background_restaurant);
            view.setData(context.getString(R.string.restaurants), context.getString(R.string.restaurants_desc));
        }
    }

    private void getListCountriesCloud() {
        ServicesHelper.getInstance(context).getCountries(context, getCountriesSuccessListener, error -> {
        });
    }

    private Response.Listener<CountryResponse> getCountriesSuccessListener = new Response.Listener<CountryResponse>() {
        @Override
        public void onResponse(CountryResponse response) {
            if (response.getListCountries() != null && !response.getListCountries().isEmpty()) {
                listCountries.addAll(response.getListCountries());
            }
        }
    };

    void getListCitiesCloud() {
        ServicesHelper.getInstance(context).getCities(context, getSelectedCountryId(), getCitiesSuccessListener, error -> {
        });
    }

    private Response.Listener<CityResponse> getCitiesSuccessListener = new Response.Listener<CityResponse>() {
        @Override
        public void onResponse(CityResponse response) {
            if (response.getListCities() != null && !response.getListCities().isEmpty()) {
                view.enableCity(true);
                listCities.clear();
                listCities.addAll(response.getListCities());
            }
        }
    };

    void getListAreasCloud() {
        ServicesHelper.getInstance(context).getAreas(context, getSelectedCityId(), getAreasSuccessListener, error -> listAreas.clear());
    }

    private Response.Listener<AreaResponse> getAreasSuccessListener = new Response.Listener<AreaResponse>() {
        @Override
        public void onResponse(AreaResponse response) {
            if (response.getListAreas() != null && !response.getListAreas().isEmpty()) {
                view.enableArea(true);
                listAreas.clear();
                listAreas.addAll(response.getListAreas());
            }
        }
    };


    public int getSelectedCountryId() {
        return selectedCountryId;
    }

    public void setSelectedCountryId(int selectedCountryId) {
        this.selectedCountryId = listCountries.get(selectedCountryId).getId();
    }

    public int getSelectedCityId() {
        return selectedCityId;
    }

    public void setSelectedCityId(int selectedCityPosition) {
        if (selectedCityPosition == -1)
            this.selectedCityId = Constants.GeneralKeys.ALL;
        else
            this.selectedCityId = listCities.get(selectedCityPosition).getId();
    }

    public int getSelectedAreaId() {
        return selectedAreaId;
    }

    public void setSelectedAreaId(int selectedAreaPosition) {
        if (selectedAreaPosition == -1)
            this.selectedAreaId = Constants.GeneralKeys.ALL;
        else
            this.selectedAreaId = listAreas.get(selectedAreaPosition).getId();
    }

    ArrayList<String> getListCitiesTitles() {
        ArrayList<String> countries = new ArrayList<>();
        for (int i = 0; i < listCities.size(); i++) {
            countries.add(listCities.get(i).getName());
        }
        return countries;
    }

    ArrayList<String> getListAreasTitles() {
        ArrayList<String> areas = new ArrayList<>();
        for (int i = 0; i < listAreas.size(); i++) {
            areas.add(listAreas.get(i).getName());
        }
        return areas;
    }

    void filterClicked() {
        if (type == Constants.Types.HOTELS) {
            view.navigateToHotelsScreen(selectedCountryId, selectedCityId, selectedAreaId);
        } else {
            view.navigateToRestaurantsScreen(selectedCountryId,selectedCityId, selectedAreaId);
        }
    }

    public ArrayList<String> getListCountriesTitles() {
        ArrayList<String> countries = new ArrayList<>();
        for (int i = 0; i < listCountries.size(); i++) {
            countries.add(listCountries.get(i).getName());
        }
        return countries;
    }
}
