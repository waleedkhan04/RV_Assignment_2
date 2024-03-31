package com.example.smd_assignment2;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    ArrayList<Restaurant> restaurants;
    private final ArrayList<Restaurant> filteredList;
    public RestaurantAdapter(ArrayList<Restaurant> list)
    {
        restaurants = list;
        filteredList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = filteredList.get(position);
        holder.ContactName.setText(restaurant.getName());
        holder.Phone.setText(restaurant.getPhone());
        holder.Location.setText(restaurant.getLocation());
        holder.Description.setText(restaurant.getDescription());
        holder.Rating.setText(restaurant.getRating());
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<Restaurant> filteredList) {
        this.filteredList.clear();
        this.filteredList.addAll(filteredList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.filteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView ContactName, Phone, Location, Description, Rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ContactName = itemView.findViewById(R.id.Name);
            Phone = itemView.findViewById(R.id.Phone);
            Location = itemView.findViewById(R.id.Location);
            Description = itemView.findViewById(R.id.Description);
            Rating = itemView.findViewById(R.id.Rating);


        }
    }
}