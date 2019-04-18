package com.mti.cityguide.helpers.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantHotelFilter implements Parcelable {
    private int cityId, areaId, countryId, categoryId, roomType; //1 for Single & 2 for Double
    private String priceLow, priceHigh;
    private String search, recommended, sortAZ;

    public RestaurantHotelFilter(int countryId, int cityId, int areaId) {
        this.cityId = cityId;
        this.areaId = areaId;
        this.countryId = countryId;
        this.categoryId = -1;
        this.priceLow = "";
        this.priceHigh = "";
        this.search = "";
        this.recommended = "";
        this.sortAZ = "";
        this.roomType = -1;
    }

    public RestaurantHotelFilter() {
    }

    protected RestaurantHotelFilter(Parcel in) {
        cityId = in.readInt();
        areaId = in.readInt();
        countryId = in.readInt();
        categoryId = in.readInt();
        roomType = in.readInt();
        priceLow = in.readString();
        priceHigh = in.readString();
        search = in.readString();
        recommended = in.readString();
        sortAZ = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cityId);
        dest.writeInt(areaId);
        dest.writeInt(countryId);
        dest.writeInt(categoryId);
        dest.writeInt(roomType);
        dest.writeString(priceLow);
        dest.writeString(priceHigh);
        dest.writeString(search);
        dest.writeString(recommended);
        dest.writeString(sortAZ);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantHotelFilter> CREATOR = new Creator<RestaurantHotelFilter>() {
        @Override
        public RestaurantHotelFilter createFromParcel(Parcel in) {
            return new RestaurantHotelFilter(in);
        }

        @Override
        public RestaurantHotelFilter[] newArray(int size) {
            return new RestaurantHotelFilter[size];
        }
    };

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getSortAZ() {
        return sortAZ;
    }

    public void setSortAZ(String sortAZ) {
        this.sortAZ = sortAZ;
    }

    public String getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(String priceLow) {
        this.priceLow = priceLow;
    }

    public String getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(String priceHigh) {
        this.priceHigh = priceHigh;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }
}
