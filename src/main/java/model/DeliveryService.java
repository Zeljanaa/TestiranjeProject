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
public class DeliveryService extends Entity{

    private String name;
    private float startingPrice;
    private float pricePerKilometer;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public float getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(float pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }
    
    public DeliveryService(){
        
    }
    
    public DeliveryService(Integer id, String name, float startingPrice, float pricePerKilometer){
        this.id = id;
        this.name = name;
        this.startingPrice = startingPrice;
        this.pricePerKilometer = pricePerKilometer;
    }
}
