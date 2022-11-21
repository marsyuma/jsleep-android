package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model;

import android.content.Context;
import android.widget.EditText;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request.BaseApiService;

import javax.security.auth.callback.Callback;

/**
 * Write a description of enum Type here.
 *
 * @author Bintang Marsyuma Rakhasunu
 * @version Post-Test CS 4
 */
public class Account extends Serializable
{

    public double balance;
    public Renter renter;
    public String name;
    public String email;
    public String password;
    public  static final String REGEX_EMAIL = "^[a-zA-Z0-9 ][a-zA-Z0-9]+@[a-zA-Z.]+?\\.[a-zA-Z]+?$";
    public  static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public Account(int id) {
        super(id);

    }

    @Override
    public String toString(){
        return "Account{" +
                "balance=" + balance +
                ", email= " + email + '\'' +
                ", name= " + name + '\'' +
                ", password= " + password + '\'' +
                ", renter= " + renter +
                "}";

    }

}