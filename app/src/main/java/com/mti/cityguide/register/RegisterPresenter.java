package com.mti.cityguide.register;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.LoginResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.User;

public class RegisterPresenter extends BasePresenter {
    private Context context;
    private RegisterView view;
    private User user;


    public RegisterPresenter(Context context, RegisterView view) {
        this.context = context;
        this.view = view;
        this.user = new User();
    }


    public User getUser() {
        return user;
    }

    boolean validateUser(String confPassword) {
        if (TextUtils.isEmpty(user.getEmail()) && TextUtils.isEmpty(user.getPhone())) {
            view.showEmailPhoneWarning(context.getString(R.string.mail_phone_warning));
            return false;
        }

        if (TextUtils.isEmpty(user.getPassword())) {
            view.passEmpty();
            return false;
        } else {
            view.hidePassError();
        }

        if (!confPassword.equals(user.getPassword())) {
            view.showEmailPhoneWarning(context.getString(R.string.conf_pass_must_equal_pass));
            return false;
        }
        return true;
    }

    public void register() {
        view.showProgress(true);
        view.enableViews(false);
        ServicesHelper.getInstance(context).register(context, user, registerSuccessListener, getErrorSuccessListener);
    }

    private Response.Listener<LoginResponse> registerSuccessListener = new Response.Listener<LoginResponse>() {
        @Override
        public void onResponse(LoginResponse response) {
            view.showProgress(false);
            view.enableViews(true);
            if (response != null) {
                User.initUser(context, response.getUser());
                view.showErrorMessage(context.getString(R.string.registered_successfully));
                view.onUserRegisteredSuccess();
            } else
                view.showErrorMessage(context.getString(R.string.somethingWrong));
        }
    };

    private Response.ErrorListener getErrorSuccessListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            view.showProgress(false);
            view.enableViews(true);
            if (VolleyErrorHandler.isServerProblem(error))
                view.showEmailPhoneWarning(context.getString(R.string.phone_or_mail_exists));
            else
                view.showErrorMessage(VolleyErrorHandler.getErrorMessage(context, error));
        }
    };
}
