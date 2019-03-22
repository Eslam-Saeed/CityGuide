package com.mti.cityguide.helpers.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Ess on 03/22/2019.
 */

public class GsonWrapper {
    private static GsonWrapper sharedInstance;

    private Gson gson;

    private GsonWrapper() {
        gson = new GsonBuilder().create();
    }

    public synchronized static GsonWrapper getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new GsonWrapper();
        }
        return sharedInstance;
    }

    public Gson getGson() {
        return gson;
    }
}
