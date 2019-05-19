package com.mti.cityguide.restaurants.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mti.cityguide.R;
import com.mti.cityguide.base.BaseActivity;
import com.mti.cityguide.helpers.Constants;
import com.mti.cityguide.helpers.UIUtilities;
import com.mti.cityguide.model.Menu;
import com.squareup.picasso.Picasso;

public class GradientActivity extends BaseActivity {
    private TextView txtGradientDescription, txtDishName;
    private ImageView imgRecipe;


    public static void startActivity(Context context, Menu menu) {
        Intent starter = new Intent(context, GradientActivity.class);
        starter.putExtra(Constants.BundleKeys.MENU_ITEM, menu);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient);
        initializeViews();
        setListeners();
        Menu menu = getIntent().getParcelableExtra(Constants.BundleKeys.MENU_ITEM);
        loadDataIntoViews(menu);
    }

    private void loadDataIntoViews(Menu menu) {
        UIUtilities.displayText(txtDishName, menu.getDishName());
        UIUtilities.displayHTMLTextHide(txtGradientDescription, menu.getGradientDescription());
        if (TextUtils.isEmpty(menu.getGradientImgUrl()))
            imgRecipe.setBackgroundResource(R.drawable.default_image);
        else
            Picasso.get().load(menu.getGradientImgUrl()).placeholder(R.drawable.default_image).into(imgRecipe);
    }


    @Override
    protected void initializeViews() {
        txtGradientDescription = findViewById(R.id.txtGradientDescription);
        txtDishName = findViewById(R.id.txtDishName);
        imgRecipe = findViewById(R.id.imgRecipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar, "", true, true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void setListeners() {

    }
}
