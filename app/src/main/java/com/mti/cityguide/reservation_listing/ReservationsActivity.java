package com.mti.cityguide.reservation_listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;

public class ReservationsActivity extends BaseActivity implements ReservationsView {
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private RecyclerView rvReservations;
    private TextView txtError;
    private ReservationsPresenter presenter;
    private ReservationsAdapter adapter;


    public static void startActivity(Context context) {
        Intent starter = new Intent(context, ReservationsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        initializeViews();
        presenter = new ReservationsPresenter(this, this);
        adapter = new ReservationsAdapter(this, presenter.getListReservations());
        rvReservations.setAdapter(adapter);
        setToolbar(toolbar, this.getString(R.string.reservations), true, true);
        setListeners();
        presenter.loadData();
    }

    @Override
    protected void initializeViews() {
        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);
        rvReservations = findViewById(R.id.rvReservations);
        txtError = findViewById(R.id.txtError);
        rvReservations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvReservations.setNestedScrollingEnabled(false);
    }

    @Override
    protected void setListeners() {
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        txtError.setVisibility(View.VISIBLE);
        rvReservations.setVisibility(View.GONE);
        txtError.setText(message);
    }

    @Override
    public void onReservationsLoadedSuccessfully() {
        adapter.notifyDataSetChanged();
        txtError.setVisibility(View.GONE);
        rvReservations.setVisibility(View.VISIBLE);
    }
}
