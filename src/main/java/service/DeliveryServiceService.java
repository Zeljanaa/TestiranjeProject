/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.DeliveryServiceDAO;
import model.DeliveryService;

/**
 *
 * @author Mladen
 */
public class DeliveryServiceService {
    
    private final DeliveryServiceDAO ddao = new DeliveryServiceDAO();
    
    public boolean register(String name, float pricePerKilometer, float startingPrice){
        
        if(name == "" || startingPrice <= 0 || pricePerKilometer <= 0){
            return false;
        }
        
        DeliveryService d = new DeliveryService(null, name, pricePerKilometer, startingPrice);
        ddao.insertOne(d);
        
        return true;
    }
    
    public boolean deleteDeliveryService(DeliveryService d){
        
        ddao.deleteOne(d.getId());
        
        return true;
    }
    
    public boolean updateInfo(DeliveryService d,String name, float startingPrice, float pricePerKilometer){
        if(d.getId() == null){
            return false;
        }
        
        d.setName(name);
        d.setStartingPrice(startingPrice);
        d.setPricePerKilometer(pricePerKilometer);
        
        ddao.updateOne(d);
        
        return true;
    }
    
}
