package com.mti.cityguide.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.home.HomeActivity;
import com.mti.cityguide.register.RegisterActivity;
import com.mti.cityguide.base.BaseFragment;

public class LoginFragment extends BaseFragment implements LoginView {
    private Context context;
    private LoginPresenter presenter;

    private TextInputLayout tilPhoneNumber, tilPassword;
    private EditText edtPhoneNumber, edtPassword;
    private TextView btnLogin, btnRegister;
    private ProgressBar progressLogin;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        presenter = new LoginPresenter(context, this);
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        progressLogin = v.findViewById(R.id.progressLogin);
        tilPhoneNumber = v.findViewById(R.id.tilPhone);
        edtPhoneNumber = v.findViewById(R.id.edtPhone);
        tilPassword = v.findViewById(R.id.tilPassword);
        edtPassword = v.findViewById(R.id.edtPassword);
        btnLogin = v.findViewById(R.id.btnLogin);
        btnRegister = v.findViewById(R.id.btnRegister);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        btnLogin.setOnClickListener(v -> presenter.validateAndLogin(edtPhoneNumber.getText().toString(), edtPassword.getText().toString()));
        btnRegister.setOnClickListener(v -> navigateToRegisterActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressLogin.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void enableViews(boolean enable) {
        btnLogin.setEnabled(enable);
        btnRegister.setEnabled(enable);
    }

    @Override
    public void navigateToRegisterActivity() {
        RegisterActivity.startActivity(context);
    }

    @Override
    public void showError(boolean isPhoneEmpty, boolean isPasswordEmpty) {
        tilPhoneNumber.setErrorEnabled(isPhoneEmpty);
        tilPhoneNumber.setError(isPhoneEmpty ? getString(R.string.phone_cant_empty) : "");
        tilPassword.setErrorEnabled(isPasswordEmpty);
        tilPassword.setError(isPasswordEmpty ? getString(R.string.password_cant_empty) : "");
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserLoggedInSuccess() {
        HomeActivity.startActivity(context);
        getActivity().finish();
    }

    @Override
    public void navigateToLoginActivity() {

    }
}
