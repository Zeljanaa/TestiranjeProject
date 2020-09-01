/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mladen
 */
public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection(){
        
        try{
            if(connection == null){
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_shop?useSSL=false", "root", "root");
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return connection;
    }
    
    public static void close(){
        if(connection != null){
            try{
                connection.close();
                connection = null;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
}
