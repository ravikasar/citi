package com.citi.card.services;

import com.citi.card.ElectronicCard;

/**
 * THe interface for Card Services

 */
public interface CardTransactionService {

    boolean authorizeAndRebalance(long cardNumber, String retailerName, double amount);
    boolean addBalance(long cardNumber, double additionalBalance);
    boolean validatePin(long cardNumber, int pin);

}
