package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Payment;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.UtilsApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.*;
import retrofit2.http.Query;

public class CreatePaymentActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;

    Payment payment;

    TextView roomName;

    CardView CardViewBook;
    Button BookingButton;

    EditText BookingFrom, BookingTo;

    CardView CardViewConfirmCancel;
    Button ConfirmButton, CancelButton;

    final String REGEX_DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mApiService = UtilsApi.getApiService();
        mContext = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_payment);

        roomName = findViewById(R.id.RoomNamePaymentFill);
        roomName.setText(DetailRoomActivity.tempRoom.name);
        BookingButton = findViewById(R.id.CreatePaymentButton);

        //==============Booking Biasa================
        CardViewBook = findViewById(R.id.CardViewBook);
        BookingFrom = findViewById(R.id.FromFillText);
        BookingTo = findViewById(R.id.ToFillText);

        //==============Confirm Cancel================
        CardViewConfirmCancel = findViewById(R.id.CardViewConfirmCancel);
        ConfirmButton = findViewById(R.id.ConfirmPaymentButton);
        CancelButton = findViewById(R.id.CancelPaymentButton);

        CardViewBook.setVisibility(CardView.VISIBLE);
        CardViewConfirmCancel.setVisibility(CardView.GONE);
        BookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPayment(MainActivity.loggedAccount.id,
                        DetailRoomActivity.tempRoom.accountId,
                        DetailRoomActivity.tempRoom.id,
                        BookingFrom.getText().toString(),
                        BookingTo.getText().toString());

            }
        });

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestConfirmPayment(payment.id);
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCancelPayment(payment.id);
            }
        });

    }
    protected Payment requestPayment(int buyerId, int renterId, int roomId, String fromDate, String toDate) {

        System.out.println(fromDate);
        System.out.println(toDate);

        Pattern pattern = Pattern.compile(REGEX_DATE_PATTERN);
        Matcher matcher = pattern.matcher(fromDate);
        Matcher matcher2 = pattern.matcher(toDate);

        boolean isMatched = matcher.matches();
        boolean isMatched2 = matcher2.matches();



        if(isMatched && isMatched2) {
            mApiService.createPayment(buyerId, renterId, roomId, fromDate, toDate).enqueue(new Callback<Payment>() {
                @Override
                public void onResponse(Call<Payment> call, Response<Payment> response) {
                    if (response.isSuccessful()) {
                        payment = response.body();
                        CardViewBook.setVisibility(CardView.GONE);
                        CardViewConfirmCancel.setVisibility(CardView.VISIBLE);
                        Toast.makeText(mContext, "Payment Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Payment Failed to Create", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Payment> call, Throwable t) {
                    Toast.makeText(mContext, "Payment Failed to Create", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(mContext, "Date format is not valid", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    protected Boolean requestConfirmPayment(int paymentId) {
        mApiService.acceptPayment(paymentId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean isConfirmed = response.body();
                    if(isConfirmed) {
                        Toast.makeText(mContext, "Payment Confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "Payment Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }
    protected Boolean requestCancelPayment(int paymentId) {
        mApiService.cancelPayment(paymentId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean isConfirmed = response.body();
                    if(isConfirmed) {
                        Toast.makeText(mContext, "Payment Canceled", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "Payment Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     This method is called when a menu item is selected.
     @param item the selected menu item
     @return true if the menu item was handled successfully, false otherwise
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                Intent move = new Intent(CreatePaymentActivity.this, MainActivity.class);
                startActivity(move);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     This method is called to prepare the options menu.
     @param menu the options menu to prepare
     @return true if the menu was prepared successfully, false otherwise
     */
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem acc = menu.findItem(R.id.AccountIcon);
        MenuItem box = menu.findItem(R.id.AddIcon);
        MenuItem search = menu.findItem(R.id.SearchIcon);
        search.setVisible(false);
        acc.setVisible(false);
        box.setVisible(false);
        return true;
    }

}