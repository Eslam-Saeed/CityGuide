package com.mti.cityguide.restaurants;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.DTO.RestaurantFilter;

public class FilterActivity extends BaseActivity {
    private Switch switchRecommended;
    private RadioGroup rgSort;
    private RadioButton rbSortAZ, rbSortZA;
    private TextView txtApplyFilter;
    private RestaurantFilter filter;

    public static Intent startActivity(Context context, RestaurantFilter filter) {
        Intent starter = new Intent(context, FilterActivity.class);
        starter.putExtra(Constants.BundleKeys.RESTAURANT_FILTER, filter);
        return starter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initializeViews();
        setListeners();
        filter = getIntent().getParcelableExtra(Constants.BundleKeys.RESTAURANT_FILTER);
        loadDataIntoViews();
    }

    @Override
    protected void initializeViews() {
        switchRecommended = findViewById(R.id.switchRecommended);
        rgSort = findViewById(R.id.rgSort);
        rbSortAZ = findViewById(R.id.rbSortAZ);
        rbSortZA = findViewById(R.id.rbSortZA);
        txtApplyFilter = findViewById(R.id.txtApplyFilter);
    }

    @Override
    protected void setListeners() {
        txtApplyFilter.setOnClickListener(v -> onFilterClicked());
    }

    private void loadDataIntoViews() {
        if (!TextUtils.isEmpty(filter.getSortAZ())) {
            if (filter.getSortAZ().equals(Constants.GeneralKeys.ASC))
                rbSortAZ.setChecked(true);
            else if (filter.getSortAZ().equals(Constants.GeneralKeys.DESC))
                rbSortZA.setChecked(true);

            switchRecommended.setChecked(!TextUtils.isEmpty(filter.getRecommended()));
        }
    }

    private void onFilterClicked() {
        int id = rgSort.getCheckedRadioButtonId();
        if (rbSortAZ.getId() == id)
            filter.setSortAZ(Constants.GeneralKeys.ASC);
        else if (rbSortZA.getId() == id)
            filter.setSortAZ(Constants.GeneralKeys.DESC);

        filter.setRecommended(switchRecommended.isChecked() ? getString(R.string.recommended) : null);

        getIntent().putExtra(Constants.BundleKeys.RESTAURANT_FILTER, filter);
        setResult(RESULT_OK, getIntent());
        finish();
    }
}
