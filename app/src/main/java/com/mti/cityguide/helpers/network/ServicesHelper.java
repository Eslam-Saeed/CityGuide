package com.mti.cityguide.helpers.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mti.cityguide.helpers.DTO.AreasResponse;
import com.mti.cityguide.helpers.DTO.CitiesResponse;
import com.mti.cityguide.helpers.DTO.CountryResponse;
import com.mti.cityguide.helpers.DTO.LoginRequest;
import com.mti.cityguide.helpers.DTO.LoginResponse;
import com.mti.cityguide.model.User;

import org.json.JSONObject;


public class ServicesHelper {

    private static ServicesHelper mInstance;
    private RequestQueue mRequestQueue;

    private final String BASE_URL = "http://192.168.1.2/cityguide/index.php/api/";
    private final String FAIL_CODE = "failed_fail";
    private final String LOGIN_URL = BASE_URL + "user/login";
    private final String REGISTER_URL = BASE_URL + "user/adduser";
    private final String COUNTRIES_URL = BASE_URL + "countries/";
    private final String CITIES_URL = BASE_URL + "countries/";
    private final String AREAS_URL = BASE_URL + "countries/";

    public enum Tag {
        LOGIN,
        REGISTER, COUNTRIES, CITIES, AREAS
    }

    private ServicesHelper(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized ServicesHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ServicesHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private <T> void addToRequestQueue(Request<T> req) {
        mRequestQueue.add(req);
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    //============ Login ====================
    public void login(Context context, LoginRequest loginRequest, final Response.Listener<LoginResponse> loginSuccessListener, final Response.ErrorListener loginErrorListener) {
        try {
            String loginJson = GsonWrapper.getInstance().getGson().toJson(loginRequest, LoginRequest.class);
            new CustomJsonObjectRequest(context, Request.Method.POST, LOGIN_URL, new JSONObject(loginJson), response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    LoginResponse loginResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), LoginResponse.class);
                    loginSuccessListener.onResponse(loginResponse);
                } else
                    loginErrorListener.onErrorResponse(new VolleyError());
            }, loginErrorListener, Tag.LOGIN);
        } catch (Exception e) {
            e.printStackTrace();
            loginErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //================================ Lookups ==========================

    public void getCountries(Context context, final Response.Listener<CountryResponse> getCountiresSuccessListener, final Response.ErrorListener getCountriesErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, COUNTRIES_URL, null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    CountryResponse countryResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), CountryResponse.class);
                    getCountiresSuccessListener.onResponse(countryResponse);
                } else
                    getCountriesErrorListener.onErrorResponse(new VolleyError());
            }, getCountriesErrorListener, Tag.COUNTRIES);
        } catch (Exception e) {
            e.printStackTrace();
            getCountriesErrorListener.onErrorResponse(new VolleyError());
        }
    }

    public void getCities(Context context, int countryId, final Response.Listener<CitiesResponse> getCitiesSuccessListener, final Response.ErrorListener getCitiesErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, "", null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    CitiesResponse citiesResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), CitiesResponse.class);
                    getCitiesSuccessListener.onResponse(citiesResponse);
                } else
                    getCitiesErrorListener.onErrorResponse(new VolleyError());
            }, getCitiesErrorListener, Tag.CITIES);
        } catch (Exception e) {
            e.printStackTrace();
            getCitiesErrorListener.onErrorResponse(new VolleyError());
        }
    }

    public void getAreas(Context context, int cityId, final Response.Listener<AreasResponse> getAreasResponseListener, final Response.ErrorListener getAreasErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, "", null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    AreasResponse areasResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), AreasResponse.class);
                    getAreasResponseListener.onResponse(areasResponse);
                } else
                    getAreasErrorListener.onErrorResponse(new VolleyError());
            }, getAreasErrorListener, Tag.AREAS);
        } catch (Exception e) {
            e.printStackTrace();
            getAreasErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //================================ Register =================================
    public void register(Context context, User registerRequest, final Response.Listener<LoginResponse> registerSuccessListener, final Response.ErrorListener registerErrorListener) {
        try {
            String registerJson = GsonWrapper.getInstance().getGson().toJson(registerRequest, User.class);
            new CustomJsonObjectRequest(context, Request.Method.POST, REGISTER_URL, new JSONObject(registerJson), response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    LoginResponse registerResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), LoginResponse.class);
                    registerSuccessListener.onResponse(registerResponse);
                } else
                    registerErrorListener.onErrorResponse(new VolleyError());
            }, registerErrorListener, Tag.REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
            registerErrorListener.onErrorResponse(new VolleyError());
        }
    }
}