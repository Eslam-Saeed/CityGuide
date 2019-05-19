package com.mti.cityguide.restaurants.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.model.Restaurant;

public class RestaurantDetailsActivity extends BaseActivity {

    public static void startActivity(Context context, Restaurant restaurant) {
        Intent starter = new Intent(context, RestaurantDetailsActivity.class);
        starter.putExtra(Constants.BundleKeys.RESTAURANT, restaurant);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_toolbar);
        initializeViews();
        loadFragment(getIntent().getParcelableExtra(Constants.BundleKeys.RESTAURANT));
    }

    private void loadFragment(Restaurant restaurant) {
        RestaurantDetailsFragment fragment = (RestaurantDetailsFragment) getSupportFragmentManager().findFragmentByTag(Constants.FragmentTags.RESTAURANT_DETAILS);
        replaceFragment(fragment == null ? RestaurantDetailsFragment.newInstance(restaurant) : fragment, R.id.frameContainer, Constants.FragmentTags.RESTAURANT_DETAILS, false);
    }

    @Override
    protected void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar, "", true, true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void setListeners() {

    }
}
