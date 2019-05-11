package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.Menu;

import java.util.ArrayList;

public class MenuResponse extends ApiResponse {
    @SerializedName("result")
    private ArrayList<Menu> listMenus;

    public ArrayList<Menu> getListMenus() {
        return listMenus;
    }

    public void setListMenus(ArrayList<Menu> listMenus) {
        this.listMenus = listMenus;
    }
}
