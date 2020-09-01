/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mladen
 */
public class ShopItem extends Entity{

    private String name;
    private float price;
    private int amount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public ShopItem(){
        
    }
    
    public ShopItem(Integer id, String name, float price, int amount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
