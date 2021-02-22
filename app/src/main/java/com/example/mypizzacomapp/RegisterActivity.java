package com.example.mypizzacomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText emailAddressEditText;
    EditText registerPasswordEditText;
    EditText registerConfirmPasswordEditText;
    Button registerationButton;
    Button signinButton;
    String user_id;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebasestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //code idea from smallAcademy
        nameEditText = findViewById(R.id.registerNameEditText);
        emailAddressEditText = findViewById(R.id.emailAddressEditText);
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);
        registerConfirmPasswordEditText = findViewById(R.id.registerConfirmPasswordEditText);
        registerationButton = findViewById(R.id.registerationButton);
        signinButton = findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebasestore = FirebaseFirestore.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        registerationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String email = emailAddressEditText.getText().toString().trim();
                String password1 = registerPasswordEditText.getText().toString().trim();
                String password2 = registerConfirmPasswordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    emailAddressEditText.setError("Email is required!");
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    registerPasswordEditText.setError("Password is required!");
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    registerConfirmPasswordEditText.setError("Please confirm your password!");
                    return;
                }
                if (!password1.equals(password2)) {
                    registerConfirmPasswordEditText.setError("Password need to be matched!");
                    return;
                }

                if(password1.length() < 8 ){
                    registerPasswordEditText.setError("Password must be 8 characters long!");
                    return;
                }

                if(!isValid(password1)) {
                    registerPasswordEditText.setError("Password must include at least 1 upper case, 1 digits and 1 special character!");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser verify_user = firebaseAuth.getCurrentUser();
                            verify_user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Verification email has been sent to your email", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: Error!" + e.getMessage());
                                }
                            });

                            user_id = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebasestore.collection("Users").document(user_id);
                            Map<String, Object> user = new HashMap<>();
                            user.put("user_name", name);
                            user.put("user_email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: New user is created successfully" + user_id);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: User cannot be created. Error :" + e.getMessage());
                                }
                            });
                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Register Fail." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                 });

            }

        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    // idea from stackoverflow
    public static boolean isValid(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}