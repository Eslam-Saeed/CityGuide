package com.mti.cityguide.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, HomeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initializeViews() {

    }

    @Override
    protected void setListeners() {

    }
}
