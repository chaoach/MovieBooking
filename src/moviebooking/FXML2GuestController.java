/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML2GuestController implements Initializable {

    /**
     * Initializes the controller class.
     * Class qui définit la page de booking guest
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        movieChoiceBox.getItems().addAll("mickey","iron man");
    }   
    
    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> movieChoiceBox;

    @FXML
    private ChoiceBox<?> screenChoiceBox;

    @FXML
    private ChoiceBox<?> timeChoiceBox;

    @FXML
    void selectButtonAction(MouseEvent event) {

    }
    

/*
    void remplir_Jcomb() {
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
            ResultSet rs = stmt.executeQuery("select * from site_reservation where ID = 1");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
            }
            stmt.executeUpdate("INSERT INTO employe " + "VALUES (1001, 'alex', 'rakul', 'Springfield', 2001)");

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
    }*/
    
}
