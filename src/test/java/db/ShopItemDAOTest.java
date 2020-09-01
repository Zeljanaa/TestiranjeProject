/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import model.ShopItem;
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
public class ShopItemDAOTest {
    private static ShopItem shopItem;
    private static ShopItemDAO shopItemDAO;
    private static int testNumber;
    private static DatabaseConnection conn;
    public ShopItemDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        conn = new DatabaseConnection();
        testNumber = 0;
    }
    
    @AfterClass
    public static void tearDownClass() {

    }
    
    @Before
    public void setUp() {
        shopItem = new ShopItem(1,"NekiArtikal", 70.0f, 5);
        shopItemDAO = new ShopItemDAO();
    }
    
    @After
    public void tearDown() {
    }
    
    private boolean checkDeliveryServiceFromDatabase(int id, String name, float price, int amount){
        PreparedStatement st = null;
        ResultSet rs;
        ShopItem si;
        String query = "SELECT * FROM shop_item where id="+id+" and name='"+name+"' and price='"+price+"' and amount='"+amount+"'";
        try{
            
            st = conn.getConnection().prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                si = shopItemDAO.convertFromResultSet(rs);
                if(si.getId()==id && si.getName().equals(name) && si.getPrice() == price && si.getAmount() == amount ){
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    private boolean checkDeliveryServiceFromDatabase(String name, float price, float amount){
        PreparedStatement st = null;
        ResultSet rs;
        ShopItem si;
        String query = "SELECT * FROM shop_item where name='"+name+"' and price='"+price+"' and amount='"+amount+"'";
        try{
            st = DatabaseConnection.getConnection().prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                si = shopItemDAO.convertFromResultSet(rs);
                if(si.getName().equals(name) && si.getPrice() == price && si.getAmount() == amount){
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    @Test
    public void testUpdateName() {
        testNumber++;
        System.out.println("testUpdateName");
        boolean expResult = true;
        String newName = "Artikal2";
        shopItem.setId(testNumber);
        shopItemDAO.insertOne(shopItem);
        shopItem.setName(newName);
        boolean result = shopItemDAO.updateOne(shopItem);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(newName, shopItem.getPrice(), shopItem.getAmount());
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @Test
    public void testUpdatePrice() {
        testNumber++;
        System.out.println("testUpdatePrice");
        boolean expResult = true;
        float newPrice = 55.0f;
        shopItem.setId(testNumber);
        shopItemDAO.insertOne(shopItem);
        shopItem.setPrice(newPrice);
        boolean result = shopItemDAO.updateOne(shopItem);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(shopItem.getName(), newPrice, shopItem.getAmount());
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @Test
    public void testUpdateAmount() {
        testNumber++;
        System.out.println("testUpdateAmount");
        boolean expResult = true;
        int newAmount = 18;
        shopItem.setId(testNumber);
        shopItemDAO.insertOne(shopItem);
        shopItem.setAmount(newAmount);
        boolean result = shopItemDAO.updateOne(shopItem);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(shopItem.getName(), shopItem.getPrice(), newAmount);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }

    @Test
    public void testInsertOne() {
        testNumber++;
        System.out.println("testInsertOne");
        boolean expResult = true;
        shopItem.setId(testNumber);
        boolean result =  shopItemDAO.insertOne(shopItem);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(shopItem.getName(), shopItem.getPrice(), shopItem.getAmount());
        
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @Test
    public void testInsertWithoutName() {
        testNumber++;
        System.out.println("testInsertWithoutName");
        boolean expResult = false;
        shopItem.setName("");
        boolean result = shopItemDAO.insertOne(shopItem);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(shopItem.getId(), shopItem.getName(), shopItem.getPrice(), shopItem.getAmount());
        
        assertEquals(expResult, result);
        assertNotEquals(expResult, databaseResult);
    }
  
    
    
    @org.junit.Test
    public void getOneTest() {
        testNumber++;
        System.out.println("getOneTest");
        boolean expResult = true;
        shopItem.setId(testNumber);
        shopItemDAO.insertOne(shopItem);
        ShopItem gottenSI = shopItemDAO.getOne(testNumber);
        boolean result = Objects.equals(shopItem.getId(), gottenSI.getId());
        System.out.println(shopItem.getId() + " " + gottenSI.getId());
      
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void getAllTest() {
        testNumber++;
        System.out.println("getAllTest");
        boolean expResult = true;
        shopItemDAO.insertOne(shopItem);
        ArrayList<ShopItem> gottenSIs = shopItemDAO.getAll();
        boolean result = gottenSIs.size() > 0;
      
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void deleteOneTest() {
        testNumber++;
        System.out.println("deleteOneTest");
        boolean expResult = true;
        shopItemDAO.insertOne(shopItem);
        boolean result = shopItemDAO.deleteOne(testNumber);
      
        assertEquals(expResult, result);
        
        boolean result2 = shopItemDAO.getOne(testNumber) == null;
        assertEquals(expResult,result2);
    }
    
}
