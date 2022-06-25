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
public class Customer /*extends User*/{
    
    private String email,name,f_name,date,time,movie;
    private int screen, n_place;
    
    /*public Customer(int id_user, String email){
        //super(id_user);
        this.email = email;
    }*/

    public Customer(String email, String name, String f_name, String date, String time, String movie, int screen, int n_place) {
        this.email = email;
        this.name = name;
        this.f_name = f_name;
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.screen = screen;
        this.n_place = n_place;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getF_name() {
        return f_name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMovie() {
        return movie;
    }

    public int getScreen() {
        return screen;
    }

    public int getN_place() {
        return n_place;
    }
    
    
    
    public void bookMovie(int nOfTickets, String movieName, String time, double discount){
        //appeler la methode de la database qui crée une ligne "réservation" dans le tableau reservation 
    }
    
    public void getSQLMovies(){
        
    }
    
}
