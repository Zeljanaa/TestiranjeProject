/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import model.Client;
import model.DeliveryService;
import model.ShopItem;
import model.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.ShopItemService;


/**
 *
 * @author Zeljana
 */
public class TransactionDAOTest {
    private static Date date;
    private static int testNumber;
    private static TransactionDAO tsdao;
    private static ShopItemDAO sidao;
    private static ClientDAO cdao;
    private static DeliveryServiceDAO dsdao;
    public TransactionDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        date = new Date();
        testNumber = 0;
        dsdao = new DeliveryServiceDAO();
        tsdao = new TransactionDAO();
        sidao = new ShopItemDAO();
        cdao = new ClientDAO();
        testNumber = 0;
        tsdao = new TransactionDAO();
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

    
        @Test
    public void getOneTest() {
        testNumber++;
        System.out.println("getOneTest");
        boolean expResult = true;
        Client c = new Client(testNumber,"Zeljana555","Zeljana555","Zeljana555");
        DeliveryService d = new DeliveryService(testNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(testNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        tsdao.insertOne(new Transaction(testNumber, 400.0f, 30, new Date(), testNumber, testNumber, testNumber, 400.0f ));
        Transaction gottenTR = tsdao.getOne(testNumber);
        boolean result = Objects.equals(testNumber, gottenTR.getId());
        
      
        assertEquals(expResult, result);
    }
    
    @Test
    public void getAllTest() {
        testNumber++;
        System.out.println("getAllTest");
        boolean expResult = true;
        Client c = new Client(testNumber,"Zeljana998","Zeljana998","Zeljana998");
        DeliveryService d = new DeliveryService(testNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(testNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        tsdao.insertOne(new Transaction(testNumber, 400.0f, 30, new Date(), testNumber, testNumber, testNumber, 400.0f ));
        ArrayList<Transaction> gottenSIs = tsdao.getAll();
        boolean result = gottenSIs.size() > 0;
      
        assertEquals(expResult, result);
    }
    
    @Test
    public void deleteOneTest() {
        testNumber++;
        System.out.println("deleteOneTest");
        boolean expResult = true;
        Client c = new Client(testNumber,"Zeljana100","Zeljana100","Zeljana100");
        DeliveryService d = new DeliveryService(testNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(testNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        tsdao.insertOne(new Transaction(testNumber, 400.0f, 30, new Date(), testNumber, testNumber, testNumber, 400.0f ));
        boolean result = tsdao.deleteOne(testNumber);
      
        assertEquals(expResult, result);
        
        boolean result2 = tsdao.getOne(testNumber) == null;
        assertEquals(expResult,result2);
    }
    
}
