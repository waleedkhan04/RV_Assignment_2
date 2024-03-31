package com.example.smd_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    Button btnBack, btnRegister;
    EditText Name, Location, Phone, Description, Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields
                String name = Name.getText().toString();
                String location = Location.getText().toString();
                String phone = Phone.getText().toString();
                String description = Description.getText().toString();
                String rating = Rating.getText().toString();

                // Save data to SharedPreferences
                saveDataToSharedPreferences(name, location, phone, description, rating);

                Toast.makeText(Registration.this, "New restaurant added!", Toast.LENGTH_SHORT).show();

                // Clear EditText fields for entering another restaurant
                Name.setText("");
                Location.setText("");
                Phone.setText("");
                Description.setText("");
                Rating.setText("");
            }
        });
    }

    private void init() {
        btnRegister =findViewById(R.id.btnRegister);
        btnBack =findViewById(R.id.btnBack);
        Name = findViewById(R.id.etName);
        Location = findViewById(R.id.etLocation);
        Phone = findViewById(R.id.etPhone);
        Description = findViewById(R.id.etDescription);
        Rating = findViewById(R.id.etRating);
    }

    private void saveDataToSharedPreferences(String name, String location, String phone, String description, String rating)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("restaurant_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int count = sharedPreferences.getInt("count", 0);

        String nameKey = "name_" + count;
        String locationKey = "location_" + count;
        String phoneKey = "phone_" + count;
        String descriptionKey = "description_" + count;
        String ratingKey = "rating_" + count;

        editor.putString(nameKey, name);
        editor.putString(locationKey, location);
        editor.putString(phoneKey, phone);
        editor.putString(descriptionKey, description);
        editor.putString(ratingKey, rating);

        editor.putInt("count", count + 1);

        editor.apply();
    }

}

