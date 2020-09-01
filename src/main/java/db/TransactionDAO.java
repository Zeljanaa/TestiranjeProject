/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Transaction;

/**
 *
 * @author Mladen
 */
public class TransactionDAO extends GenericDAO<Transaction> {
    
    public TransactionDAO(){
        this.tableName = "transaction";
    }
    
    @Override
    protected Transaction convertFromResultSet(ResultSet rs){
        Transaction c = new Transaction();
        try{
            c.setId(rs.getInt("id"));
            c.setTotalPrice(rs.getFloat("total_price"));
            c.setAmount(rs.getInt("amount"));
            c.setDate(rs.getDate("date"));
            c.setClientId(rs.getInt("client_id"));
            c.setShopItemId(rs.getInt("shop_item_id"));
            c.setDeliveryServiceId(rs.getInt("delivery_service_id"));
            c.setDistance(rs.getFloat("distance"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return c;
    }
    
    public boolean insertOne(Transaction object){
        
        String query = "INSERT INTO " + tableName + " (total_price, amount, date, client_id, shop_item_id, delivery_service_id, distance)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setFloat(1, object.getTotalPrice());
            st.setInt(2, object.getAmount());
            st.setDate(3, new java.sql.Date(object.getDate().getTime()));
            st.setInt(4, object.getClientId());
            st.setInt(5, object.getShopItemId());
            st.setInt(6, object.getDeliveryServiceId());
            st.setFloat(7, object.getDistance());
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
