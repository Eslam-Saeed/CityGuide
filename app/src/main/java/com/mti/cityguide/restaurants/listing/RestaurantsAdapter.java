package com.mti.cityguide.restaurants.listing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mti.cityguide.R;
import com.mti.cityguide.helpers.UIUtilities;
import com.mti.cityguide.helpers.Utilities;
import com.mti.cityguide.model.Restaurant;
import com.squareup.picasso.Picasso;

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
            UIUtilities.displayText(myViewHolder.txtRestaurantName, restaurant.getRestaurantName());
            UIUtilities.displayText(myViewHolder.txtRestaurantDescription, restaurant.getRestaurantDescription());
            UIUtilities.displayText(myViewHolder.txtRestaurantCategory, restaurant.getCategoryName());

            if (TextUtils.isEmpty(restaurant.getRestaurantImgUrl()))
                myViewHolder.imgRestaurant.setBackgroundResource(R.drawable.default_image);
            else
                Picasso.get().load(restaurant.getRestaurantImgUrl()).placeholder(R.drawable.default_image).into(myViewHolder.imgRestaurant);

            myViewHolder.ratingBar.setIsIndicator(true);
            myViewHolder.ratingBar.setRating(restaurant.getRestaurantRate());
            myViewHolder.imgRecommended.setVisibility(restaurant.getRestaurantRecommended() == 1 ? View.VISIBLE : View.GONE);
            myViewHolder.imgLocation.setVisibility((restaurant.getRestaurantLat() == null || restaurant.getRestaurantLng() == null) ? View.GONE : View.VISIBLE);
            myViewHolder.setListener(restaurant);
        }
    }

    @Override
    public int getItemCount() {
        if (restaurantsList == null)
            return 0;
        return restaurantsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imgRestaurant;
        private TextView txtRestaurantName, txtRestaurantCategory, txtRestaurantDescription;
        private RatingBar ratingBar;
        private ImageView imgRecommended, imgLocation;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRestaurant = itemView.findViewById(R.id.imgRestaurant);
            txtRestaurantName = itemView.findViewById(R.id.txtRestaurantName);
            txtRestaurantCategory = itemView.findViewById(R.id.txtRestaurantCategory);
            txtRestaurantDescription = itemView.findViewById(R.id.txtRestaurantDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imgRecommended = itemView.findViewById(R.id.imgRecommended);
            imgLocation = itemView.findViewById(R.id.imgLocation);
        }

        void setListener(Restaurant restaurant) {
            itemView.setOnClickListener(v -> {
                if (interaction != null)
                    interaction.onRestaurantClicked(restaurant);
            });

            imgLocation.setOnClickListener(v -> Utilities.openMap(context, restaurant.getRestaurantLat(), restaurant.getRestaurantLng()));
        }
    }

    interface IRestaurantsInteraction {
        void onRestaurantClicked(Restaurant restaurant);
    }
}
