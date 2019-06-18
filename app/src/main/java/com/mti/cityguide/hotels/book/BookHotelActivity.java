package com.mti.cityguide.hotels.book;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.mti.cityguide.helpers.DateTimeHelper;
import com.mti.cityguide.model.Hotel;

import java.util.Calendar;

public class BookHotelActivity extends BaseActivity implements BookHotelView {
    private BookHotelPresenter presenter;
    private Toolbar toolbar;
    private TextView txtHotelTitle, txtHotelAvgPrice,txtStartAt, txtEndAt, btnBook;
    private EditText edtDays, edtPersonNum;
    private RadioGroup rgRoomType;
    private RadioButton rbSingle, rbDouble;
    private DatePickerDialog startDatePicker, endDatePicker;
    private ProgressBar progressBar;
    final Calendar myCalendar = Calendar.getInstance();



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
        startDatePicker = new DatePickerDialog(this, startDateOnDateSetListener, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        endDatePicker = new DatePickerDialog(this, endDateOnDateSetListener, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        startDatePicker.getDatePicker().setMinDate(myCalendar.getTimeInMillis() - 9999);
        endDatePicker.getDatePicker().setMinDate(myCalendar.getTimeInMillis() - 9999);
    }

    private DatePickerDialog.OnDateSetListener endDateOnDateSetListener = (view, year, month, dayOfMonth) -> {
        txtEndAt.setText("End at :" + validateStartDate(year, month, dayOfMonth, txtEndAt, false));
    };

    private DatePickerDialog.OnDateSetListener startDateOnDateSetListener = (view, year, month, dayOfMonth) -> {
        txtStartAt.setText("Start at :" + validateStartDate(year, month, dayOfMonth, txtStartAt, true));
    };

    private String validateStartDate(int year, int month, int dayOfMonth, TextView txt, boolean isStart) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (isStart)
            presenter.getReservation().setStartAt(DateTimeHelper.convertDateToString(cal.getTime(), DateTimeHelper.SERVER_FORMAT));
        else
            presenter.getReservation().setEndAt(DateTimeHelper.convertDateToString(cal.getTime(), DateTimeHelper.SERVER_FORMAT));
        return DateTimeHelper.convertDateToString(cal.getTime(), DateTimeHelper.DD_MM);
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
        txtStartAt = findViewById(R.id.txtStartAt);
        txtEndAt = findViewById(R.id.txtEndAt);
        setToolbar(toolbar, this.getString(R.string.book_room), true, true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void setListeners() {
        btnBook.setOnClickListener(v -> onBtnBookClicked());
        edtPersonNum.addTextChangedListener(priceChanged);
        edtDays.addTextChangedListener(priceChanged);
        txtStartAt.setOnClickListener(v -> startDatePicker.show());
        txtEndAt.setOnClickListener(v -> endDatePicker.show());
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
