package com.mti.cityguide.helpers.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantFilter implements Parcelable {
    private int cityId, areaId, countryId, categoryId;
    private String search, recommended, sortAZ;

    public RestaurantFilter(int countryId, int cityId, int areaId) {
        this.cityId = cityId;
        this.areaId = areaId;
        this.countryId = countryId;
        this.categoryId = -1;
        this.search = "";
        this.recommended = "";
        this.sortAZ = "";
    }

    public RestaurantFilter() {
    }

    protected RestaurantFilter(Parcel in) {
        cityId = in.readInt();
        areaId = in.readInt();
        countryId = in.readInt();
        categoryId = in.readInt();
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
        dest.writeString(search);
        dest.writeString(recommended);
        dest.writeString(sortAZ);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantFilter> CREATOR = new Creator<RestaurantFilter>() {
        @Override
        public RestaurantFilter createFromParcel(Parcel in) {
            return new RestaurantFilter(in);
        }

        @Override
        public RestaurantFilter[] newArray(int size) {
            return new RestaurantFilter[size];
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
}
