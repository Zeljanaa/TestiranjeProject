/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;

/**
 *
 * @author Mladen
 */
public class PromotionService {
    
    private float discount;
    
    public void calculateSeasonalPromotions(Date d){
    }
    
    public boolean checkHolidays(Date d){
        return false;
    }
    
    public void calculateSpecialPromotions(){
    }
    
    public void calculateDiscount(){
        this.discount = 1000;
    }
    
    public float applyPromotions(float price, Date d){
        if(checkHolidays(d)){
            calculateSeasonalPromotions(d);
            
        }
        calculateSeasonalPromotions(d);
        calculateSpecialPromotions();
        
        calculateDiscount();
        
        return this.discount;
    }
    
}
