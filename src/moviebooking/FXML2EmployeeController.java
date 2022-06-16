/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML2EmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private TextField movieName;

    @FXML
    private TextField price;

    @FXML
    void addMovieButton(ActionEvent event) {
        double priceDouble = Double.parseDouble(price.getText());
        addMovie(movieName.getText(), priceDouble);
    }
    
    public void addMovie(String name, double price){
        SQLMgmt sql = new SQLMgmt();
        
        sql.SQLAddMovie(name,price);
    }
    
}
