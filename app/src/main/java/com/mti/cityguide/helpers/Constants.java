package com.mti.cityguide.helpers;

/**
 * Created by Eslam on 03/22/2019.
 */

public class Constants {


    public class GeneralKeys {
        public static final int TIME_OUT_VALUE = 30000;
        public static final int DEFAULT_PAGE_NUMBER = 1;
        public static final int DEFAULT_PAGE_SIZE = 5;
        public static final int ALL = 0;
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
    }

    public class BundleKeys {
        public static final String TYPE = "type";
        public static final String CITY_ID = "city_id";
        public static final String AREA_ID = "area_id";
        public static final String COUNTRY_ID = "country_id";
        public static final String RESTAURANT_FILTER = "restaurant_filter";
        public static final String IS_HOTEL = "is_hotel";
        public static final String RESTAURANT_ID = "restaurant_id";
        public static final String RESTAURANT = "restaurant";
    }

    public class Localization {
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String CONTENT_TYPE_VALUE = "application/json; charset=utf-8";
    }

    public class Types {
        public static final String MALE = "male";
        public static final String FEMALE = "female";
        public static final int HOTELS = 0;
        public static final int RESTAURANT = 1;
    }

    public class FragmentTags {
        public static final String LOGIN = "login";
        public static final String REGISTER = "register";
        public static final String HOTELS = "hotels";
        public static final String RESTAURANTS = "restaurants";
        public static final String RESTAURANT_DETAILS = "restaurant_details";
    }

    public class User {
        public static final String ID = "id";
        public static final String EMAIL = "emial";
        public static final String PASSWORD = "password";
        public static final String NAME = "full_name";
        public static final String PHONE = "phone";
        public static final String COUNTRY = "coutry";
    }

    public class RequestCodes {

        public static final int RESTAURANT_CODE = 1;
    }
}
