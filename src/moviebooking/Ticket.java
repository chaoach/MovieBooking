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
public class Ticket {
    
    String movie, date, time, name, f_name, mail;
    int screen, tickets;

    public Ticket(String movie, String date, String time, String name, String f_name, String mail, int screen, int tickets) {
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.name = name;
        this.f_name = f_name;
        this.mail = mail;
        this.screen = screen;
        this.tickets = tickets;
    }

    public String getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getF_name() {
        return f_name;
    }

    public String getMail() {
        return mail;
    }

    public int getScreen() {
        return screen;
    }

    public int getTickets() {
        return tickets;
    }


    
    
    
    
}
