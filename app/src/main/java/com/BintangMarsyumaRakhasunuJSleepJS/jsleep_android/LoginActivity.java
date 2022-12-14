package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Account;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.UtilsApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 This is the activity class for the login screen.
 @author Bintang MR
 */
public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText email,password;
    Context mContext;

    ImageView exit;

/**
 * Method to initialize the activity.
 * @param savedInstanceState
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_login);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        TextView register = findViewById(R.id.RegisterClick);
        email = findViewById(R.id.EmailBox);
        password = findViewById(R.id.PasswordBox);

        exit = findViewById(R.id.exit);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(move);
            }
        });
        Button loginbutton = findViewById(R.id.LoginButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestLogin();

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });

    }

    protected Account requestAccount(){
        mApiService.getAccount(0).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("On Failure");
                Toast.makeText(mContext, "no Account id = 0", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    /**
     * Method to send a request to the backend to login.
     * @return
     */
    protected Account requestLogin(){
        mApiService.login(email.getText().toString(),password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    MainActivity.loggedAccount = account;
                    System.out.println(account.toString());
                    Intent move = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("On Failure");
                Toast.makeText(mContext, "Email atau Password salah", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}