package com.mti.cityguide.helpers;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Eslam on 03/22/2019.
 */

public class MyApplication extends Application {

    private static Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();
        mGson = new GsonBuilder().create();
    }

    public static Gson getmGson() {
        return mGson;
    }


    public static MyApplication getInstance(Context context) {
        return ((MyApplication) context.getApplicationContext());
    }

}
