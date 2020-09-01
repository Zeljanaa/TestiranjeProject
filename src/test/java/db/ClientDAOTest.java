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
import model.Client;
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
public class ClientDAOTest {
    private static Client client;
    private static ClientDAO clientDAO;
    private Client dataBaseClient;
    private static int testNumber;
    private static DatabaseConnection conn;
    public ClientDAOTest() {
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
        client = new Client(1, "Zeljana", "Zeks555", "123");
        clientDAO = new ClientDAO();
        dataBaseClient = new Client();
        
    }
    
    @After
    public void tearDown() {
    }
    
    private boolean checkClientFromDatabase(int id, String name, String username, String password){
        PreparedStatement st = null;
        ResultSet rs;
        Client cl;
        String query = "SELECT * FROM web_shop.client where id="+id+" and name='"+name+"' and username='"+username+"' and password='"+password+"'";
        try{
            
            st = conn.getConnection().prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                cl = clientDAO.convertFromResultSet(rs);
                if(cl.getId()==id && cl.getName().equals(name) && cl.getUsername().equals(username) && cl.getPassword().equals(password) ){
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
    
        private boolean checkClientFromDatabase(String name, String username, String password){
        PreparedStatement st = null;
        ResultSet rs;
        Client cl;
        String query = "SELECT * FROM web_shop.client where name='"+name+"' and username='"+username+"' and password='"+password+"'";
        try{
            st = DatabaseConnection.getConnection().prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                cl = clientDAO.convertFromResultSet(rs);
                System.out.println(cl);
                if(cl.getName().equals(name) && cl.getUsername().equals(username) && cl.getPassword().equals(password) ){
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
     * Test of updateOne method, of class ClientDAO.
     */
    @org.junit.Test
    public void testUpdateName() {
        testNumber++;
        System.out.println("testUpdateName");
        boolean expResult = true;
        
        int clientId = testNumber;
        String clientName = "Zeljana";
        String clientUsername = "Zeks1";
        String clientPassword = "123";
        
        String clientAfterChangeName = "Zeks";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        clientDAO.insertOne(client);

        client.setName(clientAfterChangeName);
        boolean result = clientDAO.updateOne(client);
        assertEquals(expResult, result);
        
        boolean databaseResult = this.checkClientFromDatabase(clientId, clientAfterChangeName, clientUsername, clientPassword);
        assertEquals(expResult, databaseResult);
    }
    
    @org.junit.Test
    public void testUpdateUsername() {
        testNumber++;
        System.out.println("testUpdateUsername");
        boolean expResult = true;
        
        int clientId = testNumber;
        String clientName = "Zeljana";
        String clientUsername = "Zeks2";
        String clientPassword = "123";
        
        String clientAfterChangeUsername = "Zeks3";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        clientDAO.insertOne(client);

        client.setUsername(clientAfterChangeUsername);
        boolean result = clientDAO.updateOne(client);
        assertEquals(expResult, result);
        
        boolean databaseResult = this.checkClientFromDatabase(clientId, clientName, clientAfterChangeUsername, clientPassword);
        assertEquals(expResult, databaseResult);
    }
    
    @org.junit.Test
    public void testUpdatePassword() {
        testNumber++;
        System.out.println("testUpdatePassword");
        boolean expResult = true;
        
        int clientId = testNumber;
        String clientName = "Zeljana";
        String clientUsername = "Zeks4";
        String clientPassword = "123";
        
        String clientAfterChangePass = "456";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        clientDAO.insertOne(client);

        client.setPassword(clientAfterChangePass);
        boolean result = clientDAO.updateOne(client);
        assertEquals(expResult, result);
        
        boolean databaseResult = this.checkClientFromDatabase(clientId, clientName, clientUsername, clientAfterChangePass);
        assertEquals(expResult, databaseResult);
    }

    /**
     * Test of insertOne method, of class ClientDAO.
     */
    @org.junit.Test
    public void insertIDtest() {
        testNumber++;
        System.out.println("insertIDtest");
        boolean expResult = true;
        int clientId = testNumber;
        String clientName = "Zeljana";
        String clientUsername = "Zeks";
        String clientPassword = "123";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        boolean result = clientDAO.insertOne(client);
        
        boolean databaseResult = this.checkClientFromDatabase(clientId, clientName, clientUsername, clientPassword);
        System.out.println(databaseResult);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    
    @org.junit.Test
    public void insertDuplicateUsername() {
        testNumber++;
        testNumber++;
        System.out.println("insertDuplicateUsername");
        boolean expResult = true;
        int clientId = 5;
        String clientName = "Zeljana";
        String clientUsername = "Zeks555";
        String clientPassword = "123";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        boolean result = clientDAO.insertOne(client);
        boolean secondResult = clientDAO.insertOne(client);
        
        boolean databaseResult = this.checkClientFromDatabase(clientName, clientUsername, clientPassword);
        System.out.println(databaseResult);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
        assertEquals(!expResult, secondResult);
    }
    
    @org.junit.Test
    public void insertWithoutName() {
        testNumber++;
        System.out.println("insertWithoutName");
        boolean expResult = false;
        int clientId = 6;
        String clientName = "";
        String clientUsername = "Zeks400";
        String clientPassword = "123";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        boolean result = clientDAO.insertOne(client);
        
        boolean databaseResult = this.checkClientFromDatabase(clientName, clientUsername, clientPassword);
        System.out.println(databaseResult);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @org.junit.Test
    public void insertWithoutUsername() {
        testNumber++;
        System.out.println("insertWithoutUsername");
        boolean expResult = false;
        int clientId = 7;
        String clientName = "Zeljana";
        String clientUsername = "";
        String clientPassword = "123";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        boolean result = clientDAO.insertOne(client);
        
        boolean databaseResult = this.checkClientFromDatabase(clientName, clientUsername, clientPassword);
        System.out.println(databaseResult);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @org.junit.Test
    public void insertWithoutPass() {
        testNumber++;
        System.out.println("insertWithoutPass");
        boolean expResult = false;
        int clientId = 001;
        String clientName = "Zeljana";
        String clientUsername = "Zeks1";
        String clientPassword = "";
        client = new Client(clientId, clientName, clientUsername, clientPassword);
        boolean result = clientDAO.insertOne(client);
        
        boolean databaseResult = this.checkClientFromDatabase(clientName, clientUsername, clientPassword);
        System.out.println(databaseResult);
        assertEquals(expResult, result);
        assertEquals(expResult, databaseResult);
    }
    
    @org.junit.Test
    public void getOneTest() {
        testNumber++;
        System.out.println("getOneTest");
        boolean expResult = true;
        int clientId = testNumber;
        String clientName = "Zeljana";
        String clientUsername = "Zeks0116";
        String clientPassword = "123";
        client = new Client(testNumber, clientName, clientUsername, clientPassword);
        clientDAO.insertOne(client);
        Client gottenClient = clientDAO.getOne(testNumber);
        System.out.println(testNumber + " TESTNMBR");
        boolean result = Objects.equals(client.getId(), gottenClient.getId());
      
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void getAllTest() {
        testNumber++;
        System.out.println("getAllTest");
        boolean expResult = true;
        clientDAO.insertOne(new Client(2,"Client","Client","Client"));
        ArrayList<Client> gottenClients = clientDAO.getAll();
        boolean result = gottenClients.size() > 0;
      
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void deleteOneTest() {
        testNumber++;
        System.out.println("deleteOneTest");
        boolean expResult = true;
        clientDAO.insertOne(new Client(testNumber,"Client2","Client2","Client2"));
        boolean result = clientDAO.deleteOne(testNumber);
      
        assertEquals(expResult, result);
        
        boolean result2 = clientDAO.getOne(testNumber) == null;
        assertEquals(expResult,result2);
        
        
    }
}
