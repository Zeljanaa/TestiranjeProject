/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.TransactionDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import model.Client;
import model.DeliveryService;
import model.ShopItem;
import model.Transaction;

/**
 *
 * @author Mladen
 */
public class TransactionService {
    
    private final TransactionDAO tdao = new TransactionDAO();
    private final PromotionService ps = new PromotionService();
    private  ShopItemService si = null;
    public TransactionService(ShopItemService si){
        this.si = si;
        
    }
    
    public void completeTransaction(Client c, DeliveryService d, ShopItem s, int amount, float distance){
        Transaction  t = new Transaction();
        t.setAmount(amount);
        t.setClientId(c.getId());
        t.setShopItemId(s.getId());
        t.setDeliveryServiceId(d.getId());
        t.setDate(new Date());
        t.setDistance(distance);
        
        if(c.getId() == null || d.getId() == null || s.getId() == null || distance <= 0 || amount <= 0){
            throw new IllegalArgumentException();
        }
        
        
        si.buy(s, amount);
        t.setTotalPrice(calculatePrice(s, distance, amount, d));
        
        tdao.insertOne(t);
        
    }
    
    private float calculatePrice(ShopItem s, float distance, int amount, DeliveryService d){
        float price = 0;
        
        price += d.getStartingPrice();
        if(amount > 20 || amount < 50){
            price+= s.getPrice()*0.9;
        }
        else if(amount > 50){
            price += s.getPrice()*0.8;
        }
        else{
            price += s.getPrice();
        }  
        //popust za bliske dostave
        price -= 200/((int) distance * 20);
        
        price += distance * 100 * d.getPricePerKilometer();
        price += s.getPrice();
        
        float promotion = ps.applyPromotions(price, new Date());
        if(promotion  < price/2){
            price -= promotion;
        }
        return price;
    }
    
    public ArrayList<Transaction> getRecentTransactions(){
        ArrayList<Transaction> t = new ArrayList<>();
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,-30);
        for(Transaction temp : tdao.getAll()){
            if(temp.getDate().after(c.getTime()) || temp.getDate().before(new Date())){
                t.add(temp);
            }
        }
        
        return t;
    }
    
}
