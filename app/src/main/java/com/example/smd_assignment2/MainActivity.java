package com.example.smd_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRestaurants;
    RestaurantAdapter RAdapter;
    ArrayList<Restaurant> list;
    Button btnAddNewR;
    Button btnSearch;
    EditText Search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        rvRestaurants.setHasFixedSize(true);

        // Initialize adapter with the original list
        RAdapter = new RestaurantAdapter(list);

        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurants.setAdapter(RAdapter);

        loadDataFromSharedPreferences();

        btnAddNewR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = Search.getText().toString().trim();
                Log.d("MainActivity", "Search query: " + query);
                filterList(query);
            }
        });
    }
    private void init()
    {
        list = new ArrayList<>();
        list.add(new Restaurant("Fuchsia Kitchen", " Block E1 Block E 1 Gulberg III, Lahore", "+923214005000", "A Modern Asian restaurant", "4.5"));
        list.add(new Restaurant("Paola’s Cosa Nostra", "Block H Gulberg، Lahore", "+923214567000", "An Italian restaurant", "4"));
        list.add(new Restaurant("Saku Hana", "Fairways Commercial", "+923214005678", "A Japanese restaurant", "4.7"));
        list.add(new Restaurant("Sashas", "Mall 94 Gulberg, Lahore", "+923213345000", "The pioneers of all day breakfast and dining", "5"));

        rvRestaurants = findViewById(R.id.rvRestaurants);
        btnAddNewR = findViewById(R.id.btnAddNewRestaurant);
        Search = findViewById(R.id.etSearch);

        btnSearch = findViewById(R.id.btnSearch);

    }
    private void filterList(String query) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();

        for (Restaurant restaurant : list) {
            boolean isNameMatch = restaurant.getName().toLowerCase().contains(query.toLowerCase());
            boolean isLocationMatch = restaurant.getLocation().toLowerCase().contains(query.toLowerCase());
            boolean isRatingMatch = restaurant.getRating().equals(query);

            if (isNameMatch || isLocationMatch || isRatingMatch) {
                filteredList.add(restaurant);
            }
        }

        RAdapter.filterList(filteredList);
    }


    @SuppressLint("NotifyDataSetChanged")
    private void loadDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("restaurant_data", MODE_PRIVATE);
        int count = sharedPreferences.getInt("count", 0);

        for (int i = 0; i < count; i++) {
            String nameKey = "name_" + i;
            String locationKey = "location_" + i;
            String phoneKey = "phone_" + i;
            String descriptionKey = "description_" + i;
            String ratingKey = "rating_" + i;

            String name = sharedPreferences.getString(nameKey, "");
            String location = sharedPreferences.getString(locationKey, "");
            String phone = sharedPreferences.getString(phoneKey, "");
            String description = sharedPreferences.getString(descriptionKey, "");
            String rating = sharedPreferences.getString(ratingKey, "");

            if (isDataValid(name, location, phone, description, rating)) {
                list.add(new Restaurant(name, location, phone, description, rating));
            }
        }
        RAdapter.notifyDataSetChanged();
    }

    private boolean isDataValid(String... data) {
        for (String datum : data) {
            if (datum.isEmpty()) {
                return false;
            }
        }
        return true;
    }



}