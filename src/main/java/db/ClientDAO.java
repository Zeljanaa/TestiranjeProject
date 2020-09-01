/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Client;

/**
 *
 * @author Mladen
 */
public class ClientDAO extends GenericDAO<Client> {
    
    public ClientDAO(){
        this.tableName = "client";
    }
    
    @Override
    protected Client convertFromResultSet(ResultSet rs){
        Client c = new Client();
        try{
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setPassword(rs.getString("password"));
            c.setUsername(rs.getString("username"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return c;
    }
    
    public boolean updateOne(Client object){
        String query = "UPDATE " + tableName + " SET name=?, username=?, password=? WHERE id=?";
        
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setString(1, object.getName());
            st.setString(2, object.getUsername());
            st.setString(3, object.getPassword());
            st.setInt(4, object.getId());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean insertOne(Client object){
        
        String query = "INSERT INTO " + tableName + " (name, username, password) VALUES (?, ?, ?)";
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setString(1, object.getName());
            st.setString(2, object.getUsername());
            st.setString(3, object.getPassword());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
