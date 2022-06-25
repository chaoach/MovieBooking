/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static String login;
    
    public FXMLLoginController(){
        
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Label label1;

    @FXML
    private TextField usrName; //text Field user name

    @FXML
    private PasswordField usrPwd; //password field password

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (chkLogin(usrName.getText(), usrPwd.getText()) == true) { //check the return of method checkLogin
            System.out.println("Login ok");

            login = usrName.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML2member.fxml"));
            //FXMLDocumentController s2Controller = loader.getController();
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("MEMBER Window");
            stage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        } else {
            System.out.println("Login not ok");
        }
    }

    private boolean chkLogin(String usr, String pwd) throws FileNotFoundException { //Method that checks the login/password, call the sql method

        boolean login = false;

        SQLMgmt sql = new SQLMgmt(); //use the SQLMgmgt Class

        if (sql.SQLLoginCheck(usr, pwd)) { //call for method that check if login passwd is in database
            login = true;
            return login;
        } else {
            login = false;
        }
        return login;
    }
}
