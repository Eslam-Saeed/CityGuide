package com.mti.cityguide.restaurants.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.UIUtilities;
import com.mti.cityguide.helpers.Utilities;
import com.mti.cityguide.model.Menu;
import com.mti.cityguide.model.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantDetailsFragment extends BaseFragment implements MenuAdapter.IMenuInteraction, RestaurantDetailsView {
    private Context context;
    private ProgressBar progressBar;
    private RestaurantDetailsPresenter presenter;
    private MenuAdapter adapterBreakfast, adapterLunch, adapterDinner;
    private RelativeLayout rlRestaurantInfo;
    private RecyclerView rvBreakfast, rvLunch, rvDinner;
    private LinearLayout lnrMenu;

    private RoundedImageView imgRestaurant;
    private TextView txtRestaurantName, txtRestaurantCategory, txtRestaurantDescription;
    private RatingBar ratingBar;
    private ImageView imgRecommended, imgLocation;


    public static RestaurantDetailsFragment newInstance(Restaurant restaurant) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.BundleKeys.RESTAURANT, restaurant);
        RestaurantDetailsFragment fragment = new RestaurantDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        presenter = new RestaurantDetailsPresenter(context, this, getArguments().getParcelable(Constants.BundleKeys.RESTAURANT));
        adapterBreakfast = new MenuAdapter(context, presenter.getListMenuBreakfast(), this);
        adapterLunch = new MenuAdapter(context, presenter.getListMenuLunch(), this);
        adapterDinner = new MenuAdapter(context, presenter.getListMenuDinner(), this);
        rvBreakfast.setAdapter(adapterBreakfast);
        rvLunch.setAdapter(adapterLunch);
        rvDinner.setAdapter(adapterDinner);
        presenter.loadRestaurantData();
        presenter.callGetMenu();
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
        rlRestaurantInfo = v.findViewById(R.id.rlRestaurantInfo);
        rvBreakfast = v.findViewById(R.id.rvBreakfast);
        rvLunch = v.findViewById(R.id.rvLunch);
        rvDinner = v.findViewById(R.id.rvDinner);
        lnrMenu = v.findViewById(R.id.lnrMenu);
        progressBar = v.findViewById(R.id.progressBar);
        imgRestaurant = v.findViewById(R.id.imgRestaurant);
        txtRestaurantName = v.findViewById(R.id.txtRestaurantName);
        txtRestaurantCategory = v.findViewById(R.id.txtRestaurantCategory);
        txtRestaurantDescription = v.findViewById(R.id.txtRestaurantDescription);
        ratingBar = v.findViewById(R.id.ratingBar);
        imgRecommended = v.findViewById(R.id.imgRecommended);
        imgLocation = v.findViewById(R.id.imgLocation);
        rvBreakfast.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvLunch.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvDinner.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvBreakfast.setNestedScrollingEnabled(false);
        rvLunch.setNestedScrollingEnabled(false);
        rvDinner.setNestedScrollingEnabled(false);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        imgLocation.setOnClickListener(v -> Utilities.openMap(context, presenter.getRestaurant().getRestaurantLat(),
                presenter.getRestaurant().getRestaurantLng()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_restaurant_details;
    }

    @Override
    public void onMenuItemClicked(Menu menu) {
        if (!TextUtils.isEmpty(menu.getGradientDescription())) {
            GradientActivity.startActivity(context, menu);
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuLoadedSuccessfully() {
        lnrMenu.setVisibility(View.VISIBLE);
        adapterBreakfast.notifyDataSetChanged();
        adapterLunch.notifyDataSetChanged();
        adapterDinner.notifyDataSetChanged();
    }

    @Override
    public void showEmptyMenuView() {
        lnrMenu.setVisibility(View.GONE);
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showRestaurantData(Restaurant restaurant) {
        if (restaurant != null) {
            UIUtilities.displayText(txtRestaurantName, restaurant.getRestaurantName());
            UIUtilities.displayText(txtRestaurantDescription, restaurant.getRestaurantDescription());
            UIUtilities.displayText(txtRestaurantCategory, restaurant.getCategoryName());

            if (TextUtils.isEmpty(restaurant.getRestaurantImgUrl()))
                imgRestaurant.setBackgroundResource(R.drawable.default_image);
            else
                Picasso.get().load(restaurant.getRestaurantImgUrl()).placeholder(R.drawable.default_image).into(imgRestaurant);

            ratingBar.setIsIndicator(true);
            ratingBar.setRating(restaurant.getRestaurantRate());
            imgRecommended.setVisibility(restaurant.getRestaurantRecommended() == 1 ? View.VISIBLE : View.GONE);
            imgLocation.setVisibility((restaurant.getRestaurantLat() == null || restaurant.getRestaurantLng() == null) ? View.GONE : View.VISIBLE);
        }
    }
}
