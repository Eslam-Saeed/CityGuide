package com.mti.cityguide.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mti.cityguide.R;


public class Utilities {

    public static Spanned convertToHTML(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        else
            return Html.fromHtml(text);
    }

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void hideSoftKeyboard(Context context) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                ((Activity) context).getCurrentFocus().getWindowToken(), 0);
    }

    public static void openMap(Context context, Double lat, Double lng) {
        if (lat != null && lng != null) {
            Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lng);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        }
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static void openDial(Context context, String phoneNumber) {
        PackageManager packageManager = context.getPackageManager();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                "tel", phoneNumber, null));
        if (phoneIntent.resolveActivity(packageManager) != null) {
            context.startActivity(phoneIntent);
        } else {
            Log.d("", "No Intent available to handle action");
        }
    }

    public static void sendMail(Context mContext, String clientMail) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{clientMail});
        try {
            mContext.startActivity(emailIntent);
            Log.i("DONE", "Finished sending email");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openBrowserURL(Context context, String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }
    }

    public static void shareURL(Context context, String url) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.app_name)));
        } else {
            Toast.makeText(context, R.string.share_fail, Toast.LENGTH_LONG).show();
        }
    }
}
