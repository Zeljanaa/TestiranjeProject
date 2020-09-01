/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ShopItemDAO;
import db.TransactionDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import model.ShopItem;
import model.Transaction;

/**
 *
 * @author Mladen
 */
public class ShopItemService {
    
    private final ShopItemDAO sidao = new ShopItemDAO();
    private final TransactionDAO tdao = new TransactionDAO();
    private final TransactionService ts = new TransactionService(this);
    
    public boolean postItem(String name, float price, int amount){
        if(name.equals("") || price < 0 || amount < 0){
            return false;
        }
        
        ShopItem si = new ShopItem(null, name, price, amount);
        sidao.insertOne(si);
        
        return true;
    }
    
    public boolean removeItem(ShopItem s){
        
        if(s.getId() == null){
            return false;
        }
        
        sidao.deleteOne(s.getId());
        
        return true;
    }
    
    public void buy(ShopItem s, int amount){
        
        s.setAmount(s.getAmount() - amount);
        if(amount > s.getAmount()){
            System.out.println("Sprecena kupovina, neispravna kolicina");
            return;
        }
        if(amount < 0){
            System.out.println("Sprecena kupovina, negativan broj");

        sidao.updateOne(s);
    }    
    } 
    
    public void stockUp(ShopItem s, int amount){
        s.setAmount(s.getAmount() + amount);
        if(amount < 0){
            System.out.println("Spreceno dodavanje, negativan broj");
            return;
        }
        sidao.updateOne(s);
    }
    
    public boolean checkIfPopular(ShopItem s){
        int amountBought = 0;
        for(Transaction t : ts.getRecentTransactions()){
            if(t.getShopItemId() == s.getId()){
                amountBought += t.getAmount();
            }
        }
        
        if((amountBought > (s.getAmount()* 0.6) && (s.getPrice() > 300f)) || (amountBought > ((s.getAmount() * 0.8)) && s.getPrice() < 300f)){
            return true;
        }
        
        return false;
    }
    
    public float getTrendingIndex(ShopItem s){
        float index = 0;
        
        if(s.getId() == null){
            throw new IllegalArgumentException();
        }
        
        ArrayList<Transaction> allTransactions = tdao.getAll();
        ArrayList<Transaction> relevant = new ArrayList<>();
        
        for(Transaction t : allTransactions){
            if(t.getShopItemId() == s.getId()){
                index += t.getTotalPrice();
                relevant.add(t);
            }
        }
        
        if(index == 0){
            return 0;
        }
        
        Collections.sort(relevant);
        
        Transaction last = relevant.get(0);
        
        Date now = new Date();
        Date latest = last.getDate();
        
        int diffInDays = (int)(now.getTime() - latest.getTime())/(1000*60*60*24);
        index = (int)index/diffInDays;
        
        return index;
    }
    
    
    
}
