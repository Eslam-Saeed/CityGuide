package com.mti.cityguide.login;

import com.mti.cityguide.base.BaseView;

public interface LoginView extends BaseView {
    void enableViews(boolean enable);

    void navigateToRegisterActivity();

    void showError(boolean isPhoneEmpty, boolean isPasswordEmpty);

    void showErrorMessage(String errorMessage);

    void onUserLoggedInSuccess();

    void navigateToLoginActivity();
}
