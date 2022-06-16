/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;
import java.sql.*;

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
    
}

/*
public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        try {
                // db parameters - ptest is the name of the database
                String url       = "jdbc:mysql://localhost:3306/site_cinema";
                String user      = "root";
                String password  = "";

<<<<<<< Updated upstream
                // create a connection to the database
                conn = DriverManager.getConnection(url, user, password);
                // more processing here
                // ...   
                Statement stmt=conn.createStatement(); 
                ResultSet rs=stmt.executeQuery("select * from movies"); 
                while(rs.next()) 
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)); 
=======
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

    public void SQLAddreservation(String date_choisi, String movie_choisi, String time_choisi, String screen, String nom, String prenom, String mail,String nb_place) {
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
            stmt.executeUpdate("INSERT INTO site_reservation (name_movie, date_movie,time_movie,screen,nom,prenom,mail) VALUES ('" + movie_choisi + "','" + date_choisi + "','" + time_choisi + "','" + screen + "','" + nom + "','" + prenom + "','" + mail + "')");   //SQL command to add movie and price to table movie  
            System.out.println("movie add");

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

    
}
>>>>>>> Stashed changes

            } catch(SQLException e) {
                 System.out.println(e.getMessage());
            } finally {
                 try{
                         if(conn != null)
                         conn.close();
                     }catch(SQLException ex){
                            System.out.println(ex.getMessage());
                    }
        }       
    } 
*/
