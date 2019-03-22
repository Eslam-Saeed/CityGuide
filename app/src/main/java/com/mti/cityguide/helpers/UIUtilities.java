package com.mti.cityguide.helpers;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Eslam on 03/22/2019.
 */

public class UIUtilities {

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void changeSpinnerIconColor(Context context, Spinner spinner, int color) {
        spinner.getBackground().setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);

    }

    public static void displayText(TextView txtView, String text) {
        if (TextUtils.isEmpty(text))
            txtView.setText("");
        else txtView.setText(text);
    }

    public static void displayTextHide(TextView txtView, String text) {
        if (TextUtils.isEmpty(text))
            txtView.setVisibility(View.GONE);
        else {
            txtView.setVisibility(View.VISIBLE);
            txtView.setText(text);
        }
    }

    public static void displayHTMLTextHide(TextView txtView, String text) {
        if (TextUtils.isEmpty(text))
            txtView.setVisibility(View.GONE);
        else {
            txtView.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                txtView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
            else
                txtView.setText(Html.fromHtml(text));


        }
    }
}
