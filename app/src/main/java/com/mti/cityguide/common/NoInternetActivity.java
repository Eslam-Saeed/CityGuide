package com.mti.cityguide.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;

public class NoInternetActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, NoInternetActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }

    @Override
    protected void initializeViews() {

    }

    @Override
    protected void setListeners() {

    }
}
