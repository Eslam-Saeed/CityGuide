package com.mti.cityguide.model;

import com.google.gson.annotations.SerializedName;

public class Hotel {
    @SerializedName("hotel_id")
    private String hotelId;
    @SerializedName("hotel_recommended")
    private int recommended;
    @SerializedName("hotel_avg_price")
    private int avgPrice;
    @SerializedName("hotel_name")
    private String hotelName;
    @SerializedName("hotel_description")
    private String hotelDescription;
    @SerializedName("hotel_img_url")
    private String hotelImgUrl;
    @SerializedName("hotel_rating")
    private Float hotelRating;
    @SerializedName("hotel_lat")
    private Double hotelLat;
    @SerializedName("hotel_lng")
    private Double hotelLng;
    @SerializedName("hotel_area_id")
    private String hotelAreaId;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelImgUrl() {
        return hotelImgUrl;
    }

    public void setHotelImgUrl(String hotelImgUrl) {
        this.hotelImgUrl = hotelImgUrl;
    }

    public Double getHotelLat() {
        return hotelLat;
    }

    public void setHotelLat(Double hotelLat) {
        this.hotelLat = hotelLat;
    }

    public Double getHotelLng() {
        return hotelLng;
    }

    public void setHotelLng(Double hotelLng) {
        this.hotelLng = hotelLng;
    }

    public String getHotelAreaId() {
        return hotelAreaId;
    }

    public void setHotelAreaId(String hotelAreaId) {
        this.hotelAreaId = hotelAreaId;
    }

    public Float getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(Float hotelRating) {
        this.hotelRating = hotelRating;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }
}
