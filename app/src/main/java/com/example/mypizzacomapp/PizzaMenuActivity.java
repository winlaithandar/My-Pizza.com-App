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

import java.util.ArrayList;

public class PizzaMenuActivity extends AppCompatActivity {

    Spinner menuSpinner;
    ListView complexListView;
    String [] pizza_menus;
    String[] pizza_menu_description;
    int[] menu_photo = {
            R.drawable.pizza_menu1,
            R.drawable.pizza_menu2,
            R.drawable.pizza_menu3,
            R.drawable.pizza_menu4,
            R.drawable.pizza_menu5,
            R.drawable.pizza_menu6,
            R.drawable.pizza_menu7,
            R.drawable.pizza_menu8,
    };

    ArrayList<Menu> menuArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_menu);

        menuSpinner = findViewById(R.id.menuSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PizzaMenuActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_menu));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(adapter);
        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                    //menuSpinner.setSelection(i);
                    //Toast.makeText(PizzaMenuActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else if (i == 1) {
                    menuSpinner.setSelection(i);
                    Toast.makeText(PizzaMenuActivity.this, "Deals", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DealsActivity.class));
                }else if (i == 2) {
                    menuSpinner.setSelection(i);
                    Toast.makeText(PizzaMenuActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                }else if (i == 3) {
                    menuSpinner.setSelection(i);
                    Toast.makeText(PizzaMenuActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                menuSpinner.setSelection(3);
                Toast.makeText(PizzaMenuActivity.this, "Menu", Toast.LENGTH_SHORT).show();
            }
        });

        pizza_menus = getResources().getStringArray(R.array.pizza_menus);
        pizza_menu_description = getResources().getStringArray(R.array.pizza_menu_descriptions);

        generateMenus();

        complexListView = findViewById(R.id.complexListView);
        complexListView.setAdapter(new MenuAdapter(this, R.layout.menu_list, menuArrayList));

        complexListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0 ) {
                    startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                } else if (i == 1 ) {
                    //startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                } else if (i == 2 ) {
                    //startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                } else if (i == 4 ) {
                    //startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                } else if (i == 5 ) {
                    //startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                } else if (i == 6 ) {
                    //startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                } else if (i == 7 ) {
                    //startActivity(new Intent(getApplication(), Menu1Activity.class));
                    Toast.makeText(getBaseContext(), "Selected" + menuArrayList.get(i), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void generateMenus() {
        for (int i = 0; i < menu_photo.length; i++){
            Menu menu = new Menu(pizza_menus[i], pizza_menu_description[i], menu_photo[i]);

            menuArrayList.add(menu);
        }
    }
}