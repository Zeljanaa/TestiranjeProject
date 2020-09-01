/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import model.DeliveryService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 *
 * @author Zeljana
 */
public class DeliveryServiceDAOTest {
    private static DeliveryService deliveryService;
    private static DeliveryServiceDAO deliveryServiceDAO;
    private static int testNumber;
    private static DatabaseConnection conn;
    public DeliveryServiceDAOTest() {
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
        deliveryService = new DeliveryService(1,"NekiServis", 70.0f, 55.0f);
        deliveryServiceDAO = new DeliveryServiceDAO();
    }
    
    @After
    public void tearDown() {
    }

    private boolean checkDeliveryServiceFromDatabase(int id, String name, float startingPrice, float pricePerKm){
        PreparedStatement st = null;
        ResultSet rs;
        DeliveryService ds;
        String query = "SELECT * FROM delivery_service where id="+id+" and name='"+name+"' and starting_price='"+startingPrice+"' and price_per_kilometer='"+pricePerKm+"'";
        try{
            
            st = conn.getConnection().prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                ds = deliveryServiceDAO.convertFromResultSet(rs);
                if(ds.getId()==id && ds.getName().equals(name) && ds.getStartingPrice() == startingPrice && ds.getPricePerKilometer() == pricePerKm ){
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
    
    private boolean checkDeliveryServiceFromDatabase(String name, float startingPrice, float pricePerKm){
        PreparedStatement st = null;
        ResultSet rs;
        DeliveryService ds;
        String query = "SELECT * FROM delivery_service where name='"+name+"' and starting_price='"+startingPrice+"' and price_per_kilometer='"+pricePerKm+"'";
        try{
            st = DatabaseConnection.getConnection().prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                ds = deliveryServiceDAO.convertFromResultSet(rs);
                if(ds.getName().equals(name) && ds.getStartingPrice() == startingPrice && ds.getPricePerKilometer() == pricePerKm){
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
    


    /**
     * Test of updateOne method, of class DeliveryServiceDAO.
     */
    @Test
    public void testUpdateName() {
        testNumber++;
        System.out.println("testUpdateName");
        boolean expResult = true;
        
        int id = 1;
        String name = "Servis";
        String newName = "NoviServis";
        float startingPrice = 55.0f;
        float pricePerKm = 70.0f;
        deliveryService = new DeliveryService(id, name, startingPrice, pricePerKm);
        deliveryServiceDAO.insertOne(deliveryService);
        deliveryService.setName(newName);
        boolean result = deliveryServiceDAO.updateOne(deliveryService);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(newName, startingPrice, pricePerKm);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @Test
    public void testUpdateStartingPrice() {
        testNumber++;
        System.out.println("testUpdateStartingPrice");
        boolean expResult = true;
        
        int id = 1;
        String name = "Servis";
        float startingPrice = 55.0f;
        float newStartingPrice = 30.0f;
        float pricePerKm = 70.0f;
        deliveryService = new DeliveryService(id, name, startingPrice, pricePerKm);
        deliveryServiceDAO.insertOne(deliveryService);
        deliveryService.setStartingPrice(newStartingPrice);
        boolean result = deliveryServiceDAO.updateOne(deliveryService);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(name, newStartingPrice, pricePerKm);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @Test
    public void testUpdatePricePerKm() {
        testNumber++;
        System.out.println("testUpdatePricePerKm");
        boolean expResult = true;
        
        int id = 1;
        String name = "Servis";
        float startingPrice = 55.0f;
        float pricePerKm = 70.0f;
        float newPricePerKm = 30.0f;
        deliveryService = new DeliveryService(id, name, startingPrice, pricePerKm);
        deliveryServiceDAO.insertOne(deliveryService);
        deliveryService.setPricePerKilometer(newPricePerKm);
        boolean result = deliveryServiceDAO.updateOne(deliveryService);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(name, startingPrice, newPricePerKm);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }

    /**
     * Test of insertOne method, of class DeliveryServiceDAO.
     */
    @Test
    public void testInsertOne() {
        testNumber++;
        System.out.println("testInsertOne");
        boolean expResult = true;
        
        int id = 1;
        String name = "Servis";
        float startingPrice = 55.0f;
        float pricePerKm = 70.0f;
        deliveryService = new DeliveryService(id, name, startingPrice, pricePerKm);
        boolean result = deliveryServiceDAO.insertOne(deliveryService);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(name, startingPrice, pricePerKm);
        
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }

    
    
    @Test
    public void testInsertWithoutName() {
        testNumber++;
        System.out.println("testInsertWithoutName");
        boolean expResult = true;
        
        int id = 1;
        String name = "";
        float startingPrice = 55.0f;
        float pricePerKm = 70.0f;
        deliveryService = new DeliveryService(id, name, startingPrice, pricePerKm);
        boolean result = deliveryServiceDAO.insertOne(deliveryService);
        boolean databaseResult = this.checkDeliveryServiceFromDatabase(name, startingPrice, pricePerKm);
        
        assertEquals(expResult, result);
        assertNotEquals(expResult, databaseResult);
    }
    
    @org.junit.Test
    public void getOneTest() {
        testNumber++;
        System.out.println("getOneTest");
        boolean expResult = true;
        int deliveryServiceId = testNumber;

        deliveryService = new DeliveryService(testNumber, "Servis", 30.0f, 30.0f);
        deliveryServiceDAO.insertOne(deliveryService);
        DeliveryService gottenDS = deliveryServiceDAO.getOne(testNumber);
        boolean result = Objects.equals(deliveryService.getId(), gottenDS.getId());
      
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void getAllTest() {
        testNumber++;
        System.out.println("getAllTest");
        boolean expResult = true;
        deliveryServiceDAO.insertOne(new DeliveryService(testNumber, "Servis", 30.0f, 30.0f));
        ArrayList<DeliveryService> gottenDSs = deliveryServiceDAO.getAll();
        boolean result = gottenDSs.size() > 0;
      
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void deleteOneTest() {
        testNumber++;
        System.out.println("deleteOneTest");
        boolean expResult = true;
        deliveryServiceDAO.insertOne(new DeliveryService(testNumber, "Servis", 30.0f, 30.0f));
        boolean result = deliveryServiceDAO.deleteOne(testNumber);
      
        assertEquals(expResult, result);
        
        boolean result2 = deliveryServiceDAO.getOne(testNumber) == null;
        assertEquals(expResult,result2);
    }

    
}
