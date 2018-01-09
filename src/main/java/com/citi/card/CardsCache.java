package com.citi.card;

import java.util.HashMap;

public class CardsCache {

    private HashMap<Long, ElectronicCard> cards = new HashMap<>();

    private static CardsCache cache = new CardsCache();

    private CardsCache() {

    }

    public static void addCard(ElectronicCard card) {
        cache.cards.put(card.getCardNumber(), card);
    }

    public static ElectronicCard getCard(long cardNumber) {
        return cache.cards.get(cardNumber);
    }
}
