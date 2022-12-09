package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model;


/**
 * The `Invoice` class represents an invoice for a hotel room rental.
 *
 * @author Bintang MR
 * @version PT Modul 9
 */


public class Invoice extends Serializable {
    public enum RoomRating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD
    }

    public enum PaymentStatus{
        FAILED,
        WAITING,
        SUCCESS
    }
    public int buyerId;
    public int renterId;
    public RoomRating rating;
    public PaymentStatus status;


    public Invoice(int id) {
        super(id);
    }

    /**
     * Returns a string representation of the invoice.
     *
     * @return a string representation of the invoice
     */
    public String print(){
        return ("buyerId: " + this.buyerId + " renterId: " + this.renterId);
    }
}
