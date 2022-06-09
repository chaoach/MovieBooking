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
public class Customer extends User{
    
    private String email;
    
    public Customer(int id_user, String email){
        super(id_user);
        this.email = email;
    }
    
    public void bookMovie(int nOfTickets, String movieName, String time, double discount){
        //appeler la methode de la database qui crée une ligne "réservation" dans le tableau reservation 
    }
    
}
