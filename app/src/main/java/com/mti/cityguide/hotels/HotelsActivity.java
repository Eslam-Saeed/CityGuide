package com.mti.cityguide.hotels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;

public class HotelsActivity extends BaseActivity {

    public static void startActivity(Context context, int countryId, int cityId, int areaId) {
        Intent starter = new Intent(context, HotelsActivity.class);
        starter.putExtra(Constants.BundleKeys.COUNTRY_ID, countryId);
        starter.putExtra(Constants.BundleKeys.CITY_ID, cityId);
        starter.putExtra(Constants.BundleKeys.AREA_ID, areaId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_toolbar);
        initializeViews();
        setListeners();
        loadFragment();
    }

    private void loadFragment() {
        HotelsFragment fragment = (HotelsFragment) getSupportFragmentManager().findFragmentByTag(Constants.FragmentTags.HOTELS);
        replaceFragment(fragment == null ? HotelsFragment.newInstance(getIntent().getIntExtra(Constants.BundleKeys.COUNTRY_ID, Constants.GeneralKeys.ALL)
                , getIntent().getIntExtra(Constants.BundleKeys.CITY_ID, Constants.GeneralKeys.ALL),
                getIntent().getIntExtra(Constants.BundleKeys.AREA_ID, Constants.GeneralKeys.ALL)) :
                fragment, R.id.frameContainer, Constants.FragmentTags.HOTELS, false);
    }

    @Override
    protected void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar, getString(R.string.hotels), true, true);
    }

    @Override
    protected void setListeners() {

    }
}
