package com.mti.cityguide.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;

public class RegisterActivity extends BaseActivity {
    private Toolbar toolbar;

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();
        setToolbar(toolbar, getString(R.string.register), true, true);
        setToolbarNavigationIcon(toolbar, R.drawable.ic_close);
        loadFragment();
    }

    private void loadFragment() {
        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag(Constants.FragmentTags.REGISTER);
        replaceFragment(fragment == null ? RegisterFragment.newInstance() : fragment, R.id.frmRegisterContainer, Constants.FragmentTags.REGISTER, false);
    }

    @Override
    protected void initializeViews() {
        toolbar = findViewById(R.id.toolbarRegister);
    }

    @Override
    protected void setListeners() {

    }
}
