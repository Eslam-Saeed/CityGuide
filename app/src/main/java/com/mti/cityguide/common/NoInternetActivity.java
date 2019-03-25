package com.mti.cityguide.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;

public class NoInternetActivity extends BaseActivity {

    private TextView btnGoBack;

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, NoInternetActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        initializeViews();
        setListeners();
    }

    @Override
    protected void initializeViews() {
        btnGoBack = findViewById(R.id.btnGoBack);
    }

    @Override
    protected void setListeners() {
        btnGoBack.setOnClickListener(v -> onBackPressed());
    }
}
