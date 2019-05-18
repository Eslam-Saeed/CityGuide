package com.mti.cityguide.restaurants.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mti.cityguide.R;
import com.mti.cityguide.helpers.UIUtilities;
import com.mti.cityguide.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Menu> listMenu;
    private IMenuInteraction interaction;

    public MenuAdapter(Context context, ArrayList<Menu> listMenu, IMenuInteraction interaction) {
        this.context = context;
        this.listMenu = listMenu;
        this.inflater = LayoutInflater.from(context);
        this.interaction = interaction;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(inflater.inflate(R.layout.item_menu, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Menu menu = listMenu.get(position);
        if (menu != null) {
            UIUtilities.displayText(myViewHolder.txtMenuItemName, menu.getDishName());
            UIUtilities.displayText(myViewHolder.txtMenuItemPrice, menu.getMenuDishPrice() + "$");
            myViewHolder.setListeners(menu);
            myViewHolder.itemView.setBackgroundColor(context.getResources().getColor(position % 2 == 0 ?
                    R.color.white : R.color.backgroundColor));

        }
    }

    @Override
    public int getItemCount() {
        if (listMenu == null)
            return 0;
        return listMenu.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMenuItemName, txtMenuItemPrice;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMenuItemName = itemView.findViewById(R.id.txtMenuItemName);
            txtMenuItemPrice = itemView.findViewById(R.id.txtMenuItemPrice);
        }

        public void setListeners(Menu menu) {
            itemView.setOnClickListener(v -> {
                if (interaction != null)
                    interaction.onMenuItemClicked(menu);
            });
        }
    }

    interface IMenuInteraction {
        void onMenuItemClicked(Menu menu);
    }
}
