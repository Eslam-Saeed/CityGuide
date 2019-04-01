package com.mti.cityguide.restaurants;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mti.cityguide.R;
import com.mti.cityguide.model.Restaurant;

import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Restaurant> restaurantsList;
    private IRestaurantsInteraction interaction;

    public RestaurantsAdapter(Context context, ArrayList<Restaurant> restaurantsList, IRestaurantsInteraction interaction) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.restaurantsList = restaurantsList;
        this.interaction = interaction;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_restaurant, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Restaurant restaurant = restaurantsList.get(position);
        if (restaurant != null) {

        }
    }

    @Override
    public int getItemCount() {
        if (restaurantsList == null)
            return 0;
        return restaurantsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    interface IRestaurantsInteraction {
        void onRestaurantClicked(Restaurant restaurant);
    }
}
