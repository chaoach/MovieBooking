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
    
}
