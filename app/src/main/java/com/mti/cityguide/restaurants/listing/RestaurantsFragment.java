package com.mti.cityguide.restaurants.listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.Utilities;
import com.mti.cityguide.model.Restaurant;
import com.mti.cityguide.restaurants.details.RestaurantDetailsActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class RestaurantsFragment extends BaseFragment implements RestaurantsView, RestaurantsAdapter.IRestaurantsInteraction {
    private Context context;
    private RestaurantsPresenter presenter;
    private RestaurantsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtError, txtFilter, txtCategory, txtSearch;
    private EditText edtSearch;

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
        presenter.loadCategories();
        presenter.loadData();
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        progressBar = v.findViewById(R.id.progressBar);
        txtFilter = v.findViewById(R.id.txtFilter);
        txtCategory = v.findViewById(R.id.txtCategory);
        txtSearch = v.findViewById(R.id.txtSearch);
        edtSearch = v.findViewById(R.id.edtSearch);
        txtError = v.findViewById(R.id.txtError);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        txtFilter.setOnClickListener(v -> onFilterClicked());
        txtCategory.setOnClickListener(v -> onCategoryClicked(presenter.getListRestaurantCategoriesTitles()));
        txtSearch.setOnClickListener(v -> handleSearchCLicked());
        edtSearch.setOnEditorActionListener((v, actionId, event) -> onSearchClicked(actionId));
    }


    @Override
    public void handleSearchCLicked() {
        if (edtSearch.getVisibility() == View.VISIBLE) {
            callSearch("");
        }
        edtSearch.setVisibility(edtSearch.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }


    @Override
    public void callSearch(String searchKeyword) {
        presenter.getRestaurantHotelFilter().setSearch(searchKeyword);
        presenter.loadData();
        Utilities.hideSoftKeyboard(context);
    }


    @Override
    public boolean onSearchClicked(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callSearch(edtSearch.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    public void onFilterClicked() {
        startActivityForResult(FilterActivity.createIntent(context, presenter.getRestaurantHotelFilter(), false), Constants.RequestCodes.RESTAURANT_CODE);
    }

    @Override
    public void onCategoryClicked(ArrayList<String> listCategories) {
        if (listCategories.isEmpty())
            showErrorMessage(context.getString(R.string.no_data_available));
        else
            new AlertDialog.Builder(context)
                    .setItems(listCategories.toArray(new String[0]), (dialog, selectedItemPositions) -> {
                        dialog.dismiss();
                        presenter.getRestaurantHotelFilter().setCategoryId(presenter.getListRestaurantCategories().get(selectedItemPositions).getCategoryId());
                        txtCategory.setText(selectedItemPositions != 0 ? listCategories.get(selectedItemPositions) : getString(R.string.category));
                        presenter.loadData();
                    })
                    .show();
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
        RestaurantDetailsActivity.startActivity(context, restaurant);
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
        txtError.setText(getString(R.string.no_restaurants_found));
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCodes.RESTAURANT_CODE && resultCode == RESULT_OK && data != null) {
            presenter.setRestaurantHotelFilter(data.getParcelableExtra(Constants.BundleKeys.RESTAURANT_FILTER));
            presenter.loadData();
        }
    }
}
