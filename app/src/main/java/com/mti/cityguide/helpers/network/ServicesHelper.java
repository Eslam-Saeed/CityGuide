package com.mti.cityguide.helpers.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mti.cityguide.helpers.DTO.ApiResponse;
import com.mti.cityguide.helpers.DTO.AreaResponse;
import com.mti.cityguide.helpers.DTO.CityResponse;
import com.mti.cityguide.helpers.DTO.CountryResponse;
import com.mti.cityguide.helpers.DTO.HotelResponse;
import com.mti.cityguide.helpers.DTO.LoginRequest;
import com.mti.cityguide.helpers.DTO.LoginResponse;
import com.mti.cityguide.helpers.DTO.MenuResponse;
import com.mti.cityguide.helpers.DTO.ReservationResponse;
import com.mti.cityguide.helpers.DTO.RestaurantCategoriesResponse;
import com.mti.cityguide.helpers.DTO.RestaurantHotelFilter;
import com.mti.cityguide.helpers.DTO.RestaurantResponse;
import com.mti.cityguide.model.Reservation;
import com.mti.cityguide.model.User;

import org.json.JSONObject;


public class ServicesHelper {

    private static ServicesHelper mInstance;
    private RequestQueue mRequestQueue;

    //    private final String BASE_URL = "https://morethink2.000webhostapp.com/cityguide/index.php/api/";
    private final String BASE_URL = "http://192.168.1.7/cityguide/index.php/api/";
    private final String FAIL_CODE = "failed_fail";
    private final String LOGIN_URL = BASE_URL + "user/login";
    private final String REGISTER_URL = BASE_URL + "user/adduser";
    private final String COUNTRIES_URL = BASE_URL + "country/getallcountries";
    private final String CITIES_URL = BASE_URL + "city/getcitiesbycountry?country_id=";
    private final String AREAS_URL = BASE_URL + "area/getareasbycity?city_id=";
    private final String GET_HOTELS = BASE_URL + "hotel/gethotels?country_id=";
    private final String GET_RESTAURANTS = BASE_URL + "restaurant/getrestaurants?country_id=";
    private final String GET_RESTAURANTS_CATEGORIES = BASE_URL + "restaurantCategory/getrestaurantcategories";
    private final String GET_MENU = BASE_URL + "menu/getmenus?restaurant_id=";
    private final String BOOK_URL = BASE_URL + "reservation/addreservation";
    private final String GET_RESERVATIONS_URL = BASE_URL + "reservtion/addreservation?id=";

    public enum Tag {
        LOGIN,
        REGISTER, COUNTRIES, CITIES, AREAS, HOTELS, RESTAURANTS, RESTAURANTS_CATEGORIES, GET_MENU, Book, GET_RESERVATIONS
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

