package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.*;
/**
 * DetailRoomActivity is an activity that displays detailed information about a room,
 * such as its name, price, size, address, bed type, and facilities.
 * The user can also place an order for the room from this activity.
 *
 * @author Bintang MR
 */
public class DetailRoomActivity extends AppCompatActivity {

    TextView roomName, roomPrice, roomSize, roomAddress, roomBedtype, city;
    CheckBox ac, refrig, wifi, bathub, balcony, restaurant, pool, fitness;

    Button booking, cancel;
    public static Room tempRoom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);

        roomName = findViewById(R.id.NameFillText);
        roomPrice = findViewById(R.id.PriceFillText);
        roomSize = findViewById(R.id.SizeFillText);
        roomAddress = findViewById(R.id.RoomAddressFillText);
        roomBedtype = findViewById(R.id.BedTypeFillText);
        city = findViewById(R.id.CityFillText);
        cancel = findViewById(R.id.backButton);
        booking = findViewById(R.id.BookButton);

        ac = findViewById(R.id.checkboxDetailac);
        refrig = findViewById(R.id.checkboxDetailrefrigerator);
        wifi = findViewById(R.id.checkboxDetailwifi);
        bathub = findViewById(R.id.checkboxDetailbathub);
        balcony = findViewById(R.id.checkboxDetailbalcony);
        restaurant = findViewById(R.id.checkboxDetailrestaurant);
        pool = findViewById(R.id.checkboxDetailpool);
        fitness = findViewById(R.id.checkboxDetailfitness);

        roomName.setText(tempRoom.name);
        roomPrice.setText(String.valueOf(tempRoom.price.price));
        roomSize.setText(String.valueOf(tempRoom.size));
        roomAddress.setText(tempRoom.address);
        roomBedtype.setText(tempRoom.bedType.toString());
        city.setText(tempRoom.city.toString());

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRoomActivity.this, CreatePaymentActivity.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < tempRoom.facility.size(); i++) {
            if (tempRoom.facility.get(i).equals(Facility.AC )) {
                ac.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.Refrigerator)) {
                refrig.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.WiFi)) {
                wifi.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.Bathtub)) {
                bathub.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.Balcony)) {
                balcony.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.Restaurant)) {
                restaurant.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.SwimmingPool)) {
                pool.setChecked(true);
            } else if (tempRoom.facility.get(i).equals(Facility.FitnessCenter)) {
                fitness.setChecked(true);
            }
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


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
                Intent move = new Intent(DetailRoomActivity.this, MainActivity.class);
                startActivity(move);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}