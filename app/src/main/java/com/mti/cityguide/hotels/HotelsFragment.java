package com.mti.cityguide.hotels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.mti.cityguide.model.Hotel;
import com.mti.cityguide.restaurants.FilterActivity;

import static android.app.Activity.RESULT_OK;

public class HotelsFragment extends BaseFragment implements HotelsView, HotelsAdapter.IHotelInteraction {
    private Context context;
    private HotelsPresenter presenter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtError;
    private HotelsAdapter adapter;

    private TextView  txtFilter, txtSearch;
    private EditText edtSearch;


    public static HotelsFragment newInstance(int countryId, int cityId, int areaId) {
        Bundle args = new Bundle();
        HotelsFragment fragment = new HotelsFragment();
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
        presenter = new HotelsPresenter(context, this,
                getArguments() == null ? Constants.GeneralKeys.ALL : getArguments().getInt(Constants.BundleKeys.COUNTRY_ID),
                getArguments() == null ? Constants.GeneralKeys.ALL : getArguments().getInt(Constants.BundleKeys.CITY_ID)
                , getArguments() == null ? Constants.GeneralKeys.ALL : getArguments().getInt(Constants.BundleKeys.AREA_ID));
        adapter = new HotelsAdapter(context, presenter.getListHotels(), this);
        recyclerView.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        progressBar = v.findViewById(R.id.progressBar);
        txtFilter = v.findViewById(R.id.txtFilter);
        txtSearch = v.findViewById(R.id.txtSearch);
        edtSearch = v.findViewById(R.id.edtSearch);
        txtError = v.findViewById(R.id.txtError);
    }


    @Override
    protected void setListeners() {
        super.setListeners();
        txtFilter.setOnClickListener(v -> onFilterClicked());
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
        startActivityForResult(FilterActivity.createIntent(context, presenter.getRestaurantHotelFilter(), true), Constants.RequestCodes.RESTAURANT_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hotels;
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHotelsLoadedSuccessfully() {
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
        txtError.setVisibility(View.GONE);
    }

    @Override
    public void onHotelClicked(Hotel hotel) {
        Toast.makeText(context, hotel.getHotelName() + " was clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        txtError.setVisibility(View.VISIBLE);
        txtError.setText(getString(R.string.no_hotels_found));
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
