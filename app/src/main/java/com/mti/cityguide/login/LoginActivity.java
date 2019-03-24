package com.mti.cityguide.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;

public class LoginActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(starter);
        ((Activity) context).finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_toolbar);
        initializeViews();
        loadFragment();
    }

    private void loadFragment() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(Constants.FragmentTags.LOGIN);
        replaceFragment(fragment == null ? LoginFragment.newInstance() : fragment, R.id.frameContainer, Constants.FragmentTags.LOGIN, false);
    }

    @Override
    protected void initializeViews() {
    }

    @Override
    protected void setListeners() {

    }
}
