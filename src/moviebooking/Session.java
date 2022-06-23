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
public class Session {
    
    String name;
    int screen;
    String date;
    String time;
    double tickets;

    public Session(String name, int screen, String date, String time/*, double tickets*/) {
        this.name = name;
        this.screen = screen;
        this.date = date;
        this.time = time;
        //this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public int getScreen() {
        return screen;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getRemain_ticket() {
        return tickets;
    }
    
    
    
}
