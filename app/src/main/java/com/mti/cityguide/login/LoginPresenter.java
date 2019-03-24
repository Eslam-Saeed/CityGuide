package com.mti.cityguide.login;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.LoginRequest;
import com.mti.cityguide.helpers.DTO.LoginResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.User;

public class LoginPresenter extends BasePresenter {
    private Context context;
    private LoginView view;
    private LoginRequest loginRequest;

    public LoginPresenter(Context context, LoginView view) {
        this.context = context;
        this.view = view;

    }

    public void validateAndLogin(String phone, String password) {
        if (TextUtils.isEmpty(phone) | TextUtils.isEmpty(password)) {
            view.showError(TextUtils.isEmpty(phone), TextUtils.isEmpty(password));
            return;
        }
        this.loginRequest = new LoginRequest(phone, password);
        callLoginService(loginRequest);
    }

    private void callLoginService(LoginRequest loginRequest) {
        view.showProgress(true);
        view.enableViews(false);
        ServicesHelper.getInstance(context).login(context, loginRequest, getLoginSuccessListener, getErrorSuccessListener);
    }

    private Response.Listener<LoginResponse> getLoginSuccessListener = new Response.Listener<LoginResponse>() {
        @Override
        public void onResponse(LoginResponse response) {
            view.showProgress(false);
            view.enableViews(true);
            if (response != null) {
                User.initUser(context, response.getUser());
                view.onUserLoggedInSuccess();
            } else
                view.showErrorMessage(context.getString(R.string.somethingWrong));
        }
    };

    private Response.ErrorListener getErrorSuccessListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            view.showProgress(false);
            view.enableViews(true);
            view.showErrorMessage(VolleyErrorHandler.getErrorMessage(context, error));
        }
    };

    public void checkLoggedInUser() {
        if (User.isLoggedInUser(context)) {
            User user = User.getLoggedInUser(context);

            loginRequest = new LoginRequest(!TextUtils.isEmpty(user.getPhone()) ? user.getPhone() :
                    user.getEmail(), user.getPassword());
            callLoginService(loginRequest);
        } else {
            view.navigateToLoginActivity();
        }
    }
}
