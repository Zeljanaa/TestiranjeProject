/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.DeliveryServiceDAO;
import model.DeliveryService;
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
public class DeliveryServiceServiceTest {
    private static DeliveryServiceService dss;
    private static DeliveryServiceDAO ddao;
    private static int testNumber;
    public DeliveryServiceServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ddao = new DeliveryServiceDAO();
        testNumber = 0;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dss = new DeliveryServiceService();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class DeliveryServiceService.
     */
    @Test
    public void testRegister() {
        testNumber++;
        boolean result = dss.register("DeliveryService503",300.0f,70.0f);
        assertEquals(true, result);
        assertEquals(testNumber,(int)ddao.getOne(testNumber).getId());
        assertEquals(300.0f,(float)ddao.getOne(testNumber).getStartingPrice(),0.2);
        assertEquals(70.0f,(float)ddao.getOne(testNumber).getPricePerKilometer(),0.2);
        assertEquals("DeliveryService503", ddao.getOne(testNumber).getName());
    }
    
    @Test
    public void testRegisterNegativePricePerKm() {
        
        boolean result = dss.register("DeliveryService",-1.0f,300.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getPricePerKilometer() == -1.0f){
                result = false;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testRegisterZeroPricePerKm() {
        
        boolean result = dss.register("DeliveryService",0.0f,300.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getPricePerKilometer() == 0.0f){
                result = false;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testRegisterNegativeStartingPrice() {
        
        boolean result = dss.register("DeliveryService",300.0f,-1.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getStartingPrice() == -1.0f){
                result = false;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(true, result);
    }

    @Test
    public void testRegisterZeroStartingPrice() {
        
        boolean result = dss.register("DeliveryService",300.0f,0.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getStartingPrice() == 0.0f){
                result = false;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testRegisterWithoutName() {
        
        boolean result = dss.register("",300.0f,0.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getName().equals("")){
                result = false;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteDeliveryService() {
        testNumber++;
        DeliveryService newDS =  new DeliveryService(testNumber, "Servis501", 50.0f, 55.0f);
        ddao.insertOne(newDS);
        boolean result = dss.deleteDeliveryService(newDS);
        assertEquals(true, result);
        
        for(DeliveryService x: ddao.getAll()){
            if(x.getId() == testNumber){
                result = false;
            }
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteNonExistingDeliveryService() {
        boolean result = dss.deleteDeliveryService(new DeliveryService(999, "JedinstvenoIme501", 1.0f,1.0f));
        assertEquals(false, result);
        
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if(x.getName().equals("JedinstvenoIme501")){
                result = false;
            }
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testUpdateInfoWithNullID() {
        testNumber++;
        DeliveryService newDS = new DeliveryService(null, "NoviServis", 40.0f, 41.0f);
        ddao.insertOne(newDS);
        
        boolean result = dss.updateInfo(newDS, "NoviServis", 35.0f, 40.0f);
        assertEquals(false, result);
    }
    
    @Test
    public void testUpdateNumbers() {
        testNumber++;
        DeliveryService newDS = new DeliveryService(testNumber, "NoviServis501", 40.0f, 41.0f);
        ddao.insertOne(newDS);
        
        boolean result = dss.updateInfo(newDS, "NoviServis501", 35.0f, 40.0f);
        assertEquals(true, result);
        
        DeliveryService dbDS = ddao.getOne(testNumber);
        assertEquals(35.0f, dbDS.getPricePerKilometer(), 0.2);
        assertEquals(40.0f, dbDS.getStartingPrice(), 0.2);
    }
    
        public void testUpdateName() {
        testNumber++;
        DeliveryService newDS = new DeliveryService(testNumber, "NoviServis503", 40.0f, 41.0f);
        ddao.insertOne(newDS);
        
        boolean result = dss.updateInfo(newDS, "NoviServis502", 35.0f, 40.0f);
        assertEquals(true, result);
        
        DeliveryService dbDS = ddao.getOne(testNumber);
        assertEquals("NoviServis552", dbDS.getName());
    }
    
}


