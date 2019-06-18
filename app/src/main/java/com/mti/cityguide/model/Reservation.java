package com.mti.cityguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reservation implements Parcelable {
    @SerializedName("reservation_id")
    @Expose
    private int id;
    @SerializedName("reservation_user_id")
    private int userId;
    @SerializedName("reservation_hotel_id")
    private int hotelId;
    @SerializedName("reservation_room_type")
    private int roomType;
    @SerializedName("reservation_person_count")
    private int personsCount;
    @SerializedName("reservation_days_count")
    private int daysCount;
    @SerializedName("reservation_avg_price")
    private int avgPrice;
    @SerializedName("hotel_name")
    private String hotelTitle;
    @SerializedName("reservation_start_at")
    private String startAt;
    @SerializedName("reservation_end_at")
    private String endAt;
    @SerializedName("reservation_created_at")
    private String createdAt;

    public Reservation() {
    }


    protected Reservation(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        hotelId = in.readInt();
        roomType = in.readInt();
        personsCount = in.readInt();
        daysCount = in.readInt();
        avgPrice = in.readInt();
        hotelTitle = in.readString();
        startAt = in.readString();
        endAt = in.readString();
        createdAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeInt(hotelId);
        dest.writeInt(roomType);
        dest.writeInt(personsCount);
        dest.writeInt(daysCount);
        dest.writeInt(avgPrice);
        dest.writeString(hotelTitle);
        dest.writeString(startAt);
        dest.writeString(endAt);
        dest.writeString(createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getPersonsCount() {
        return personsCount;
    }

    public void setPersonsCount(int personsCount) {
        this.personsCount = personsCount;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getHotelTitle() {
        return hotelTitle;
    }

    public void setHotelTitle(String hotelTitle) {
        this.hotelTitle = hotelTitle;
    }
}
