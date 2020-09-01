/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import db.DeliveryServiceDAO;
import db.ShopItemDAO;
import db.TransactionDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

/**
 *
 * @author Zeljana
 */
public class ShopItemServiceTest {
    private static ShopItemService service;
    private static ShopItemDAO sdao;
    private static ClientDAO cdao;
    private static DeliveryServiceDAO dsdao;
    private static int testNumber;
    private static TransactionDAO tdao;
    private static SimpleDateFormat sdf;
//    private static TransactionService ts = new TransactionService();
    public ShopItemServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        tdao = new TransactionDAO();
        sdao = new ShopItemDAO();
        cdao = new ClientDAO();
        dsdao = new DeliveryServiceDAO();
        testNumber = 0;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        service = new ShopItemService();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of postItem method, of class ShopItemService.
     */
    @Test
    public void testPostItem() {
        testNumber++;
        boolean result = service.postItem("Artikal", 50.0f, 20);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(true, result);
        assertEquals("Artikal", gottenItem.getName());
        assertEquals(50.0f, gottenItem.getPrice(),0.2);
        assertEquals(20, gottenItem.getAmount());
    }

    @Test
    public void testPostItemWithoutName() {
        boolean result = service.postItem("", 50.0f, 20);
        assertEquals(false, result);
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == testNumber+1 && x.getName().equals("")){
                System.out.println(testNumber);
                System.out.println(x.getId());
                System.out.println(x.getName());
                result = true;
            }
        }
        if(result == true){ testNumber ++; }
        assertEquals(false, result);
    }
    
    @Test
    public void testPostItemNegativePrice() {
        boolean result = service.postItem("Artikal", -1.0f, 20);
        assertEquals(false, result);
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == testNumber+1 && x.getPrice() == -1.0f){
                result = true;
            }
        }
        if(result == true){ testNumber ++; }
        assertEquals(false, result);
    }
    
    @Test
    public void testPostItemZeroPrice() {
        boolean result = service.postItem("Artikal", 0.0f, 20);
        assertEquals(true, result);
        
        result = false;
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == testNumber+1 && x.getPrice() == 0.0f){
                result = true;
            }
        }
        if(result == true){ testNumber ++; }
        assertEquals(true, result);
    }
    
    @Test
    public void testPostItemNegativeAmount() {
        boolean result = service.postItem("Artikal", 50.0f, -1);
        assertEquals(false, result);
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == testNumber+1 && x.getAmount() == 0){
                result = true;
            }
        }
        if(result == true){ testNumber ++; }
        assertEquals(false, result);
    }
    
    @Test
    public void testPostItemZeroAmount() {
        boolean result = service.postItem("Artikal", 50.0f, 0);
        assertEquals(true, result);
        
        result = false;
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == testNumber+1 && x.getAmount() == 0){
                result = true;
            }
        }
        if(result == true){ testNumber ++; }
        assertEquals(true, result);
    }
    
    @Test
    public void testRemoveItem() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 20);
        sdao.insertOne(newItem);
        
        boolean result = service.removeItem(newItem);
        assertEquals(true, result);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(null, gottenItem);
    }

    @Test
    public void testRemoveNonExistingItem() {
        ShopItem newItem = new ShopItem(44, "Artikal", 20.0f, 20);
        boolean result = service.removeItem(newItem);
        assertEquals(false, result);
    }
    
    @Test
    public void testRemoveNullItem() {
        ShopItem newItem = null;
        boolean result = service.removeItem(newItem);
        assertEquals(false, result);
    }
    
    @Test
    public void testBuyEqualAmount() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, 50);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(0, gottenItem.getAmount());
    }
    
    @Test
    public void testBuyOneMoreThanAllowed() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, 51);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(50, gottenItem.getAmount());
    }
    
    @Test
    public void testBuyNegativeNumber() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, -1);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(50, gottenItem.getAmount());
    }
    
    @Test
    public void testBuyZero() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, 0);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(50, gottenItem.getAmount());
    }
    
    @Test
    public void testStockUpNegative() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 0);
        sdao.insertOne(newItem);
        service.stockUp(newItem, -1);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(0, gottenItem.getAmount());
    }
    
    @Test
    public void testStockUpZero() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 0);
        sdao.insertOne(newItem);
        service.stockUp(newItem, 0);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(0, gottenItem.getAmount());
    }
    
    @Test
    public void testStockUpOne() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "Artikal", 20.0f, 0);
        sdao.insertOne(newItem);
        service.stockUp(newItem, 1);
        ShopItem gottenItem = sdao.getOne(testNumber);
        assertEquals(1, gottenItem.getAmount());
    }
    
    @Test
    public void testCheckIfPopularHighPrice() {
        testNumber++;
        System.out.println(testNumber + " test number");
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal", 301.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 6100.0f, 61, new Date(), 1, testNumber, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckIfPopularHighPriceUnder() {
        testNumber++;
        System.out.println(testNumber + " test number");
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal", 301.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 6000.0f, 60, new Date(), 1, testNumber, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckIfPopularLowPrice() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal2", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 8100.0f, 81, new Date(), 1, testNumber, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckIfPopularLowPriceUnder() {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal2", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 8000.0f, 80, new Date(), 1, testNumber, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckIfPopularHighPriceDate() throws ParseException {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal", 301.0f, 100);
        sdao.insertOne(newItem);
        Date date = sdf.parse("2017-1-1");
        Transaction t = new Transaction(1, 6100.0f, 61, date, 1, testNumber, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckIfPopularLowPriceUnderDate() throws ParseException {
        testNumber++;
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal", 299.0f, 100);
        sdao.insertOne(newItem);
        Date date = sdf.parse("2017-1-1");
        Transaction t = new Transaction(1, 8000.0f, 81, date , 1, testNumber, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTrendingNullID()  {
        ShopItem newItem = new ShopItem( null , "PopularniArtikal", 299.0f, 100);
        float result = service.getTrendingIndex(newItem);
    }
    
    @Test
    public void testTrending()  {
        testNumber++;
        
        LocalDate localDate = LocalDate.now().minusDays(1);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 1000.0f, 10, date , 1, testNumber, 1, 0.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        float result = service.getTrendingIndex(newItem);
        assertEquals(result, 1000.0f,0.2);
    }
    
        @Test
    public void testTrending0days()  {
        testNumber++;
        
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(testNumber, "PopularniArtikal", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 1000.0f, 10, date , 1, testNumber, 1, 0.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        float result = service.getTrendingIndex(newItem);
        assertEquals(result, 1000.0f,0.2);
    }
    
}
