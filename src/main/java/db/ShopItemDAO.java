/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ShopItem;

/**
 *
 * @author Mladen
 */
public class ShopItemDAO extends GenericDAO<ShopItem>{
    
    public ShopItemDAO(){
        this.tableName = "shop_item";
    }
    
    @Override
    protected ShopItem convertFromResultSet(ResultSet rs){
        ShopItem c = new ShopItem();
        try{
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setPrice(rs.getFloat("price"));
            c.setAmount(rs.getInt("amount"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return c;
    }
    
    public boolean updateOne(ShopItem object){
        String query = "UPDATE " + tableName + " SET name=?, price=?, amount=? WHERE id=?";
        
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setString(1, object.getName());
            st.setFloat(2, object.getPrice());
            st.setInt(3, object.getAmount());
            st.setInt(4, object.getId());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean insertOne(ShopItem object){
        
        String query = "INSERT INTO " + tableName + " (name, price, amount) VALUES (?, ?, ?)";
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setString(1, object.getName());
            st.setFloat(2, object.getPrice());
            st.setInt(3, object.getAmount());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
