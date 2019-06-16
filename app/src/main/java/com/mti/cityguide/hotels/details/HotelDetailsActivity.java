package com.mti.cityguide.hotels.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.UIUtilities;
import com.mti.cityguide.helpers.Utilities;
import com.mti.cityguide.hotels.book.BookHotelActivity;
import com.mti.cityguide.model.Hotel;
import com.squareup.picasso.Picasso;

public class HotelDetailsActivity extends BaseActivity {
    private Hotel hotel;
    private RoundedImageView imgHotel;
    private ImageView imgLocation, imgRecommended;
    private TextView txtHotelTitle, txtHotelDescription, txtHotelAvgPriceValue, btnBook;
    private RatingBar ratingBar;

    public static void startActivity(Context context, Hotel hotel) {
        Intent starter = new Intent(context, HotelDetailsActivity.class);
        starter.putExtra(Constants.BundleKeys.HOTEL, hotel);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        hotel = getIntent().getParcelableExtra(Constants.BundleKeys.HOTEL);
        initializeViews();
        setListeners();
        loadDataIntoViews(hotel);
    }

    private void loadDataIntoViews(Hotel hotel) {
        if (hotel != null) {
            UIUtilities.displayText(txtHotelTitle, hotel.getHotelName());
            UIUtilities.displayText(txtHotelDescription, hotel.getHotelDescription());
            UIUtilities.displayText(txtHotelAvgPriceValue, hotel.getAvgPrice() + "$ /Day");
            if (TextUtils.isEmpty(hotel.getHotelImgUrl()))
                imgHotel.setBackgroundResource(R.drawable.default_image);
            else
                Picasso.get().load(hotel.getHotelImgUrl()).placeholder(R.drawable.default_image).into(imgHotel);
            ratingBar.setIsIndicator(true);
            ratingBar.setRating(hotel.getHotelRating());
            imgLocation.setVisibility((hotel.getHotelLat() == null || hotel.getHotelLng() == null) ? View.GONE : View.VISIBLE);
            imgRecommended.setVisibility(hotel.getRecommended() == 1 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    protected void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar, "", true, true);
        imgHotel = findViewById(R.id.imgHotel);
        imgRecommended = findViewById(R.id.imgRecommended);
        imgLocation = findViewById(R.id.imgLocation);
        txtHotelTitle = findViewById(R.id.txtHotelTitle);
        txtHotelAvgPriceValue = findViewById(R.id.txtHotelAvgPriceValue);
        txtHotelDescription = findViewById(R.id.txtHotelDescription);
        btnBook = findViewById(R.id.btnBook);
        ratingBar = findViewById(R.id.ratingBar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void setListeners() {
        imgLocation.setOnClickListener(v -> Utilities.openMap(this, hotel.getHotelLat(), hotel.getHotelLng()));
        btnBook.setOnClickListener(v -> onBtnBookClicked());

    }

    private void onBtnBookClicked() {
        BookHotelActivity.startActivity(this, hotel);
    }
}
