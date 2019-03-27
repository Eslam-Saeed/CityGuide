package com.mti.cityguide.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.common.FragmentViewPagerAdapter;
import com.mti.cityguide.helpers.Constants;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private ViewPager vpHotelsRest;
    private TabLayout tabHotelsRest;
    private FragmentViewPagerAdapter adapter;

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, HomeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(starter);
        ((Activity) context).finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeViews();
        setListeners();
        initPager();
    }

    private void initPager() {
        if (adapter == null) {
            ArrayList<Fragment> listFragment = new ArrayList<>();
            ArrayList<String> listTitles = new ArrayList<>();
            listFragment.add(HomeFragment.newInstance(Constants.Types.HOTELS));
            listFragment.add(HomeFragment.newInstance(Constants.Types.RESTAURANT));
            listTitles.add(getString(R.string.hotels));
            listTitles.add(getString(R.string.restaurants));
            adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), listFragment, listTitles);
        }
        vpHotelsRest.setVisibility(View.VISIBLE);
        if (vpHotelsRest.getAdapter() == null)
            vpHotelsRest.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbarContainer);
        setToolbar(toolbar, getString(R.string.app_name), false, false);
        vpHotelsRest = findViewById(R.id.vpHotelsRest);
        tabHotelsRest = findViewById(R.id.tabHotelsRest);
        tabHotelsRest.setupWithViewPager(vpHotelsRest);
        tabHotelsRest.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    }

    @Override
    protected void setListeners() {

    }
}
