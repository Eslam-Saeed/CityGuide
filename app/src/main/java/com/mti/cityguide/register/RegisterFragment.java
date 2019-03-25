package com.mti.cityguide.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.home.HomeActivity;

public class RegisterFragment extends BaseFragment implements RegisterView {
    private Context context;
    private RegisterPresenter presenter;

    private TextInputLayout tilName, tilEmail, tilPhoneNumber, tilPassword, tilConfPassword;
    private EditText edtName, edtEmail, edtPhoneNumber, edtPassword, edtConfPassword;
    private TextView btnRegister;
    private ProgressBar progressRegister;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        presenter = new RegisterPresenter(context, this);
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        progressRegister = v.findViewById(R.id.progressRegister);
        tilName = v.findViewById(R.id.tilName);
        edtName = v.findViewById(R.id.edtName);
        tilPhoneNumber = v.findViewById(R.id.tilPhone);
        edtPhoneNumber = v.findViewById(R.id.edtPhone);
        tilEmail = v.findViewById(R.id.tilEmail);
        edtEmail = v.findViewById(R.id.edtEmail);
        tilPassword = v.findViewById(R.id.tilPassword);
        edtPassword = v.findViewById(R.id.edtPassword);
        tilConfPassword = v.findViewById(R.id.tilConfPassword);
        edtConfPassword = v.findViewById(R.id.edtConfPassword);
        btnRegister = v.findViewById(R.id.btnRegister);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        btnRegister.setOnClickListener(v -> {
            updateData();
            if (presenter.validateUser(edtConfPassword.getText().toString())) {
                presenter.register();
            }
        });
    }

    @Override
    public void passEmpty() {
        tilPassword.setErrorEnabled(true);
        tilPassword.setError(getString(R.string.password_cant_empty));
    }

    private void updateData() {
        presenter.getUser().setPhone(edtPhoneNumber.getText().toString());
        presenter.getUser().setEmail(edtEmail.getText().toString());
        presenter.getUser().setName(edtName.getText().toString());
        presenter.getUser().setPassword(edtPassword.getText().toString());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressRegister.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showEmailPhoneWarning(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setNegativeButton(R.string.ok,
                (dialog, which) -> dialog.dismiss()).create().show();
    }

    @Override
    public void enableViews(boolean enable) {
        btnRegister.setEnabled(enable);
    }

    @Override
    public void hidePassError() {
        tilPassword.setError("");
        tilPassword.setErrorEnabled(false);
    }

    @Override
    public void onUserRegisteredSuccess() {
        HomeActivity.startActivity(context);
        getActivity().finish();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
