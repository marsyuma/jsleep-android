package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model;



public class Renter extends Serializable {
    public String phoneNumber;
    public String address = "";
    public String username = "";
    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    public static final String REGEX_PHONE_NUMBER = "^[0-9]{9,12}$";


    public Renter(int id) {
        super(id);
    }
}
