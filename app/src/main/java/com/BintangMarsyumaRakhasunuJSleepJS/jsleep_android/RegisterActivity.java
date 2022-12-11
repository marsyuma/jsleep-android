package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Account;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText name, email,password;
    Context mContext;
    Button cancel, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancel = findViewById(R.id.backButton);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        name = findViewById(R.id.NameFillBox);
        email = findViewById(R.id.EmailFillBox);
        password = findViewById(R.id.PasswordFillBox);
        register = findViewById(R.id.RegisterButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = requestRegister();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(move);
            }
        });

    }
    protected Account requestRegister(){
        mApiService.register(name.getText().toString(), email.getText().toString(),password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    MainActivity.loggedAccount = account;
                    System.out.println(account.toString());
                    Intent move = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("On Failure");
                Toast.makeText(mContext, "Register Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}