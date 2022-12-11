package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Account;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Renter;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.UtilsApi;

import retrofit2.*;
/**
 * @author Bintang MR
 * @version 1.0
 *
 */
public class AboutMeActivity extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;

    TextView name,email, balance;

    Button RegisterRenterButton;
    CardView RegisterButtonCard;

    Button RegisterRenterConfirmation, RegisterRenterCancel;
    CardView RenterRegistrationCard;
    TextView RenterName, RenterAddress, RenterPhoneNumber;

    CardView RegisterDetailCard;
    TextView RenterNameFill, AddressFill, PhoneNumberFill;

    Button TopUpButton;
    TextView AmountBox;

    Button back;

    Button PaymentButton;
    /**
     This method is called when the AboutMe activity is created. It sets up the views and sets the visibility of the different view groups based on the savedAccount field.
     @param savedInstanceState The saved instance state
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        //=============Detail Akun=======================
        name = findViewById(R.id.NameFillText);
        email = findViewById(R.id.EmailFillText);
        balance = findViewById(R.id.BalanceFillText);
        if(balance == null){
            balance.setText("0");
        }


        name.setText(MainActivity.loggedAccount.name);
        email.setText(MainActivity.loggedAccount.email);
        balance.setText(String.valueOf(MainActivity.loggedAccount.balance));

        //=============Registrasi Renter Baru===================
        RegisterRenterButton = findViewById(R.id.RegisterRenterButton);
        RegisterButtonCard = findViewById(R.id.RegisterButtonCard);

        //=============Isi Registrasi Renter =============
        RegisterRenterConfirmation = findViewById(R.id.RegisterRenterConfirmation);
        RegisterRenterCancel = findViewById(R.id.RegisterRenterCancel);
        RenterRegistrationCard = findViewById(R.id.RenterRegistrationCard);
        RenterName = findViewById(R.id.RenterName);
        RenterAddress = findViewById(R.id.RenterAddress);
        RenterPhoneNumber = findViewById(R.id.RenterPhoneNumber);

        //============Renter Tersedia================
        RegisterDetailCard = findViewById(R.id.RegisterDetailCard);
        RenterNameFill = findViewById(R.id.RenterNameFillText);
        AddressFill = findViewById(R.id.AddressFillText);
        PhoneNumberFill = findViewById(R.id.PhoneNumberFillText);
        TopUpButton = findViewById(R.id.topUpButton);
        AmountBox = findViewById(R.id.AmountBox);
        PaymentButton = findViewById(R.id.PaymentList);

        //==============Back========================
        back = findViewById(R.id.backButton);

        if(MainActivity.loggedAccount.renter == null){
            RegisterButtonCard.setVisibility(CardView.VISIBLE);
            RegisterDetailCard.setVisibility(CardView.GONE);
            RenterRegistrationCard.setVisibility(CardView.GONE);

            RegisterRenterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RegisterButtonCard.setVisibility(CardView.GONE);
                    RenterRegistrationCard.setVisibility(CardView.VISIBLE);
                    RegisterDetailCard.setVisibility(CardView.GONE);

                    RegisterRenterConfirmation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Renter renter = requestRenter(MainActivity.loggedAccount.id, RenterName.getText().toString(), RenterAddress.getText().toString(), RenterPhoneNumber.getText().toString());

                        }

                    });
                    RegisterRenterCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RenterRegistrationCard.setVisibility(CardView.GONE);
                            RegisterDetailCard.setVisibility(CardView.GONE);
                            RegisterButtonCard.setVisibility(CardView.VISIBLE);
                        }
                    });
                }
            });

        }else{
            RegisterButtonCard.setVisibility(CardView.GONE);
            RegisterDetailCard.setVisibility(CardView.VISIBLE);
            RenterRegistrationCard.setVisibility(CardView.GONE);

            RenterNameFill.setText(MainActivity.loggedAccount.renter.username);
            PhoneNumberFill.setText(MainActivity.loggedAccount.renter.address);
            AddressFill.setText(MainActivity.loggedAccount.renter.phoneNumber);
            PaymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AboutMeActivity.this, BookingListActivity.class);
                    startActivity(intent);
                }
            });
        }

        TopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AmountBox.getText().toString().equalsIgnoreCase("0") || AmountBox.getText().toString().equalsIgnoreCase("" )){
                    System.out.println("Top Up Failed: Enter Required Top Up Amount!!") ;
                    Toast.makeText(mContext, "Top Up Failed: Enter Required Top Up Amount!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean topUp = requestTopUp(MainActivity.loggedAccount.id,Integer.parseInt(AmountBox.getText().toString()));
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutMeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     This method sends a request to register a renter with the given ID, username, address, and phone number. It updates the savedAccount field if the request is successful.
     @param id The ID of the renter
     @param username The username of the renter
     @param address The address of the renter
     @param phone The phone number of the renter
     @return null
     @throws NullPointerException
     */
    protected Renter requestRenter(int id, String username, String address, String phone ){
        mApiService.registerRenter(id, username, address, phone).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()){
                    Renter renter;
                    renter = response.body();
                    MainActivity.loggedAccount.renter = renter;
                    System.out.println("Renter berhasil didaftarkan");
                    Toast.makeText(mContext, "Berhasil register renter", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }

            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                Toast.makeText(mContext, "Gagal register renter", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    /**
     This method sends a request to top up the balance of the account with the given ID by the given amount. It updates the savedAccount field if the request is successful.
     @param id The ID of the account
     @param balance The amount to top up the balance by
     @return null
     */
    protected Boolean requestTopUp(int id, int balance ){
        System.out.println("Id: " + id);
        System.out.println("TopUp: " + balance);
        mApiService.topUp(id,balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Boolean topUpResult = response.body();
                    System.out.println("TOPUP SUCCESSFUL!!") ;
                    MainActivity.loggedAccount.balance += balance;
                    Toast.makeText(mContext, "Top Up Successful", Toast.LENGTH_SHORT).show();
                    Intent startIntent = getIntent();
                    finish();
                    startActivity(startIntent);

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("TOPUP ERROR!!");
                System.out.println(t.toString());
                Toast.makeText(mContext,"Top Up Failed",Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}