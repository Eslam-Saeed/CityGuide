package com.mti.cityguide.restaurants.details;

import android.content.Context;
import android.os.Bundle;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.model.Restaurant;

public class RestaurantDetailsFragment extends BaseFragment {
    private Context context;
    private RestaurantDetailsPresenter presenter;
    private MenuAdapter adapter;


    public static RestaurantDetailsFragment newInstance(Restaurant restaurant) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.BundleKeys.RESTAURANT, restaurant);
        RestaurantDetailsFragment fragment = new RestaurantDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_restaurant_details;
    }
}
