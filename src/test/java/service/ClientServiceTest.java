/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import java.util.ArrayList;
import model.Client;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Zeljana
 */
public class ClientServiceTest {
    private static ClientService cs;
    private static ClientDAO cdao;
    private static int testNumber;
    public ClientServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        cs = new ClientService();
        cdao = new ClientDAO();
        testNumber = 0;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class ClientService.
     */
    @Test
    public void testRegistrationNameFromDB() {
        testNumber++;
        System.out.println("testRegistrationNameFromDB");
        System.out.println(testNumber);
        boolean result = cs.register("Zeljana501", "Vulovic998", "123");
        boolean expResult = true;
        assertEquals(expResult, result);
        
        Client dbClient = cdao.getOne(testNumber);
        assertEquals("Zeljana501", dbClient.getName());
    }
    
    @Test
    public void testRegistrationUsernameFromDB() {
        testNumber++;
        System.out.println("testRegistrationUsernameFromDB");
        System.out.println(testNumber);
        boolean result = cs.register("Zeljana", "Vulovic501", "123");
        boolean expResult = true;
        assertEquals(expResult, result);
        
        Client dbClient = cdao.getOne(testNumber);
        assertEquals("Vulovic501", dbClient.getUsername());
    }
    
    @Test
    public void testRegistrationPasswordFromDB() {
        testNumber++;
        System.out.println("testRegistrationPasswordFromDB");
        System.out.println(testNumber);
        boolean result = cs.register("Zeljana", "Vulovic502", "1234");
        boolean expResult = true;
        assertEquals(expResult, result);
        
        Client dbClient = cdao.getOne(testNumber);
        assertEquals("1234", dbClient.getPassword());
    }
    
    @Test
    public void testRegistrationWithoutName() {
        boolean result = cs.register("", "Vulovic503", "123");
        
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getName().equals("")){
                result = false;
                break;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(!expResult, result);
    }
    
    @Test
    public void testRegistrationWithoutUsername() {
        boolean result = cs.register("Zeljana", "", "123");
        
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getUsername().equals("")){
                result = false;
                break;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(!expResult, result);
    }
    
    @Test
    public void testRegistrationWithoutPassword() {
        boolean result = cs.register("Zeljana", "Vulovic505", "");
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getPassword().equals("")){
                result = false;
                break;
            }
        }
        
        if(!result){
            testNumber++;
        }
        assertEquals(!expResult, result);
    }
    
    @Test
    public void testRegistrationDuplicateUsername() {
        testNumber++;
        cs.register("Zeljana", "Vulovic506", "123");
        boolean result = cs.register("Zeljana", "Vulovic506", "123");
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLoginExistingUserNotNull() {
        testNumber++;
        cdao.insertOne(new Client(testNumber,"Zeljana", "Zekss", "123"));
        Client loggedClient = cs.login("Zekss", "123");
        assertNotEquals(null, loggedClient);
    }
    
    @Test
    public void testLoginExistingUserIsThatUser() {
        testNumber++;
        cdao.insertOne(new Client(testNumber,"Zeljana508", "Zekss501", "123"));
        Client loggedClient = cs.login("Zekss501", "123");
        assertEquals(loggedClient.getUsername(), "Zekss501");
        assertEquals(loggedClient.getName(), "Zeljana508");
        assertEquals(loggedClient.getPassword(), "123");
        
    }
    
    @Test
    public void testLoginNonExistingUser() {
        Client loggedClient = cs.login("aaa", "aaa");
        assertEquals(null, loggedClient);
    }
        
    @Test
    public void testDeleteUser() {
        testNumber++;
        Client c = new Client(testNumber,"Zeljana510", "Zekss511", "123");
        cdao.insertOne(c);
        cs.deleteUser(c);
        boolean result = true;
        for(Client x: cdao.getAll()){
            if(x.getId() == testNumber){
                result = false;
            }
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteUserNull() {
        Client c = null;
        boolean result = cs.deleteUser(c);
        assertEquals(false, result);
    }
    
    
    @Test
    public void testUpdateInfoName() {
        testNumber++;
        System.out.println(testNumber);
        Client c = new Client(25,"Zeljana", "Zekss512", "123");
        cdao.insertOne(c);
        boolean result = false;
        cs.updateInfo(c, "Andrija", "123", "123");
        for(Client x: cdao.getAll()){
            if(x.getId() == testNumber){
                if(x.getName().equals("Andrija") && x.getPassword().equals("123"))
                result = true;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void testUpdateInfoPass() {
        testNumber++;
        System.out.println(testNumber);
        Client c = new Client(25,"Zeljana", "Zekss513", "123");
        cdao.insertOne(c);
        boolean result = false;
        cs.updateInfo(c, "Zeljana", "123", "456");
        for(Client x: cdao.getAll()){
            if(x.getId() == testNumber){
                if(x.getName().equals("Zeljana") && x.getPassword().equals("456"))
                result = true;
            }
        }
        assertEquals(true, result);
    }


    
}
