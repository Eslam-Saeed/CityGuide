package com.mti.cityguide.register;

import com.mti.cityguide.base.BaseView;

public interface RegisterView extends BaseView {
    void showEmailPhoneWarning(String message);

    void passEmpty();

    void hidePassError();

    void enableViews(boolean enable);

    void showErrorMessage(String errorMessage);

    void onUserRegisteredSuccess();
}
