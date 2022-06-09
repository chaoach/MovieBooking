/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

/**
 *
 * @author PC
 */
public class Employee extends User{
    
    public Employee(int id_user){
        super(id_user);
    }
    
    public void listAllMovie(){
        //SQL list movies
    }
    
    public void updateMovie(){
        //SQL change movie info
    }
    
    public void addMovie(String title, double duration, double ticketPrice, int screenN){
        //SQL method addMovie
    }
    
}
