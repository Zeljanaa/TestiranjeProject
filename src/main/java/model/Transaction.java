/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Mladen
 */
public class Transaction extends Entity implements Comparable<Transaction>{
    

    private float totalPrice;
    private int amount;
    private Date date;
    private int clientId;
    private int shopItemId;
    private int deliveryServiceId;
    private float distance;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(int shopItemId) {
        this.shopItemId = shopItemId;
    }

    public int getDeliveryServiceId() {
        return deliveryServiceId;
    }

    public void setDeliveryServiceId(int deliveryServiceId) {
        this.deliveryServiceId = deliveryServiceId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
    
    public Transaction(){
        
    }
    
    public Transaction(Integer id, float totalPrice, int amount, Date date, int clientId,
            int shopItemId, int deliveryServiceId, float distance){
        this.id = id;
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.date = date;
        this.clientId = clientId;
        this.shopItemId = shopItemId;
        this.deliveryServiceId = deliveryServiceId;
        this.distance = distance;
    
    }

    @Override
    public int compareTo(Transaction o) {
        return getDate().compareTo(o.getDate());
    }
    
}
