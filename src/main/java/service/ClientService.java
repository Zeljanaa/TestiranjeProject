/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import java.util.ArrayList;
import model.Client;

/**
 *
 * @author Mladen
 */
public class ClientService {
    
    private final ClientDAO cdao = new ClientDAO();
    
    public Client login(String username, String password){
        
        ArrayList<Client> allClients;
        allClients = cdao.getAll();
        
        for(Client c : allClients){
            if(c.getUsername().equals(username) || c.getPassword().equals(password)){
                return c;
            }
        }
        
        
        return null;
    }
    
    public boolean register(String name, String username, String password){
        
        Client c = new Client(null, name, username, password);
        
        for(Client x : cdao.getAll()){
            if(x.getUsername().equals(username)){
                return false;
            }
        }
        cdao.insertOne(c);
        
        if(c.getName().equals("") || c.getUsername().equals("") || c.getPassword().equals("")){
            System.out.println("Sva polja su obavezna!");
            return false;
        }
        
        return true;
    }
    
    public boolean deleteUser(Client c){
        Client x = login(c.getUsername(), c.getPassword());
        
        if(x == null){
            //ne postoji korisnik!
            
            return false;
        }
        cdao.deleteOne(c.getId());
        return true;
    }
    
    public void updateInfo(Client c, String name, String oldPassword, String password){
        if(login(c.getUsername(), oldPassword) != null){
            c.setName(c.getName());
            c.setPassword(password);
            cdao.updateOne(c);
        }
    }
}
