package com.citi.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class ElectronicCard {

    long cardNumber;
    int pin = 0000;

    Double balance = new Double(0.0);
    Date expiryDate;

    List<Transaction> transactions = new ArrayList<>();


    public ElectronicCard(Date expiryDate, long cardNumber, int pin) {
        this.expiryDate = expiryDate;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double newBalance) {
        this.balance = newBalance;
    }
    /**
     * Get the Transactions on the card
     * @return
     */
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
}
