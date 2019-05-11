package com.mti.cityguide.restaurants.listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.DTO.RestaurantHotelFilter;

public class FilterActivity extends BaseActivity {
    private Switch switchRecommended;
    private RadioGroup rgSort;
    private RadioButton rbSortAZ, rbSortZA;
    private TextView txtApplyFilter;
    private RestaurantHotelFilter filter;

    private View separatorPrice;
    private RelativeLayout rlAvgPrice, rlRoomType;
    private EditText edtFrom, edtTo;
    private CheckBox chkSingle, chkDouble;
    private boolean isHotel;

    public static Intent createIntent(Context context, RestaurantHotelFilter filter, boolean isHotel) {
        Intent starter = new Intent(context, FilterActivity.class);
        starter.putExtra(Constants.BundleKeys.RESTAURANT_FILTER, filter);
        starter.putExtra(Constants.BundleKeys.IS_HOTEL, isHotel);
        return starter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initializeViews();
        setListeners();
        filter = getIntent().getParcelableExtra(Constants.BundleKeys.RESTAURANT_FILTER);
        isHotel = getIntent().getBooleanExtra(Constants.BundleKeys.IS_HOTEL, false);
        loadDataIntoViews();
    }

    @Override
    protected void initializeViews() {
        switchRecommended = findViewById(R.id.switchRecommended);
        rgSort = findViewById(R.id.rgSort);
        rbSortAZ = findViewById(R.id.rbSortAZ);
        rbSortZA = findViewById(R.id.rbSortZA);
        txtApplyFilter = findViewById(R.id.txtApplyFilter);
        separatorPrice = findViewById(R.id.separatorPrice);
        rlAvgPrice = findViewById(R.id.rlAvgPrice);
        edtFrom = findViewById(R.id.edtFrom);
        edtTo = findViewById(R.id.edtTo);
        rlRoomType = findViewById(R.id.rlRoomType);
        chkSingle = findViewById(R.id.chkSingle);
        chkDouble = findViewById(R.id.chkDouble);
    }

    @Override
    protected void setListeners() {
        txtApplyFilter.setOnClickListener(v -> onFilterClicked());
    }

    private void loadDataIntoViews() {
        separatorPrice.setVisibility(isHotel ? View.VISIBLE : View.GONE);
        rlAvgPrice.setVisibility(isHotel ? View.VISIBLE : View.GONE);
        rlRoomType.setVisibility(isHotel ? View.VISIBLE : View.GONE);

        if (!TextUtils.isEmpty(filter.getSortAZ())) {
            if (filter.getSortAZ().equals(Constants.GeneralKeys.ASC))
                rbSortAZ.setChecked(true);
            else if (filter.getSortAZ().equals(Constants.GeneralKeys.DESC))
                rbSortZA.setChecked(true);

            switchRecommended.setChecked(!TextUtils.isEmpty(filter.getRecommended()));
        }

        if (isHotel) {
            if (!TextUtils.isEmpty(filter.getPriceLow()))
                edtFrom.setText(filter.getPriceLow());

            if (!TextUtils.isEmpty(filter.getPriceHigh()))
                edtTo.setText(filter.getPriceHigh());

            if (filter.getRoomType() != -1) {
                chkDouble.setChecked(true);
                if (filter.getRoomType() == 2)
                    chkSingle.setChecked(true);
            }
        }
    }

    private void onFilterClicked() {
        int id = rgSort.getCheckedRadioButtonId();
        if (rbSortAZ.getId() == id)
            filter.setSortAZ(Constants.GeneralKeys.ASC);
        else if (rbSortZA.getId() == id)
            filter.setSortAZ(Constants.GeneralKeys.DESC);

        filter.setRecommended(switchRecommended.isChecked() ? getString(R.string.recommended) : null);
        if (isHotel) {
            if (!TextUtils.isEmpty(edtFrom.getText().toString()) || !TextUtils.isEmpty(edtTo.getText().toString())) {
                filter.setPriceLow(TextUtils.isEmpty(edtFrom.getText().toString()) ? "0" : edtFrom.getText().toString());
                filter.setPriceHigh(TextUtils.isEmpty(edtTo.getText().toString()) ? "9999999" : edtTo.getText().toString());
            }

            if (chkSingle.isChecked())
                filter.setRoomType(-1);
            else if (chkDouble.isChecked())
                filter.setRoomType(1);
        }

        getIntent().putExtra(Constants.BundleKeys.RESTAURANT_FILTER, filter);
        setResult(RESULT_OK, getIntent());
        finish();
    }
}
