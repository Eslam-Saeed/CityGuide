package com.mti.cityguide.restaurants;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.model.Restaurant;

public class RestaurantsFragment extends BaseFragment implements RestaurantsView, RestaurantsAdapter.IRestaurantsInteraction {
    private Context context;
    private RestaurantsPresenter presenter;
    private RestaurantsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtError;

    public static RestaurantsFragment newInstance(int countryId, int cityId, int areaId) {
        Bundle args = new Bundle();
        RestaurantsFragment fragment = new RestaurantsFragment();
        args.putInt(Constants.BundleKeys.COUNTRY_ID, countryId);
        args.putInt(Constants.BundleKeys.CITY_ID, cityId);
        args.putInt(Constants.BundleKeys.AREA_ID, areaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        presenter = new RestaurantsPresenter(context, this,
                getArguments() == null ? Constants.GeneralKeys.ALL : getArguments().getInt(Constants.BundleKeys.COUNTRY_ID),
                getArguments() == null ? Constants.GeneralKeys.ALL : getArguments().getInt(Constants.BundleKeys.CITY_ID)
                , getArguments() == null ? Constants.GeneralKeys.ALL : getArguments().getInt(Constants.BundleKeys.AREA_ID));
        adapter = new RestaurantsAdapter(context, presenter.getListRestaurants(), this);
        recyclerView.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        progressBar = v.findViewById(R.id.progressBar);
        txtError = v.findViewById(R.id.txtError);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_restaurants;
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRestaurantClicked(Restaurant restaurant) {
        Toast.makeText(context, restaurant.toString() + " was clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestaurantsLoadedSuccessfully() {
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
        txtError.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        txtError.setVisibility(View.VISIBLE);
        txtError.setText(getString(R.string.no_hotels_found));
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
