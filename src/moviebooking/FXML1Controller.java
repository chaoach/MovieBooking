/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    void handleGuestAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML2Guest.fxml"));
        //FXMLDocumentController s2Controller = loader.getController();
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Second Window");
        stage.show();
        // ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    //
    @FXML
    private Button memberButton;

    
}
