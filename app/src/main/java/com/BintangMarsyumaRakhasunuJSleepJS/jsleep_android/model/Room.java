package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Room extends Serializable
{
    public ArrayList<Date> booked;
    public int size;
    public int accountId;
    public String name;
    public String address;
    public ArrayList<Facility> facility;
    public BedType bedType;
    public City city;
    public Price price;

    public Room(int id) {
        super(id);
    }
}