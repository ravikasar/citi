package com.citi.card;

import java.util.Date;

/**
 *
 */
public class Transaction {


    String retailer;
    Date date;
    double amount;

    public Transaction(String retailer, Date date, double amount) {
        this.retailer = retailer;
        this.date = date;
        this.amount = amount;
    }


    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return new StringBuilder()
                .append("Date: ").append(date)
                .append(" Retailer: ").append(retailer)
                .append(" Amount: ").append(amount)
                .toString();
    }
}
