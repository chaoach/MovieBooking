/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *ALEX
 */
package moviebooking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class SQLMgmt {

    private String url = "jdbc:mysql://localhost:3306/site_cinema"; //url of DB
    private String user = "root";
    private String password = "";

    public boolean SQLLoginCheck(String username, String passwd) {
        Connection conn = null;
        boolean login = false;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            ///////// code of the method
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from login_passwd");   //SQL call for all items in login_passwd table

            while (rs.next()) // check all rows
            {
                if (username.equals(rs.getString(2)) && passwd.equals(rs.getString(3))) {   // check if 2nd column is equal to username and if 3rd row is equal to passwd
                    login = true;
                    return login;                                       //return login, !! very important otherwise the while loop is continuing after a success an will make login to false again
                } else {
                    login = false;
                }
            }

            ///////// end of code of the method
        } catch (SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return false;
    }
    
    public boolean SQLLoginEmployeeCheck(String username, String passwd) {
        Connection conn = null;
        boolean login = false;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            ///////// code of the method
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from login_emp");   //SQL call for all items in login_passwd table

            while (rs.next()) // check all rows
            {
                if (username.equals(rs.getString(2)) && passwd.equals(rs.getString(3))) {   // check if 2nd column is equal to username and if 3rd row is equal to passwd
                    login = true;
                    return login;                                       //return login, !! very important otherwise the while loop is continuing after a success an will make login to false again
                } else {
                    login = false;
                }
            }

            ///////// end of code of the method
        } catch (SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return false;
    }
    
    public void SQLAddMovie(String name, double price){
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
            
            Statement stmt=conn.createStatement(); 
            
            stmt.executeUpdate("INSERT INTO `movie`(`id_movie`, `name`, `price`) VALUES (NULL,'" + name + "'," + price + ")");   //SQL command to add movie and price to table movie       
            
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    /*
    public void SQLAddSession(String name, double price){
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            stmt.executeUpdate("INSERT INTO `movie`(`id_movie`, `name`, `price`) VALUES (NULL,'" + name + "'," + price + ")");   //SQL command to add movie and price to table movie       
            
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }*/
    
    public ArrayList<String> SQLListMoviesForEmployee(){
        ArrayList<String> list_movie = new ArrayList<String>();
        
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select name from movie");
            while (rs.next()) {
                list_movie.add(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list_movie;
    }
    
    public ArrayList<String> SQLListScreensForEmployee(){
        ArrayList<String> list_screen = new ArrayList<String>();
        
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select screen_n from screen");
            while (rs.next()) {
                list_screen.add(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list_screen;
    }
    
    public void SQLAddSession(String name, int screen, LocalDate date, String time){
        //System.out.println(name + screen + date + time);
        //pull from screen le n de ticket et set dans la ligne remaining ticket de session
        int tickets= 0;
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            
            ResultSet rs = stmt.executeQuery("select tickets_n from screen where screen_n =" + screen+";");
            while (rs.next()) {
                tickets = rs.getInt(1);
            }
            
            stmt.executeUpdate("INSERT INTO `session`(`id_session`, `movie_name`, `screen_n`, `date`, `time`, `remain_ticket`) VALUES (NULL,'" + name + "','" + screen +" ','" + date + "','" + time + "','"+tickets+"')");
            
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    //public boolean SQLCheckIfSessionExists(int screen, LocalDate) check if screen is taken
    public void SQLRemoveSession(String name, int screen, LocalDate date, String time){
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            stmt.executeUpdate("DELETE FROM `session` WHERE movie_name = '"+name+"' AND screen_n = '"+screen+"' AND date = '"+date+"' AND time = '"+time+"';");
            //stmt.executeUpdate("INSERT INTO `session`(`id_session`, `movie_name`, `screen_n`, `date`, `time`, `remain_ticket`) VALUES (NULL,'" + name + "','" + screen +" ','" + date + "','" + time + "','"+tickets+"')");
            
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public ObservableList<Session> SQLTableSessionEmployee(){
        ObservableList<Session> list = FXCollections.observableArrayList();
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from session");
                    
            while (rs.next()){
                list.add(new Session(rs.getString(2),Integer.valueOf(rs.getString(3)),rs.getString(4),rs.getString(5),Integer.valueOf(rs.getString(6))));
            }
                
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        return list;
    }
    
    public ObservableList<Session> SQLTableSessionEmployeeFiltered(String movie){
        ObservableList<Session> list = FXCollections.observableArrayList();
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from session where movie_name = '"+movie + "'");
                    
            while (rs.next()){
                list.add(new Session(rs.getString(2),Integer.valueOf(rs.getString(3)),rs.getString(4),rs.getString(5),Integer.valueOf(rs.getString(6))));
            }
                
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        return list;
    }
    
    
    public ObservableList<Ticket> SQLTableTicketEmployee(){
        ObservableList<Ticket> list_t;
        list_t = FXCollections.observableArrayList();
        Connection conn = null;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from site_reservation");
                    
            while (rs.next()){
                list_t.add(new Ticket(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),Integer.valueOf(rs.getString(8)),Integer.valueOf(rs.getString(9))/*,Double.valueOf(rs.getString(6)*/));
                System.out.println(rs.getString(2));
            }
                
            ///////// end of code of the method
        
        } catch(SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        return list_t;
    }
    
	public ArrayList<String> SQLmovie(String date_choisi) {

        ArrayList<String> list_movie = new ArrayList<String>();

        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url = "jdbc:mysql://localhost:3306/site_cinema";
            String user = "root";
            String password = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select movie_name from session where date = '" + date_choisi + "'");
            while (rs.next()) {
                list_movie.add(rs.getString(1));//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list_movie;
    }

    public ArrayList<String> SQLtime(String date_choisi, String movie_choisi) {

        ArrayList<String> list_time = new ArrayList<String>();

        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url = "jdbc:mysql://localhost:3306/site_cinema";
            String user = "root";
            String password = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select time from session where date = '" + date_choisi + "' and movie_name = '" + movie_choisi + "' ");
            while (rs.next()) {
                list_time.add(rs.getString(1));//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list_time;
    }

    public ArrayList<String> SQLscreen(String date_choisi, String movie_choisi, String time_choisi) {

        ArrayList<String> list_time = new ArrayList<String>();

        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url = "jdbc:mysql://localhost:3306/site_cinema";
            String user = "root";
            String password = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select screen_n from session where date = '" + date_choisi + "' and movie_name = '" + movie_choisi + "' and time = '" + time_choisi + "' ");
            while (rs.next()) {
                list_time.add(rs.getString(1));//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list_time;
    }

	public void SQLAddreservation(String date_choisi, String movie_choisi, String time_choisi, String screen, String nom, String prenom, String mail, int nb_place) {
        Connection conn = null;

        System.out.println(date_choisi);
        System.out.println(movie_choisi);
        System.out.println(time_choisi);
        System.out.println(screen);
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            ///////// code of the method
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO site_reservation (name_movie, date_movie,time_movie,screen,nom,prenom,mail,nb_ticket) VALUES ('" + movie_choisi + "','" + date_choisi + "','" + time_choisi + "','" + screen + "','" + nom + "','" + prenom + "','" + mail + "','" + nb_place + "')");   //SQL command to add movie and price to table movie  
            System.out.println("movie add");

            /* Fonction de pemetre d'envelever des places */
            sql_ticketvendu(date_choisi, movie_choisi, time_choisi, screen, nb_place);

            ///////// end of code of the method
        } catch (SQLException e) { //// catch error connection
            System.out.println(e.getMessage());
        } finally { //// try catch closing connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public int sql_remainticket(String date_choisi, String movie_choisi, String time_choisi, String screen) {

        int val = 0;
        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url = "jdbc:mysql://localhost:3306/site_cinema";
            String user = "root";
            String password = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select remain_ticket from session where date = '" + date_choisi + "' and movie_name = '" + movie_choisi + "' and time = '" + time_choisi + "' ");
            while (rs.next()) {
                val = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return val;
    }

    public void sql_ticketvendu(String date_choisi, String movie_choisi, String time_choisi, String screen, int nb_place) {

        int val = 0;
        String id_movie = "2";
        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url = "jdbc:mysql://localhost:3306/site_cinema";
            String user = "root";
            String password = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select remain_ticket from session where date = '" + date_choisi + "' and movie_name = '" + movie_choisi + "' and time = '" + time_choisi + "' ");
            while (rs.next()) {
                val = rs.getInt(1);
            }
            ResultSet ds = stmt.executeQuery("select id_session from session where date = '" + date_choisi + "' and movie_name = '" + movie_choisi + "' and time = '" + time_choisi + "' ");
            while (ds.next()) {
                id_movie = ds.getString(1);
            }

            val = val - nb_place;
            stmt.executeUpdate("UPDATE site_cinema.session SET remain_ticket =" + val + " WHERE session.id_session = " + id_movie);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

	public String SQLListhistory(String login) {
        String list_history = "name_movie\t\tdate_movie\t\ttime_movie\t\tnom\t\tprenom\t\tmail\t\tscreen\t\tnb_ticket \n";;

        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM `site_reservation` WHERE mail = '" + login + "' ");
            while (res.next()) {
                //Récupérer par nom de colonne
                String name_movie = res.getString("name_movie");
                String date_movie = res.getString("date_movie");
                String time_movie = res.getString("time_movie");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                String mail = res.getString("mail");
                String screen = res.getString("screen");
                String nb_ticket = res.getString("nb_ticket");

                //Afficher les valeurs
                System.out.print(name_movie);
                System.out.print("   " + date_movie);
                System.out.print(" " + time_movie);
                System.out.print(" " + nom);
                System.out.print(" " + prenom);
                System.out.print(" " + mail);
                System.out.print(" " + screen);
                System.out.println(" " + nb_ticket);

                list_history += name_movie + "\t\t" + date_movie + "\t\t" + time_movie + "\t\t" + nom + "\t\t" + prenom + "\t\t" + mail + "\t\t" + screen + "\t\t" + nb_ticket + "\n";
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list_history;
    }
    
    public double SQLGetPrice(String movie){
        double price=0;
        
        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url = "jdbc:mysql://localhost:3306/site_cinema";
            String user = "root";
            String password = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select price from movie where name = '" + movie + "'");// and movie_name = '" + movie_choisi + "' and time = '" + time_choisi + "' ");
            while (rs.next()) {
                //System.out.println(rs.getString(1));
                price = Double.valueOf(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        return price;
    }

}

