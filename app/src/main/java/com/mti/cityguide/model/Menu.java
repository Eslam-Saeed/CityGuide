package com.mti.cityguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

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
    @SerializedName("dish_type_name")
    @Expose
    private String dishTypeName;

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

    public String getDishTypeName() {
        return dishTypeName;
    }

    public void setDishTypeName(String dishTypeName) {
        this.dishTypeName = dishTypeName;
    }
}
