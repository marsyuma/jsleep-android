package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.BedType;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.City;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Facility;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Price;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Room;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.UtilsApi;

import java.util.ArrayList;

import retrofit2.*;

public class CreateRoomActivity extends AppCompatActivity {

    EditText roomName, roomPrice, roomSize, roomAddress;
    Spinner bedSpinner, citySpinner;
    Button makeRoom, cancel;
    CheckBox ac, refrig, wifi, bathub, balcony, restaurant, pool, fitness;
    ArrayList<Facility> facility = new ArrayList<Facility>();
    BedType bedType;
    Price price;
    City city;

    BaseApiService mApiService;
    Context mContext;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        mApiService = UtilsApi.getApiService();
        mContext = this;


        //Checkbox
        ac = findViewById(R.id.checkBoxAC);
        refrig = findViewById(R.id.checkBoxRefrigerator);
        wifi = findViewById(R.id.checkBoxWifi);
        bathub = findViewById(R.id.checkBoxBathub);
        balcony = findViewById(R.id.checkBoxBalcony);
        restaurant = findViewById(R.id.checkBoxRestaurant);
        pool = findViewById(R.id.checkBoxSwimmingPool);
        fitness = findViewById(R.id.checkBoxFitnessCenter);

        //EditText
        roomName = findViewById(R.id.RoomNameFill);
        roomPrice = findViewById(R.id.RoomPriceFill);
        roomSize = findViewById(R.id.RoomSizeFill);
        roomAddress = findViewById(R.id.RoomAddressFill);

        //Button
        makeRoom = findViewById(R.id.CreateRoomConfirmation);
        cancel = findViewById(R.id.CreateRoomCancel);

        //Spinner
        bedSpinner = (Spinner) findViewById(R.id.spinnerBed);
        citySpinner = (Spinner) findViewById(R.id.spinnerCity);

        bedSpinner.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        citySpinner.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));

        makeRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ac.isChecked()) {
                    facility.add(Facility.AC);
                }
                if (refrig.isChecked()) {
                    facility.add(Facility.Refrigerator);
                }
                if (wifi.isChecked()) {
                    facility.add(Facility.WiFi);
                }
                if (bathub.isChecked()) {
                    facility.add(Facility.Bathtub);
                }
                if (balcony.isChecked()) {
                    facility.add(Facility.Balcony);
                }
                if (restaurant.isChecked()) {
                    facility.add(Facility.Restaurant);
                }
                if (pool.isChecked()) {
                    facility.add(Facility.SwimmingPool);
                }
                if (fitness.isChecked()) {
                    facility.add(Facility.FitnessCenter);
                }
                String bed = bedSpinner.getSelectedItem().toString();
                String cityStr = citySpinner.getSelectedItem().toString();
                bedType = BedType.valueOf(bed);
                city = City.valueOf(cityStr);

                Integer priceObj = new Integer(roomPrice.getText().toString());
                Integer sizeObj = new Integer(roomSize.getText().toString());

                int priceInt = priceObj.parseInt(roomPrice.getText().toString());
                int sizeInt = sizeObj.parseInt(roomSize.getText().toString());
                //price.price = priceInt;
                Room room = requestRoom(MainActivity.loggedAccount.id, roomName.getText().toString(), sizeInt, priceInt, facility, city, roomAddress.getText().toString(), bedType);

            }
        });


    }

    protected Room requestRoom(int id, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        mApiService.room(id, name, size, price, facility, city, address, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(mContext, "Berhasil buat room", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(mContext, "gagal buat room", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}