package com.example.uishowcaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Find the button
        Button registerButton = findViewById(R.id.registerButton);

        // Set a click listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DashboardActivity
                Intent intent = new Intent(RegistrationActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
