package com.mti.cityguide.home;

import android.content.Context;

import com.mti.cityguide.base.BasePresenter;

public class HomePresenter extends BasePresenter {
    private Context context;
    private HomeView view;
    private int type;

    public HomePresenter(Context context, HomeView view, int type) {
        this.context = context;
        this.view = view;
        this.type = type;
    }
}
