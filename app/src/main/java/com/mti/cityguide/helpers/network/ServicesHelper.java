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
import com.mti.cityguide.helpers.DTO.RegisterRequest;
import com.mti.cityguide.helpers.DTO.RegisterResponse;

import org.json.JSONObject;


public class ServicesHelper {

    private static ServicesHelper mInstance;
    private RequestQueue mRequestQueue;

    private final String BASE_URL = ""; //Staging Server
    private final String LOGIN_URL = BASE_URL + "user/login";
    private final String COUNTRIES_URL = BASE_URL + "countries/";
    private final String CITIES_URL = BASE_URL + "countries/";
    private final String AREAS_URL = BASE_URL + "countries/";
    private final String REGISTER_URL = BASE_URL + "user/register";

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
            CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(false, context, Request.Method.POST, LOGIN_URL, new JSONObject(loginJson), response -> {
                if (response != null) {
                    LoginResponse loginResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), LoginResponse.class);
                    loginSuccessListener.onResponse(loginResponse);
                } else
                    loginErrorListener.onErrorResponse(new VolleyError());
            }, loginErrorListener);
            customJsonObjectRequest.setTag(Tag.LOGIN);
            ServicesQueue.getInstance(context).getRequestQueue().add(customJsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            loginErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //================================ Lookups ==========================

    public void getCountries(Context context, final Response.Listener<CountryResponse> getCountiresSuccessListener, final Response.ErrorListener getCountriesErrorListener) {
        try {
            CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(false, context, Request.Method.GET, COUNTRIES_URL, null, response -> {
                if (response != null) {
                    CountryResponse countryResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), CountryResponse.class);
                    getCountiresSuccessListener.onResponse(countryResponse);
                } else
                    getCountriesErrorListener.onErrorResponse(new VolleyError());
            }, getCountriesErrorListener);
            customJsonObjectRequest.setTag(Tag.COUNTRIES);
            ServicesQueue.getInstance(context).getRequestQueue().add(customJsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            getCountriesErrorListener.onErrorResponse(new VolleyError());
        }
    }

    public void getCities(Context context, int countryId, final Response.Listener<CitiesResponse> getCitiesSuccessListener, final Response.ErrorListener getCitiesErrorListener) {
        try {
            CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(false, context, Request.Method.GET, "", null, response -> {
                if (response != null) {
                    CitiesResponse citiesResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), CitiesResponse.class);
                    getCitiesSuccessListener.onResponse(citiesResponse);
                } else
                    getCitiesErrorListener.onErrorResponse(new VolleyError());
            }, getCitiesErrorListener);
            customJsonObjectRequest.setTag(Tag.CITIES);
            ServicesQueue.getInstance(context).getRequestQueue().add(customJsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            getCitiesErrorListener.onErrorResponse(new VolleyError());
        }
    }

    public void getAreas(Context context, int cityId, final Response.Listener<AreasResponse> getAreasResponseListener, final Response.ErrorListener getAreasErrorListener) {
        try {
            CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(false, context, Request.Method.GET, "", null, response -> {
                if (response != null) {
                    AreasResponse areasResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), AreasResponse.class);
                    getAreasResponseListener.onResponse(areasResponse);
                } else
                    getAreasErrorListener.onErrorResponse(new VolleyError());
            }, getAreasErrorListener);
            customJsonObjectRequest.setTag(Tag.AREAS);
            ServicesQueue.getInstance(context).getRequestQueue().add(customJsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            getAreasErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //================================ Register =================================
    public void register(Context context, RegisterRequest registerRequest, final Response.Listener<RegisterResponse> registerSuccessListener, final Response.ErrorListener registerErrorListener) {
        try {
            String registerJson = GsonWrapper.getInstance().getGson().toJson(registerRequest, RegisterRequest.class);
            CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(false, context, Request.Method.POST, REGISTER_URL, new JSONObject(registerJson), response -> {
                if (response != null) {
                    RegisterResponse registerResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), RegisterResponse.class);
                    registerSuccessListener.onResponse(registerResponse);
                } else
                    registerErrorListener.onErrorResponse(new VolleyError());
            }, registerErrorListener);
            customJsonObjectRequest.setTag(Tag.REGISTER);
            ServicesQueue.getInstance(context).getRequestQueue().add(customJsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            registerErrorListener.onErrorResponse(new VolleyError());
        }
    }
}
