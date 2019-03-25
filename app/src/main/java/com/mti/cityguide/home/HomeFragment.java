package com.mti.cityguide.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseFragment;
import com.mti.cityguide.helpers.Constants;

public class HomeFragment extends BaseFragment {
    private Context context;
    private HomePresenter presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
    }

    public static HomeFragment newInstance(int type) {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putInt(Constants.BundleKeys.TYPE,type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initializeViews(View v) {
        super.initializeViews(v);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
