package com.example.mypizzacomapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileActivity extends AppCompatActivity {
    TextView displayAccountNameEditText;
    TextView displayAccountEmailEditText;
    Spinner menuSpinner;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseStore;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        menuSpinner = findViewById(R.id.settingMenuSpinner);
        displayAccountNameEditText = findViewById(R.id.displayAccountNameTextView);
        displayAccountEmailEditText = findViewById(R.id.displayAccountEmailEditText);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStore = FirebaseFirestore.getInstance();

        user_id = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseStore.collection("Users").document(user_id);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                displayAccountNameEditText.setText(value.getString("user_name"));
                displayAccountEmailEditText.setText(value.getString("user_email"));
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfileActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_menu));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(adapter);
        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                   // menuSpinner.setSelection(i);
                   // Toast.makeText(ProfileActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else if (i == 1) {
                    menuSpinner.setSelection(i);
                    Toast.makeText(ProfileActivity.this, "Deals", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DealsActivity.class));
                }else if (i == 2) {
                    menuSpinner.setSelection(i);
                    Toast.makeText(ProfileActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), PizzaMenuActivity.class));
                }else if (i == 3) {
                    menuSpinner.setSelection(i);
                    Toast.makeText(ProfileActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}