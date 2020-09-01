/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.DeliveryService;

/**
 *
 * @author Mladen
 */
public class DeliveryServiceDAO extends GenericDAO<DeliveryService>{
    
    public DeliveryServiceDAO(){
        this.tableName = "delivery_service";
    }
    
    @Override
    protected DeliveryService convertFromResultSet(ResultSet rs){
        DeliveryService c = new DeliveryService();
        try{
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setStartingPrice(rs.getFloat("starting_price"));
            c.setPricePerKilometer(rs.getFloat("price_per_kilometer"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return c;
    }
    
    public boolean updateOne(DeliveryService object){
        String query = "UPDATE " + tableName + " SET name=?, starting_price=?, price_per_kilometer=? WHERE id=?";
        
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setString(1, object.getName());
            st.setFloat(2, object.getStartingPrice());
            st.setFloat(3, object.getPricePerKilometer());
            st.setInt(4, object.getId());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean insertOne(DeliveryService object){
        
        String query = "INSERT INTO " + tableName + " (name, starting_price, price_per_kilometer) VALUES (?, ?, ?)";
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setString(1, object.getName());
            st.setFloat(2, object.getStartingPrice());
            st.setFloat(3, object.getPricePerKilometer());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
