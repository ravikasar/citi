package com.citi.card;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.citi.card.services.CardTransactionService;
import com.citi.card.services.impl.CardTransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 */
public class CardTransactionServiceTest {

    CardTransactionService service;

    @Before
    public void addCard(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.YEAR, 2020);
        service = new CardTransactionServiceImpl();
        CardsCache.addCard(new ElectronicCard(cal.getTime(), 1234123412341234l, 1234));
        CardsCache.addCard(new ElectronicCard(new Date(), 1234123412344321l, 1234));

    }
    @Test
    public void testCardServices() {
        assertTrue(service.validatePin(1234123412341234l, 1234));
    }

    @Test
    public void testCardAuthorizationWithNoBalanceInCard() {
        assertFalse(service.authorizeAndRebalance(1234123412341234l, "SuperSales", 120.00));
    }

    @Test
    public void testAddBalanceToCard() {
        service.addBalance(1234123412341234l, 200.00);
        assertEquals(CardsCache.getCard(1234123412341234l).getBalance(), 200.00, 0);
    }

    @Test
    public void testTransactionWithLessBalance() {
        service.addBalance(1234123412341234l, 100);
        assertFalse(service.authorizeAndRebalance(1234123412341234l, "SuperSales", 200.0));
        assertEquals(CardsCache.getCard(1234123412341234l).getBalance(), 100, 0);
    }

    @Test
    public void testBalanceAfterTransactionApproved() {
        service.addBalance(1234123412341234l, 100);
        assertTrue(service.authorizeAndRebalance(1234123412341234l, "SuperSales", 20.0));
        assertEquals(CardsCache.getCard(1234123412341234l).getBalance(), 80, 0);
    }

    @Test
    public void testBalanceAfterTransactionIsNotApproved() {
        service.addBalance(1234123412341234l, 100);
        assertFalse(service.authorizeAndRebalance(1234123412341234l, "SuperSales", 120.0));
        assertEquals(CardsCache.getCard(1234123412341234l).getBalance(), 100, 0);
    }

    @Test
    public void testTransactionOnExpiredCard() {
        service.addBalance(1234123412344321l, 100);
        assertFalse(service.authorizeAndRebalance(1234123412344321l, "SuperSales", 20.0));
    }

}
