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
     public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        try {
                // db parameters - ptest is the name of the database
                String url       = "jdbc:mysql://localhost:3306/site_cinema";
                String user      = "root";
                String password  = "";

                // create a connection to the database
                conn = DriverManager.getConnection(url, user, password);
                // more processing here
                // ...   
                Statement stmt=conn.createStatement(); 
                ResultSet rs=stmt.executeQuery("select * from movies"); 
                while(rs.next()) 
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)); 

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
    
}
