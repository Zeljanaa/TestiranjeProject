/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Entity;

/**
 *
 * @author Mladen
 * @param <T>
 */
public abstract class GenericDAO<T extends Entity> {
    
    protected String tableName;
    
    protected T convertFromResultSet(ResultSet rs){
        return null;
    }
    
    
    public ArrayList<T> getAll(){
        ArrayList<T> res = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        try{
            Statement st = DatabaseConnection.getConnection().createStatement(); 
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                res.add(convertFromResultSet(rs));
            }
        }
        catch(Exception e){
           e.printStackTrace();
        }
        
        return res;
    }
    
    public T getOne(int id){
        T res = null;
        String query = "SELECT * FROM " + tableName + " WHERE id=?";
        try{
            
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                res = convertFromResultSet(rs);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    
    public boolean deleteOne(int id){
        String query = "DELETE FROM " + tableName + " WHERE id = ?";
        try{
            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    
}
