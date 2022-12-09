package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model;


/**
 * Class for processing payment
 *
 * @author Bintang MR
 * @version TP 6 (KJ lord JS)
 */

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Payment extends Invoice {

    public int roomId;
    public Date from;
    public Date to;

    public Payment(int id) {
        super(id);
    }

}