    public void getCities(Context context, int countryId, final Response.Listener<CityResponse> getCitiesSuccessListener, final Response.ErrorListener getCitiesErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, CITIES_URL + countryId, null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    CityResponse cityResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), CityResponse.class);
                    getCitiesSuccessListener.onResponse(cityResponse);
                } else
                    getCitiesErrorListener.onErrorResponse(new VolleyError());
            }, getCitiesErrorListener, Tag.CITIES);
        } catch (Exception e) {
            e.printStackTrace();
            getCitiesErrorListener.onErrorResponse(new VolleyError());
        }
    }

    public void getAreas(Context context, int cityId, final Response.Listener<AreaResponse> getAreasResponseListener, final Response.ErrorListener getAreasErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, AREAS_URL + cityId, null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    AreaResponse areaResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), AreaResponse.class);
                    getAreasResponseListener.onResponse(areaResponse);
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

    //=============================== Hotels ====================================
    public void getHotels(Context context, RestaurantHotelFilter restaurantHotelFilter, final Response.Listener<HotelResponse> getHotelsSuccessListener, final Response.ErrorListener getHotelsErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, getFilterUrl(restaurantHotelFilter, GET_HOTELS), null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    HotelResponse hotelResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), HotelResponse.class);
                    getHotelsSuccessListener.onResponse(hotelResponse);
                } else
                    getHotelsErrorListener.onErrorResponse(new VolleyError());
            }, getHotelsErrorListener, Tag.HOTELS);
        } catch (Exception e) {
            e.printStackTrace();
            getHotelsErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //=============================== Restaurants ====================================
    public void getRestaurants(Context context, RestaurantHotelFilter restaurantHotelFilter, final Response.Listener<RestaurantResponse> getRestaurantsSuccessListener, final Response.ErrorListener getRestaurantsErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, getFilterUrl(restaurantHotelFilter, GET_RESTAURANTS), null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    RestaurantResponse restaurantResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), RestaurantResponse.class);
                    getRestaurantsSuccessListener.onResponse(restaurantResponse);
                } else
                    getRestaurantsErrorListener.onErrorResponse(new VolleyError());
            }, getRestaurantsErrorListener, Tag.RESTAURANTS);
        } catch (Exception e) {
            e.printStackTrace();
            getRestaurantsErrorListener.onErrorResponse(new VolleyError());
        }
    }

    private String getFilterUrl(RestaurantHotelFilter restaurantHotelFilter, String baseURL) {
        String URL;
        URL = baseURL + restaurantHotelFilter.getCountryId() + "&city_id=" +
                restaurantHotelFilter.getCityId() + "&area_id=" + restaurantHotelFilter.getAreaId();
        URL = URL.concat("&search=").concat(restaurantHotelFilter.getSearch());

        if (!TextUtils.isEmpty(restaurantHotelFilter.getRecommended()))
            URL = URL.concat("&recommended=").concat(restaurantHotelFilter.getRecommended());

        if (!TextUtils.isEmpty(restaurantHotelFilter.getRecommended()))
            URL = URL.concat("&a_z=").concat(restaurantHotelFilter.getSortAZ());

        if (restaurantHotelFilter.getCategoryId() != -1)
            URL = URL.concat("&category_id=").concat(String.valueOf(restaurantHotelFilter.getCategoryId()));

        if (!TextUtils.isEmpty(restaurantHotelFilter.getPriceLow()))
            URL = URL.concat("&priceLow=").concat(restaurantHotelFilter.getPriceLow());

        if (!TextUtils.isEmpty(restaurantHotelFilter.getPriceHigh()))
            URL = URL.concat("&priceHigh=").concat(restaurantHotelFilter.getPriceHigh());

        if (restaurantHotelFilter.getRoomType() != -1)
            URL = URL.concat("&room_type=").concat(String.valueOf(restaurantHotelFilter.getRoomType()));

        return URL;
    }
    //============================= Restaurants Categories ===========================

    public void getRestaurantsCategories(Context context, final Response.Listener<RestaurantCategoriesResponse> getRestaurantCategoriesSuccessListener, final Response.ErrorListener getRestaurantCategoriesErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, GET_RESTAURANTS_CATEGORIES, null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    RestaurantCategoriesResponse countryResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), RestaurantCategoriesResponse.class);
                    getRestaurantCategoriesSuccessListener.onResponse(countryResponse);
                } else
                    getRestaurantCategoriesErrorListener.onErrorResponse(new VolleyError());
            }, getRestaurantCategoriesErrorListener, Tag.RESTAURANTS_CATEGORIES);
        } catch (Exception e) {
            e.printStackTrace();
            getRestaurantCategoriesErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //=============================== Restaurants ====================================
    public void getMenus(Context context, int restaurantId, final Response.Listener<MenuResponse> getMenuSuccessListener, final Response.ErrorListener getMenuErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, GET_MENU + restaurantId, null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    MenuResponse restaurantResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), MenuResponse.class);
                    getMenuSuccessListener.onResponse(restaurantResponse);
                } else
                    getMenuErrorListener.onErrorResponse(new VolleyError());
            }, getMenuErrorListener, Tag.GET_MENU);
        } catch (Exception e) {
            e.printStackTrace();
            getMenuErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //===================================================================
    public void book(Context context, Reservation reservation, Response.Listener<ApiResponse> bookSuccessListener, Response.ErrorListener bookErrorListener) {
        try {
            String reservationJson = GsonWrapper.getInstance().getGson().toJson(reservation, Reservation.class);
            new CustomJsonObjectRequest(context, Request.Method.POST, BOOK_URL, new JSONObject(reservationJson), response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    ApiResponse apiResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), ApiResponse.class);
                    bookSuccessListener.onResponse(apiResponse);
                } else
                    bookErrorListener.onErrorResponse(new VolleyError());
            }, bookErrorListener, Tag.Book);
        } catch (Exception e) {
            e.printStackTrace();
            bookErrorListener.onErrorResponse(new VolleyError());
        }
    }

    //=============================== Get Reservations ====================================
    public void getReservations(Context context, int userId, final Response.Listener<ReservationResponse> getReservationsSuccessListener, final Response.ErrorListener getReservationsErrorListener) {
        try {
            new CustomJsonObjectRequest(context, Request.Method.GET, GET_RESERVATIONS_URL + userId, null, response -> {
                if (response != null && !response.toString().contains(FAIL_CODE)) {
                    ReservationResponse reservationResponse = GsonWrapper.getInstance().getGson().fromJson(response.toString(), ReservationResponse.class);
                    getReservationsSuccessListener.onResponse(reservationResponse);
                } else
                    getReservationsErrorListener.onErrorResponse(new VolleyError());
            }, getReservationsErrorListener, Tag.HOTELS);
        } catch (Exception e) {
            e.printStackTrace();
            getReservationsErrorListener.onErrorResponse(new VolleyError());
        }
    }
}
