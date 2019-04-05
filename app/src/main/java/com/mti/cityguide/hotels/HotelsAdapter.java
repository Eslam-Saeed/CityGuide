package com.mti.cityguide.hotels;

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
import com.mti.cityguide.model.Hotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Hotel> listHotels;
    private IHotelInteraction interaction;

    public HotelsAdapter(Context context, ArrayList<Hotel> listHotels, IHotelInteraction interaction) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listHotels = listHotels;
        this.interaction = interaction;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(inflater.inflate(R.layout.item_hotel, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Hotel hotel = listHotels.get(position);
        if (hotel != null) {
            UIUtilities.displayText(myViewHolder.txtHotelTitle, hotel.getHotelName());
            UIUtilities.displayText(myViewHolder.txtHotelDescription, hotel.getHotelDescription());
            if (TextUtils.isEmpty(hotel.getHotelImgUrl()))
                myViewHolder.imgHotel.setBackgroundResource(R.drawable.default_image);
            else
                Picasso.get().load(hotel.getHotelImgUrl()).placeholder(R.drawable.default_image).into(myViewHolder.imgHotel);
            myViewHolder.ratingBar.setIsIndicator(true);
            myViewHolder.ratingBar.setRating(hotel.getHotelRating());
            myViewHolder.imgLocation.setVisibility((hotel.getHotelLat() == null || hotel.getHotelLng() == null) ? View.GONE : View.VISIBLE);
            myViewHolder.setListener(hotel);
        }
    }

    @Override
    public int getItemCount() {
        if (listHotels == null)
            return 0;
        return listHotels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imgHotel;
        private ImageView imgLocation;
        private TextView txtHotelTitle, txtHotelDescription;
        private RatingBar ratingBar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHotel = itemView.findViewById(R.id.imgHotel);
            imgLocation = itemView.findViewById(R.id.imgLocation);
            txtHotelTitle = itemView.findViewById(R.id.txtHotelTitle);
            txtHotelDescription = itemView.findViewById(R.id.txtHotelDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        void setListener(Hotel hotel) {
            itemView.setOnClickListener(v -> {
                if (interaction != null)
                    interaction.onHotelClicked(hotel);
            });
            imgLocation.setOnClickListener(v -> Utilities.openMap(context, hotel.getHotelLat(), hotel.getHotelLng()));
        }
    }

    interface IHotelInteraction {
        void onHotelClicked(Hotel hotel);
    }
}
