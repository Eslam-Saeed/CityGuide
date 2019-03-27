package com.mti.cityguide.helpers.DTO;

import com.google.gson.annotations.SerializedName;
import com.mti.cityguide.model.Area;

import java.util.ArrayList;

public class AreaResponse extends ApiResponse{
    @SerializedName("result")
    private ArrayList<Area> listAreas;

    public ArrayList<Area> getListAreas() {
        return listAreas;
    }

    public void setListAreas(ArrayList<Area> listAreas) {
        this.listAreas = listAreas;
    }
}
