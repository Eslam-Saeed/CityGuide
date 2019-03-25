package com.mti.cityguide.register;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BasePresenter;
import com.mti.cityguide.helpers.DTO.CountryResponse;
import com.mti.cityguide.helpers.DTO.LoginResponse;
import com.mti.cityguide.helpers.network.ServicesHelper;
import com.mti.cityguide.helpers.network.VolleyErrorHandler;
import com.mti.cityguide.model.Country;
import com.mti.cityguide.model.User;

import java.util.ArrayList;

public class RegisterPresenter extends BasePresenter {
    private Context context;
    private RegisterView view;
    private User user;
    private ArrayList<Country> listCountries;
    private int selectedPosition = -1;


    RegisterPresenter(Context context, RegisterView view) {
        this.context = context;
        this.view = view;
        this.user = new User();
        this.listCountries = new ArrayList<>();
        getListCountries();
    }

    private void getListCountries() {
        ServicesHelper.getInstance(context).getCountries(context, getCountriesSuccessListener, error -> {
        });
    }

    private Response.Listener<CountryResponse> getCountriesSuccessListener = new Response.Listener<CountryResponse>() {
        @Override
        public void onResponse(CountryResponse response) {
            if (response.getListCountries() != null && !response.getListCountries().isEmpty()) {
                listCountries.addAll(response.getListCountries());
            }
        }
    };

    User getUser() {
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

        if (selectedPosition == -1) {
            view.showCountryError(true);
        } else {
            view.showCountryError(false);
            user.setCountry(listCountries.get(selectedPosition).getId());
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

    ArrayList<String> getListCountry() {
        ArrayList<String> countries = new ArrayList<>();
        for (int i = 0; i < listCountries.size(); i++) {
            countries.add(listCountries.get(i).getName());
        }
        return countries;
    }

    void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
