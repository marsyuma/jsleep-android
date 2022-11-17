package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.R.id;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Room;
import com.google.gson.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        InputStream filepath = null;
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Room> roomList  = new ArrayList<Room>();
        try {
            filepath = getAssets().open("randomRoomList.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
            Room[] acc = gson.fromJson(reader, Room[].class);
            Collections.addAll(roomList, acc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Room room : roomList) {
            name.add(room.name);
        }
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        ListView view = (ListView) findViewById(R.id.ListName);
        view.setAdapter(roomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        Intent aboutMeIntent = new Intent(MainActivity.this,AboutMeActivity.class);
        switch (menu.getItemId()){

            case R.id.AccountIcon:
                Toast.makeText(this, "About me", Toast.LENGTH_SHORT).show();
                startActivity(aboutMeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(menu);
        }

    }
}