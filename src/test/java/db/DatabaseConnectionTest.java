/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
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
public class DatabaseConnectionTest {
    private static DatabaseConnection conn;
    public DatabaseConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        conn = new DatabaseConnection();
    }

    /**
     * Test of getConnection method, of class DatabaseConnection.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        Connection expResult = null;
        Connection result = conn.getConnection();
        assertNotEquals(expResult, result); 
    }
    
}
