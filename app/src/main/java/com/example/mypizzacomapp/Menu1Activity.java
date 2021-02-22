package com.example.mypizzacomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Menu1Activity extends AppCompatActivity {

    Spinner sizeAndCrust;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        sizeAndCrust = findViewById(R.id.pizzaSizeAndCrustSpinner);
        price = findViewById(R.id.menuPriceTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Menu1Activity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_menu));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeAndCrust.setAdapter(adapter);
        sizeAndCrust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 1) {
                    Toast.makeText(Menu1Activity.this, "Large Pan", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 20";
                    price.setText(p);
                } else if (i == 2) {
                    Toast.makeText(Menu1Activity.this, "Large Cheesy stuffed crust", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 25";
                    price.setText(p);
                }else if (i == 3) {
                    Toast.makeText(Menu1Activity.this, "Large Hand Stretched Crust<", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 30";
                    price.setText(p);
                }else if (i == 4) {
                    Toast.makeText(Menu1Activity.this, "Regular Pan", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 15";
                    price.setText(p);
                }else if (i == 5) {
                    Toast.makeText(Menu1Activity.this, "Regular Cheesy stuffed crust", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 20";
                    price.setText(p);
                }else if (i == 6) {
                    Toast.makeText(Menu1Activity.this, "Regular Hand Stretched Crust<", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 25";
                    price.setText(p);
                }else if (i == 7) {
                    Toast.makeText(Menu1Activity.this, "Personal Pan", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 10";
                    price.setText(p);
                }else if (i == 8) {
                    Toast.makeText(Menu1Activity.this, "Personal Cheesy stuffed crust", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 15";
                    price.setText(p);
                }else if (i == 9) {
                    Toast.makeText(Menu1Activity.this, "Personal Hand Stretched Crust<", Toast.LENGTH_SHORT).show();
                    String p = "Price : $ 20";
                    price.setText(p);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void purchaseOnbuttonClick(View view) {
        startActivity(new Intent(this, PizzaMenuActivity.class));
    }

}