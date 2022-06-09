/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * 1 ERE PAGE QUI DEMANDE SI ON EST GUEST, MEMBER OU EMPLOYEE
 * 
 * 
 */

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    @FXML
    private Button employeeButton;

    @FXML
    private Button guestButton;

    @FXML
    private Button memberButton;
    
    
}
