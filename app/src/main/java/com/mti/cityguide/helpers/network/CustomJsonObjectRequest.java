package com.mti.cityguide.helpers.network;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mti.cityguide.common.NoInternetActivity;
import com.mti.cityguide.helpers.ConnectionDetector;
import com.mti.cityguide.helpers.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eslam Mahmoud on 03/22/2019.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {
    private static final int TIMEOUT_MS = 10000;
    static final int LONG_TIMEOUT_MS = 120000;
    private static final int MAX_RETRIES = 0;
    private static final float BACKOFF_MULT = 0f;
    private Map<String, String> mHeaders;
    private Context context;

    CustomJsonObjectRequest(Context context, int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, ServicesHelper.Tag tag) {
        super(method, url, jsonRequest, listener, errorListener);
        if (ConnectionDetector.isConnected(context)) {
            init(context);
            this.setTag(tag);
            ServicesQueue.getInstance(context).getRequestQueue().add(this);
        } else {
            NoInternetActivity.startActivity(context);
            errorListener.onErrorResponse(null);
        }
    }

    private void init(Context context) {
        this.context = context;
        setRetryPolicy(new DefaultRetryPolicy(LONG_TIMEOUT_MS, MAX_RETRIES, BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        mHeaders = new HashMap<>();
        mHeaders.put(Constants.Localization.CONTENT_TYPE, Constants.Localization.CONTENT_TYPE_VALUE);
        return mHeaders;
    }


    void setLongTimeouts() {
        setRetryPolicy(new DefaultRetryPolicy(LONG_TIMEOUT_MS, MAX_RETRIES, BACKOFF_MULT));
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        Log.e("MY_ERROR", error.toString());
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        super.deliverResponse(response);
        Log.v("MY_RESPONSE", response.toString());
    }
}
