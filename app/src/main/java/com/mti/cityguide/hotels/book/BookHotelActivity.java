package com.mti.cityguide.hotels.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.model.Hotel;

public class BookHotelActivity extends BaseActivity implements BookHotelView {
    private BookHotelPresenter presenter;
    private Toolbar toolbar;
    private TextView txtHotelTitle, txtHotelAvgPrice, btnBook;
    private EditText edtDays, edtPersonNum;
    private RadioGroup rgRoomType;
    private RadioButton rbSingle, rbDouble;
    private ProgressBar progressBar;


    public static void startActivity(Context context, Hotel hotel) {
        Intent starter = new Intent(context, BookHotelActivity.class);
        starter.putExtra(Constants.BundleKeys.HOTEL, hotel);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);
        initializeViews();
        setListeners();
        presenter = new BookHotelPresenter(this, this, getIntent().getParcelableExtra(Constants.BundleKeys.HOTEL));
        txtHotelTitle.setText(presenter.getHotel().getHotelName());
    }

    @Override
    protected void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        txtHotelTitle = findViewById(R.id.txtHotelTitle);
        txtHotelAvgPrice = findViewById(R.id.txtHotelAvgPrice);
        btnBook = findViewById(R.id.btnBook);
        edtDays = findViewById(R.id.edtDays);
        edtPersonNum = findViewById(R.id.edtPersonNum);
        rgRoomType = findViewById(R.id.rgRoomType);
        rbSingle = findViewById(R.id.rbSingle);
        rbDouble = findViewById(R.id.rbDouble);
        progressBar = findViewById(R.id.progressBar);
        setToolbar(toolbar, this.getString(R.string.book_room), true, true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void setListeners() {
        btnBook.setOnClickListener(v -> onBtnBookClicked());
        edtPersonNum.addTextChangedListener(priceChanged);
        edtDays.addTextChangedListener(priceChanged);
    }

    private void onBtnBookClicked() {
        updateData();
        presenter.book();
    }

    private TextWatcher priceChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateData();
            txtHotelAvgPrice.setText("Average price is: " + presenter.getReservation().getAvgPrice());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateData() {
        int days = edtDays.getText().toString().isEmpty() ? 1 : Integer.parseInt(edtDays.getText().toString());
        presenter.getReservation().setDaysCount(days);
        presenter.getReservation().setPersonsCount(edtPersonNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(edtPersonNum.getText().toString()));
        presenter.getReservation().setRoomType(rgRoomType.getCheckedRadioButtonId() == rbSingle.getId() ? 1 : 2);
        presenter.getReservation().setAvgPrice(days * presenter.getHotel().getAvgPrice() * presenter.getReservation().getRoomType());
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookSuccess() {
        finish();
    }
}
