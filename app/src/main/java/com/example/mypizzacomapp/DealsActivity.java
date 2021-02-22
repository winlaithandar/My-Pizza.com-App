package com.example.mypizzacomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DealsActivity extends AppCompatActivity {
    Spinner menuSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);

        menuSpinner = findViewById(R.id.dealsMenuSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DealsActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_menu));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(adapter);
        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 1) {
                    Toast.makeText(DealsActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else if (i == 2) {
                    Toast.makeText(DealsActivity.this, "Deals", Toast.LENGTH_SHORT).show();
                }else if (i == 3) {
                    Toast.makeText(DealsActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }else if (i == 4) {
                    Toast.makeText(DealsActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}