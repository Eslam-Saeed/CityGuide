package com.mti.cityguide.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.helpers.Constants;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements HomeView {
    private Context context;
    private HomePresenter presenter;
    private RelativeLayout rlHomeBackground;
    private TextView txtTitle, txtDesc, btnCity, btnArea, btnFilter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        presenter = new HomePresenter(context, this, getArguments() == null ? Constants.Types.HOTELS : getArguments().getInt(Constants.BundleKeys.TYPE));
        presenter.loadData();
        enableArea(false);
    }

    public static HomeFragment newInstance(int type) {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putInt(Constants.BundleKeys.TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        rlHomeBackground = v.findViewById(R.id.rlHomeBackground);
        txtTitle = v.findViewById(R.id.txtTitle);
        txtDesc = v.findViewById(R.id.txtDesc);
        btnCity = v.findViewById(R.id.btnCity);
        btnArea = v.findViewById(R.id.btnArea);
        btnFilter = v.findViewById(R.id.btnFilter);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        btnCity.setOnClickListener(v -> onCityClicked());
        btnArea.setOnClickListener(v -> onAreaClicked(presenter.getListAreasTitles()));
        btnFilter.setOnClickListener(v -> onFilterClicked());
    }

    private void onCityClicked() {
        enableArea(false);
        onCityClicked(presenter.getListCitiesTitles());
    }

    private void onCityClicked(ArrayList<String> listCities) {
        if (listCities.isEmpty())
            showErrorMessage(context.getString(R.string.no_data_available));
        else
            new AlertDialog.Builder(context)
                    .setItems(listCities.toArray(new String[0]), (dialog, selectedItemPositions) -> {
                        dialog.dismiss();
                        presenter.setSelectedCityId(selectedItemPositions);
                        btnCity.setText(listCities.get(selectedItemPositions));
                        presenter.getListAreasCloud();
                    })
                    .show();
    }

    private void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableArea(boolean enable) {
        btnArea.setAlpha(enable ? 1f : 0.5f);
        btnArea.setEnabled(enable);
        if (!enable) {
            btnArea.setText(getString(R.string.all));
            presenter.setSelectedAreaId(-1);
        }
    }


    private void onAreaClicked(ArrayList<String> listAreas) {
        if (listAreas.isEmpty())
            showErrorMessage(context.getString(R.string.no_data_available));
        else
            new AlertDialog.Builder(context)
                    .setItems(listAreas.toArray(new String[0]), (dialog, selectedItemPositions) -> {
                        dialog.dismiss();
                        presenter.setSelectedAreaId(selectedItemPositions);
                        btnArea.setText(listAreas.get(selectedItemPositions));
                    })
                    .show();
    }

    private void onFilterClicked() {
        presenter.filterClicked();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void showProgress(boolean shouldShow) {

    }

    @Override
    public void showBackground(int backgroundId) {
        rlHomeBackground.setBackground(ActivityCompat.getDrawable(context, backgroundId));
    }

    @Override
    public void setData(String title, String description) {
        txtTitle.setText(title);
        txtDesc.setText(description);
    }

    @Override
    public void navigateToHotelsScreen(int cityId, int areaId) {

    }

    @Override
    public void navigateToRestaurantsScreen(int cityId, int areaId) {

    }
}
