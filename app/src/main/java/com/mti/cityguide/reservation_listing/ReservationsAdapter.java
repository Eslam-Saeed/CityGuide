package com.mti.cityguide.reservation_listing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mti.cityguide.R;
import com.mti.cityguide.helpers.DateTimeHelper;
import com.mti.cityguide.helpers.UIUtilities;
import com.mti.cityguide.model.Reservation;

import java.util.ArrayList;

public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Reservation> listReservations;

    public ReservationsAdapter(Context context, ArrayList<Reservation> listReservations) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listReservations = listReservations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(inflater.inflate(R.layout.item_reservation, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Reservation reservation = listReservations.get(position);
        if (reservation != null) {
            UIUtilities.displayText(myViewHolder.txtHotelTitle, "Hotel : " + reservation.getHotelTitle());
            UIUtilities.displayText(myViewHolder.txtRoomType, "Room type : " + (reservation.getRoomType() == 1 ? "Single" : "Double"));
            UIUtilities.displayText(myViewHolder.txtDays, "Days : " + reservation.getDaysCount());
            UIUtilities.displayText(myViewHolder.txtNumPerson, "Person : " + reservation.getPersonsCount());
            UIUtilities.displayText(myViewHolder.txtAvgPrice, "Avg Price : " + reservation.getAvgPrice() + "$");

            UIUtilities.displayText(myViewHolder.txtStartAt, "Starts at: " + DateTimeHelper.formatDate(DateTimeHelper.SERVER_FORMAT,
                    DateTimeHelper.DD_MM, reservation.getStartAt()));
            UIUtilities.displayText(myViewHolder.txtEndAt, "Ends at: " + DateTimeHelper.formatDate(DateTimeHelper.SERVER_FORMAT,
                    DateTimeHelper.DD_MM, reservation.getEndAt()));
        }
    }

    @Override
    public int getItemCount() {
        if (listReservations == null)
            return 0;
        return listReservations.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtHotelTitle, txtRoomType, txtDays, txtNumPerson, txtAvgPrice, txtStartAt, txtEndAt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHotelTitle = itemView.findViewById(R.id.txtHotelTitle);
            txtRoomType = itemView.findViewById(R.id.txtRoomType);
            txtDays = itemView.findViewById(R.id.txtDays);
            txtNumPerson = itemView.findViewById(R.id.txtNumPerson);
            txtAvgPrice = itemView.findViewById(R.id.txtAvgPrice);
            txtStartAt = itemView.findViewById(R.id.txtStartAt);
            txtEndAt = itemView.findViewById(R.id.txtEndAt);
        }
    }
}
