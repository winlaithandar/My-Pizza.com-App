package com.example.mypizzacomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    Spinner menuSpinner;
    ListView menuListView;
    String[] pizza_menus;
    String[] pizza_menu_descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuSpinner = findViewById(R.id.menuSpinner);
        pizza_menus = getResources().getStringArray(R.array.pizza_menus);
        pizza_menu_descriptions = getResources().getStringArray(R.array.pizza_menu_descriptions);
        menuListView = findViewById(R.id.menuListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pizza_menus);

        menuListView.setAdapter(arrayAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MenuActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_menu));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(adapter);
        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 1) {
                    Toast.makeText(MenuActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else if (i == 2) {
                    Toast.makeText(MenuActivity.this, "Deals", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DealsActivity.class));
                }else if (i == 3) {
                    Toast.makeText(MenuActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                }else if (i == 4) {
                    Toast.makeText(MenuActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Menu1Activity.class));
            }
        });
    }

    public void menuOnbuttonClick(View view) {
        startActivity(new Intent(this, PizzaMenuActivity.class));
    }
}