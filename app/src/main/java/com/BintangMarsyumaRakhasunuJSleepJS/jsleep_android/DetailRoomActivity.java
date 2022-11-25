package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.*;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.*;

public class DetailRoomActivity extends AppCompatActivity {

    TextView roomName, roomPrice, roomSize, roomAddress, roomBedtype;
    CheckBox ac, refrig, wifi, bathub, balcony, restaurant, pool, fitness;

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



    }
}