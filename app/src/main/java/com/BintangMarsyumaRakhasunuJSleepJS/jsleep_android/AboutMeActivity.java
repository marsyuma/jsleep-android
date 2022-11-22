package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {
    TextView name,email, balance;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        name = findViewById(R.id.NameFillText);
        email = findViewById(R.id.EmailFillText);
        balance = findViewById(R.id.BalanceFillText);

        name.setText(MainActivity.loggedAccount.name);
        email.setText(MainActivity.loggedAccount.email);
        balance.setText(String.valueOf(MainActivity.loggedAccount.balance));

    }
}