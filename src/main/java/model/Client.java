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
public class Client extends Entity{
    
    private String name;
    private String username;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Client(){
        
    }
    
    public Client(Integer id, String name, String username, String password){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    
    
}
