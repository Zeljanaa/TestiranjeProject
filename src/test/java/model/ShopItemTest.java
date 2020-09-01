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
public class ShopItemTest {
    private static ShopItem shopItem;
    public ShopItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        shopItem = new ShopItem(1,"Artikal", 70.0f,5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class ShopItem.
     */
    @Test
    public void testGetName() {
        assertEquals("Artikal", shopItem.getName());
    }

    /**
     * Test of setName method, of class ShopItem.
     */
    @Test
    public void testSetName() {
        shopItem.setName("Artikal2");
        assertEquals("Artikal2", shopItem.getName());
    }

    /**
     * Test of getPrice method, of class ShopItem.
     */
    @Test
    public void testGetPrice() {
        assertEquals(300.0f,shopItem.getPrice(),0.2);
    }

    /**
     * Test of setPrice method, of class ShopItem.
     */
    @Test
    public void testSetPrice() {
        shopItem.setPrice(300.0f);
        assertEquals(400.0f, shopItem.getPrice(),0.2);
    }

    /**
     * Test of getAmount method, of class ShopItem.
     */
    @Test
    public void testGetAmount() {
        assertEquals(5,shopItem.getAmount());
    }

    /**
     * Test of setAmount method, of class ShopItem.
     */
    @Test
    public void testSetAmount() {
        shopItem.setAmount(6);
        assertEquals(6, shopItem.getAmount());
    }
    
}
