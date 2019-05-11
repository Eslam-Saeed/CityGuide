package com.mti.cityguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Restaurant implements Parcelable {
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("city_name")
    private String cityName;
    @SerializedName("city_code")
    private String cityCode;
    @SerializedName("city_country_id")
    private String cityCountryId;
    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_name")
    private String areaName;
    @SerializedName("area_city_id")
    private String areaCityId;
    @SerializedName("restaurant_id")
    private String restaurantId;
    @SerializedName("restaurant_name")
    private String restaurantName;
    @SerializedName("restaurant_description")
    private String restaurantDescription;
    @SerializedName("restaurant_img_url")
    private String restaurantImgUrl;
    @SerializedName("restaurant_lat")
    private Double restaurantLat;
    @SerializedName("restaurant_lng")
    private Double restaurantLng;
    @SerializedName("restaurant_area_id")
    private String restaurantAreaId;
    @SerializedName("restaurant_rate")
    private float restaurantRate;
    @SerializedName("restaurant_cat_id")
    private String restaurantCatId;
    @SerializedName("restaurant_recommended")
    private int restaurantRecommended;
    @SerializedName("restaurant_menu_id")
    private String restaurantMenuId;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("category_code")
    private String categoryCode;

    protected Restaurant(Parcel in) {
        cityId = in.readString();
        cityName = in.readString();
        cityCode = in.readString();
        cityCountryId = in.readString();
        areaId = in.readString();
        areaName = in.readString();
        areaCityId = in.readString();
        restaurantId = in.readString();
        restaurantName = in.readString();
        restaurantDescription = in.readString();
        restaurantImgUrl = in.readString();
        if (in.readByte() == 0) {
            restaurantLat = null;
        } else {
            restaurantLat = in.readDouble();
        }
        if (in.readByte() == 0) {
            restaurantLng = null;
        } else {
            restaurantLng = in.readDouble();
        }
        restaurantAreaId = in.readString();
        restaurantRate = in.readFloat();
        restaurantCatId = in.readString();
        restaurantRecommended = in.readInt();
        restaurantMenuId = in.readString();
        categoryId = in.readString();
        categoryName = in.readString();
        categoryCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityId);
        dest.writeString(cityName);
        dest.writeString(cityCode);
        dest.writeString(cityCountryId);
        dest.writeString(areaId);
        dest.writeString(areaName);
        dest.writeString(areaCityId);
        dest.writeString(restaurantId);
        dest.writeString(restaurantName);
        dest.writeString(restaurantDescription);
        dest.writeString(restaurantImgUrl);
        if (restaurantLat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(restaurantLat);
        }
        if (restaurantLng == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(restaurantLng);
        }
        dest.writeString(restaurantAreaId);
        dest.writeFloat(restaurantRate);
        dest.writeString(restaurantCatId);
        dest.writeInt(restaurantRecommended);
        dest.writeString(restaurantMenuId);
        dest.writeString(categoryId);
        dest.writeString(categoryName);
        dest.writeString(categoryCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCountryId() {
        return cityCountryId;
    }

    public void setCityCountryId(String cityCountryId) {
        this.cityCountryId = cityCountryId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(String areaCityId) {
        this.areaCityId = areaCityId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantImgUrl() {
        return restaurantImgUrl;
    }

    public void setRestaurantImgUrl(String restaurantImgUrl) {
        this.restaurantImgUrl = restaurantImgUrl;
    }

    public Double getRestaurantLat() {
        return restaurantLat;
    }

    public void setRestaurantLat(Double restaurantLat) {
        this.restaurantLat = restaurantLat;
    }

    public Double getRestaurantLng() {
        return restaurantLng;
    }

    public void setRestaurantLng(Double restaurantLng) {
        this.restaurantLng = restaurantLng;
    }

    public String getRestaurantAreaId() {
        return restaurantAreaId;
    }

    public void setRestaurantAreaId(String restaurantAreaId) {
        this.restaurantAreaId = restaurantAreaId;
    }

    public float getRestaurantRate() {
        return restaurantRate;
    }

    public void setRestaurantRate(float restaurantRate) {
        this.restaurantRate = restaurantRate;
    }

    public String getRestaurantCatId() {
        return restaurantCatId;
    }

    public void setRestaurantCatId(String restaurantCatId) {
        this.restaurantCatId = restaurantCatId;
    }

    public int getRestaurantRecommended() {
        return restaurantRecommended;
    }

    public void setRestaurantRecommended(int restaurantRecommended) {
        this.restaurantRecommended = restaurantRecommended;
    }

    public String getRestaurantMenuId() {
        return restaurantMenuId;
    }

    public void setRestaurantMenuId(String restaurantMenuId) {
        this.restaurantMenuId = restaurantMenuId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
