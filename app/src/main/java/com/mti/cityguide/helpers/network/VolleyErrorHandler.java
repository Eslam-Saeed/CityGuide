package com.mti.cityguide.helpers.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.mti.cityguide.R;

/**
 * Created by Eslam on 03/22/2019.
 */

public class VolleyErrorHandler {

    public static String getErrorMessage(Context context, Throwable error) {
        if (context == null) return "";
        if (error == null) return context.getString(R.string.somethingWrong);

        String errorMsg = context.getString(R.string.error_unknown_error_occurred);
        try {
            if (error instanceof VolleyError) {
                if (isResponseStateProblem(error)) {
                    errorMsg = error.getMessage();
                } else if (error instanceof TimeoutError) {
                    errorMsg = context.getString(R.string.error_timeout);
                } else if (isNetworkProblem(error)) {
                    errorMsg = context.getString(R.string.error_no_internet_connection);
                } else if (isServerProblem(error)) {
                    errorMsg = context.getString(R.string.error_server_error);
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return errorMsg;
    }

    // --------------------------------------------

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    private static boolean isResponseStateProblem(Object error) {
        return (error instanceof MessageError);
    }

// --------------->

    private static class MessageError extends VolleyError {
        private String exceptionMessage;

        public MessageError(String exceptionMessage) {
            super(exceptionMessage);
        }
    }

}
