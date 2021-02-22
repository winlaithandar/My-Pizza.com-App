package com.example.mypizzacomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    Button placeOrderButton;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        placeOrderButton = findViewById(R.id.placeOrderButton);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        final long[] value = {2000, 1000};

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        });

    }

    public void cardPaymentOnClickButton(View view) {
        Toast.makeText(this, "Card Payment Selected.", Toast.LENGTH_LONG).show();
    }

    public void cashPaymentOnClickButton(View view) {
        Toast.makeText(this, "Cash Payment Selected.", Toast.LENGTH_LONG).show();
    }

}