/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zeljana
 */
public class TransactionTest {
    private static Transaction trans;
    private static Date date;
    public TransactionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        date = new Date();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() { 
        trans = new Transaction(1, 300.0f, 5, date, 1, 1, 1, 300.0f);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTotalPrice method, of class Transaction.
     */
    @Test
    public void testGetTotalPrice() {
        assertEquals(300.0f, trans.getTotalPrice(),0.2);
    }

    /**
     * Test of setTotalPrice method, of class Transaction.
     */
    @Test
    public void testSetTotalPrice() {
        trans.setTotalPrice(70.0f);
        assertEquals(70.0f,trans.getTotalPrice(),0.2);
    }

    /**
     * Test of getAmount method, of class Transaction.
     */
    @Test
    public void testGetAmount() {
        assertEquals(5, trans.getAmount());
    }

    /**
     * Test of setAmount method, of class Transaction.
     */
    @Test
    public void testSetAmount() {
        trans.setAmount(7);
        assertEquals(7, trans.getAmount());
    }

    /**
     * Test of getDate method, of class Transaction.
     */
    @Test
    public void testGetDate() {
        assertEquals(date, trans.getDate());
    }

    /**
     * Test of setDate method, of class Transaction.
     */
    @Test
    public void testSetDate() {
        date = new Date();
        trans.setDate(date);
        assertEquals(date, trans.getDate());
    }

    /**
     * Test of getClientId method, of class Transaction.
     */
    @Test
    public void testGetClientId() {
        assertEquals(1, (int) trans.getId());
    }

    /**
     * Test of setClientId method, of class Transaction.
     */
    @Test
    public void testSetClientId() {
        trans.setId(4);
        assertEquals(4, (int) trans.getId());
    }

    /**
     * Test of getShopItemId method, of class Transaction.
     */
    @Test
    public void testGetShopItemId() {
        assertEquals(1,(int) trans.getShopItemId());
    }

    /**
     * Test of setShopItemId method, of class Transaction.
     */
    @Test
    public void testSetShopItemId() {
        trans.setShopItemId(2);
        assertEquals(2, (int) trans.getShopItemId());
    }

    /**
     * Test of getDeliveryServiceId method, of class Transaction.
     */
    @Test
    public void testGetDeliveryServiceId() {
        assertEquals(1, (int) trans.getDeliveryServiceId());
    }

    /**
     * Test of setDeliveryServiceId method, of class Transaction.
     */
    @Test
    public void testSetDeliveryServiceId() {
        trans.setDeliveryServiceId(7);
        assertEquals(7,(int) trans.getDeliveryServiceId());
    }

    /**
     * Test of getDistance method, of class Transaction.
     */
    @Test
    public void testGetDistance() {
        assertEquals(300.0f, trans.getDistance(),0.2);
    }

    /**
     * Test of setDistance method, of class Transaction.
     */
    @Test
    public void testSetDistance() {
        trans.setDistance(70.0f);
        assertEquals(70.0f, trans.getDistance(),0.2);
    }

    /**
     * Test of compareTo method, of class Transaction.
     */
    @Test
    public void testCompareTo() {
        Transaction secondTrans = new Transaction();
        
        date = new Date();
        trans.setDate(date);
        secondTrans.setDate(date);
        assertEquals(0, trans.compareTo(secondTrans));
    }
    
}
