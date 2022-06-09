package moviebooking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Guest extends Customer{
    
    private int discount = 0;
    
    public Guest(int id_user, String email){
        super(id_user, email);
    }
    
    
}
