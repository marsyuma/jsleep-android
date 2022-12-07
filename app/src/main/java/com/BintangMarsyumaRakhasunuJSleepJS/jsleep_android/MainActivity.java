package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.R.id;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Account;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Room;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.UtilsApi;
import com.google.gson.*;

import retrofit2.*;

public class MainActivity extends AppCompatActivity {

    BaseApiService mApiService;
    Context mContext;
    Button next, prev;
    List<String> nameStr;
    List<Room> temp ;
    List<Room> acc ;
    ListView list;
    int currentPage;
    public static Account loggedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        next = findViewById(id.NextButton);
        prev = findViewById(id.PrevButton);


        list = findViewById(id.ListName);
        list.setOnItemClickListener(this::onItemClick);


        System.out.println("test");
        acc = getRoomList(0,10);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(temp.size()>currentPage){
                    currentPage=1;
                    return;
                }
                currentPage++;
                try {
                    acc = getRoomList(currentPage-1, 1);  //return null
                    Toast.makeText(mContext, "page "+currentPage, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage<=1){
                    currentPage=1;
                    Toast.makeText(mContext, "this is the first page", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentPage--;
                try {
                    acc = getRoomList(currentPage-1, 1);  //return null
                    Toast.makeText(mContext, "page "+currentPage, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
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
        Intent CreateRoomIntent = new Intent(MainActivity.this,CreateRoomActivity.class);
        switch (menu.getItemId()){
            case R.id.AccountIcon:
                Toast.makeText(this, "About me", Toast.LENGTH_SHORT).show();
                startActivity(aboutMeIntent);
                return true;
            case id.AddIcon:
                Toast.makeText(this, "Create Room", Toast.LENGTH_SHORT).show();
                startActivity(CreateRoomIntent);
                return true;
            default:
                return super.onOptionsItemSelected(menu);
        }

    }
    protected List<Room> getRoomList(int page, int size) {
        mApiService.getAllRoom(page, size).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    temp = response.body();
                    nameStr = new ArrayList<>();
                    System.out.println(temp);
                    for (Room room : temp) {
                        nameStr.add(room.name);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, nameStr);
                    list.setAdapter(adapter);
                    Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Failed to get room list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Toast.makeText(mContext, "Failed to get room list", Toast.LENGTH_SHORT).show();
            }
        });
        return temp;
    }
    protected List<String> getName(List<Room> roomList){
        List<String> nameStr = new ArrayList<>();
        for(Room room : roomList){
            nameStr.add(room.name);
        }
        return nameStr;
    }
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, DetailRoomActivity.class);
        DetailRoomActivity.tempRoom = temp.get(position);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }
}