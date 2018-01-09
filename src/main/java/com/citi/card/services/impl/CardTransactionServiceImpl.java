package com.citi.card.services.impl;

import com.citi.card.CardsCache;
import com.citi.card.ElectronicCard;
import com.citi.card.Transaction;
import com.citi.card.services.CardTransactionService;

import java.util.Date;

/**
 * Implementation of the card services interface
 */


public class CardTransactionServiceImpl implements CardTransactionService {

    @Override
    public boolean authorizeAndRebalance(long cardNumber, String retailerName, double amount) {
        ElectronicCard card = CardsCache.getCard(cardNumber);

        if(card == null) {
            return false;
        }
        synchronized(card.getBalance()) {

            if (card.getExpiryDate().getTime() > System.currentTimeMillis() && card.getBalance() > amount) {
                card.setBalance(card.getBalance() - amount);
                card.getTransactions().add(new Transaction(retailerName, new Date(), amount));
                return true;
            }
        }
        return false;
    }

    public boolean addBalance(long cardNumber, double additionalBalance) {
        ElectronicCard card = CardsCache.getCard(cardNumber);

        if(card == null) {
            return false;
        }
        card.setBalance(card.getBalance() + additionalBalance);
        return true;
    }

    /**

     * Method to validate teh Card pin
     * @param pin
     * @return
     */
    public boolean validatePin(long cardNumber, int pin) {
        ElectronicCard card = CardsCache.getCard(cardNumber);

        if (card != null && pin == card.getPin()) {
            return true;
        }
        return false;
    }

}