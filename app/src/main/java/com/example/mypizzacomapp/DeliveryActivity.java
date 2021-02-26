package com.example.mypizzacomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DeliveryActivity extends AppCompatActivity {
    final String ErrorMessage = "Google Play Services Unavaiable.";
    final String SuccessMessage = "Connected Successfully";
    final int LocationPermission = 1;
    Geocoder geocoder;
    List<Address> addresses;
    EditText postalCodeEditText;
    EditText detailAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        //List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
        //List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
        //    http://maps.googleapis.com/maps/api/geocode/json?latlng=lattitude,longitude&sensor=true

        postalCodeEditText = findViewById(R.id.postalCodeEditText);
        detailAddressEditText = findViewById(R.id.detailAddressEditText);

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int result = apiAvailability.isGooglePlayServicesAvailable(this);

        if(result == ConnectionResult.SUCCESS) {
            Toast.makeText(this, SuccessMessage, Toast.LENGTH_SHORT).show();
        } else {
            if(!apiAvailability.isUserResolvableError(result)) {
                Toast.makeText(this, ErrorMessage, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        int permission = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);

        if(permission == PERMISSION_GRANTED) {
            getMyLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION}, LocationPermission);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==LocationPermission) {
            if(grantResults[0] != PERMISSION_GRANTED) {
                Toast.makeText(this, "Location Access Denied!", Toast.LENGTH_SHORT).show();
            } else {
                getMyLocation();
            }
        }
    }

    private void getMyLocation() {
        FusedLocationProviderClient fusedLocationProviderClient;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    updateLocation(location);
                }
            });
        }
    }

    private void updateLocation(Location location) {
        String PostalCode = "No Postal Code found";
        String LocationString = "No Location Found!";

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            //LocationString = "Latitube" + latitude +".";
            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            LocationString = addresses.get(0).getAddressLine(0);
            PostalCode = addresses.get(0).getPostalCode();
        }
        postalCodeEditText.setText(PostalCode);
        detailAddressEditText.setText(LocationString);
    }

    public void addressSelectedButtonOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), PaymentActivity.class));
    }
}