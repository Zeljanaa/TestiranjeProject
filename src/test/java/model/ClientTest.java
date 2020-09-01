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
public class ClientTest {
    private static Client client;
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        client = new Client(1,"Zeljana", "Zeks", "123");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        String result = client.getName();
        assertEquals("Zeljana", result);
    }

    /**
     * Test of setName method, of class Client.
     */
    @Test
    public void testSetName() {
        client.setName("Andrija");
        assertEquals(client.getName(), "Andrija");
    }

    /**
     * Test of getUsername method, of class Client.
     */
    @Test
    public void testGetUsername() {
        String result = client.getUsername();
        assertEquals("Zeks", result);
    }

    /**
     * Test of setUsername method, of class Client.
     */
    @Test
    public void testSetUsername() {
        client.setUsername("Zekss");
        assertEquals("Zekss", client.getUsername());
    }

    /**
     * Test of getPassword method, of class Client.
     */
    @Test
    public void testGetPassword() {
        assertEquals("123", client.getPassword());
    }

    /**
     * Test of setPassword method, of class Client.
     */
    @Test
    public void testSetPassword() {
        client.setPassword("456");
        assertEquals("456", client.getPassword());
    }
    
}
