package com.mti.cityguide.splash;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.home.HomeActivity;
import com.mti.cityguide.login.LoginActivity;
import com.mti.cityguide.login.LoginPresenter;
import com.mti.cityguide.login.LoginView;

public class SplashActivity extends BaseActivity implements LoginView {
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initializeViews();
        setListeners();
        new Handler().postDelayed(() -> {
            loginPresenter = new LoginPresenter(SplashActivity.this, SplashActivity.this);
            loginPresenter.checkLoggedInUser();
        }, 2000);

    }

    @Override
    protected void initializeViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void enableViews(boolean enable) {

    }

    @Override
    public void navigateToRegisterActivity() {

    }

    @Override
    public void showError(boolean isPhoneEmpty, boolean isPasswordEmpty) {
        HomeActivity.startActivity(this);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserLoggedInSuccess() {
        HomeActivity.startActivity(this);
    }

    @Override
    public void showProgress(boolean shouldShow) {

    }

    @Override
    public void navigateToLoginActivity() {
        LoginActivity.startActivity(this);
    }
}
