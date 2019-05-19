package com.mti.cityguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu implements Parcelable {
    @SerializedName("menu_restaurant_id")
    @Expose
    private String menuRestaurantId;
    @SerializedName("menu_dish_id")
    @Expose
    private String menuDishId;
    @SerializedName("menu_dish_price")
    @Expose
    private String menuDishPrice;
    @SerializedName("dish_id")
    @Expose
    private String dishId;
    @SerializedName("dish_name")
    @Expose
    private String dishName;
    @SerializedName("dish_type_id")
    @Expose
    private int dishTypeId;
    @SerializedName("gradient_id")
    @Expose
    private String gradientId;
    @SerializedName("gradient_name")
    @Expose
    private String gradientName;
    @SerializedName("gradient_description")
    @Expose
    private String gradientDescription;
    @SerializedName("gradient_img_url")
    @Expose
    private String gradientImgUrl;
    @SerializedName("gradient_dish_id")
    @Expose
    private String gradientDishId;
    @SerializedName("dish_type_name")
    @Expose
    private String dishTypeName;

    protected Menu(Parcel in) {
        menuRestaurantId = in.readString();
        menuDishId = in.readString();
        menuDishPrice = in.readString();
        dishId = in.readString();
        dishName = in.readString();
        dishTypeId = in.readInt();
        gradientId = in.readString();
        gradientName = in.readString();
        gradientDescription = in.readString();
        gradientImgUrl = in.readString();
        gradientDishId = in.readString();
        dishTypeName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuRestaurantId);
        dest.writeString(menuDishId);
        dest.writeString(menuDishPrice);
        dest.writeString(dishId);
        dest.writeString(dishName);
        dest.writeInt(dishTypeId);
        dest.writeString(gradientId);
        dest.writeString(gradientName);
        dest.writeString(gradientDescription);
        dest.writeString(gradientImgUrl);
        dest.writeString(gradientDishId);
        dest.writeString(dishTypeName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getMenuRestaurantId() {
        return menuRestaurantId;
    }

    public void setMenuRestaurantId(String menuRestaurantId) {
        this.menuRestaurantId = menuRestaurantId;
    }

    public String getMenuDishId() {
        return menuDishId;
    }

    public void setMenuDishId(String menuDishId) {
        this.menuDishId = menuDishId;
    }

    public String getMenuDishPrice() {
        return menuDishPrice;
    }

    public void setMenuDishPrice(String menuDishPrice) {
        this.menuDishPrice = menuDishPrice;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(int dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public String getGradientId() {
        return gradientId;
    }

    public void setGradientId(String gradientId) {
        this.gradientId = gradientId;
    }

    public String getGradientName() {
        return gradientName;
    }

    public void setGradientName(String gradientName) {
        this.gradientName = gradientName;
    }

    public String getGradientDescription() {
        return gradientDescription;
    }

    public void setGradientDescription(String gradientDescription) {
        this.gradientDescription = gradientDescription;
    }

    public String getGradientImgUrl() {
        return gradientImgUrl;
    }

    public void setGradientImgUrl(String gradientImgUrl) {
        this.gradientImgUrl = gradientImgUrl;
    }

    public String getGradientDishId() {
        return gradientDishId;
    }

    public void setGradientDishId(String gradientDishId) {
        this.gradientDishId = gradientDishId;
    }

    public String getDishTypeName() {
        return dishTypeName;
    }

    public void setDishTypeName(String dishTypeName) {
        this.dishTypeName = dishTypeName;
    }
}
