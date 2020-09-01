/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DeliveryServiceTest {
    private static DeliveryService service;
    public DeliveryServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        service = new DeliveryService(1, "Servis", 400.0f, 400.0f);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class DeliveryService.
     */
    @Test
    public void testGetName() {
        assertEquals("Servis", service.getName());

    }

    /**
     * Test of setName method, of class DeliveryService.
     */
    @Test
    public void testSetName() {
        service.setName("ServisSet");
        assertEquals("ServisSet", service.getName());
    }

    /**
     * Test of getStartingPrice method, of class DeliveryService.
     */
    @Test
    public void testGetStartingPrice() {
        assertEquals(400.0f,service.getStartingPrice(),0.2);
    }

    /**
     * Test of setStartingPrice method, of class DeliveryService.
     */
    @Test
    public void testSetStartingPrice() {
        service.setStartingPrice(200.0f);
        assertEquals(200.0f, service.getStartingPrice(),0.2);
    }

    /**
     * Test of getPricePerKilometer method, of class DeliveryService.
     */
    @Test
    public void testGetPricePerKilometer() {
        assertEquals(400.0f,service.getPricePerKilometer(),0.2);
    }

    /**
     * Test of setPricePerKilometer method, of class DeliveryService.
     */
    @Test
    public void testSetPricePerKilometer() {
        service.setPricePerKilometer(50.0f);
        assertEquals(30.0f, service.getPricePerKilometer(),0.2);
    }
}
