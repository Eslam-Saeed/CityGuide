package com.mti.cityguide.helpers.network;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;


/**
 * Created by Eslam on 03/22/2019.
 */

public class ServicesQueue {

    private static ServicesQueue mInstance;
    private RequestQueue requestQueue;
    private OkHttpClient okHttpClient;

    private ServicesQueue(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        okHttpClient = new OkHttpClient()/*.setProtocols(Collections.singletonList(Protocol.HTTP_1_1))*/;
        okHttpClient.setConnectTimeout(30, TimeUnit.MINUTES); // connect timeout
        okHttpClient.setReadTimeout(30, TimeUnit.MINUTES);
    }

    public static synchronized ServicesQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ServicesQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
