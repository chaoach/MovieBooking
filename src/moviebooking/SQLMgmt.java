/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class SQLMgmt {
    
    private String url       = "jdbc:mysql://localhost:3306/site_cinema"; //url of DB
    private String user      = "root";
    private String password  = "";
    
    
    public boolean SQLLoginCheck(String username, String passwd){
        Connection conn = null;
        boolean login = false;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            ///////// code of the method
        
            Statement stmt=conn.createStatement(); 
            ResultSet rs=stmt.executeQuery("select * from login_passwd");   //SQL call for all items in login_passwd table
            
            
                while(rs.next())                                                // check all rows

                    if(username.equals(rs.getString(2)) && passwd.equals(rs.getString(3))){   // check if 2nd column is equal to username and if 3rd row is equal to passwd
                        login = true;
                        return login;                                       //return login, !! very important otherwise the while loop is continuing after a success an will make login to false again
                    } else { login = false; }
            
            
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
    }
    
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
    
}

