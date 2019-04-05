package com.mti.cityguide.helpers.DTO;

public class RestaurantFilter {
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
