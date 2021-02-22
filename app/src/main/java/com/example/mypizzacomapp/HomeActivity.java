package com.example.mypizzacomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    Spinner menuSpinner;
    TextView verifyEmailTextView;
    String user_id;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebasestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuSpinner = findViewById(R.id.homeMenuSpinner);
        verifyEmailTextView = findViewById(R.id.verifyEmailTextView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebasestore = FirebaseFirestore.getInstance();

        user_id = firebaseAuth.getCurrentUser().getUid();
        FirebaseUser verify_user = firebaseAuth.getCurrentUser();

        if(!verify_user.isEmailVerified()) {
            verifyEmailTextView.setVisibility(View.VISIBLE);

            verifyEmailTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verify_user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification email has been sent to your email", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: Error!" + e.getMessage());
                        }
                    });
                }
            });
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_menu));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(adapter);
        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 1) {
                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (i == 2) {
                    Toast.makeText(HomeActivity.this, "Deals", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DealsActivity.class));
                }else if (i == 3) {
                    Toast.makeText(HomeActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }else if (i == 4) {
                    Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickHomeMenuImage(View view) {
        startActivity(new Intent(getApplicationContext(), DealsActivity.class));
    }

}