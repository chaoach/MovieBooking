/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXMLPaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    int tickets;
    
    private Customer customer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
   
    
    public void transferObject(Customer customer){//int customer){
        SQLMgmt sql = new SQLMgmt();
        this.customer=customer;
        nTickets.setText(String.valueOf(customer.getN_place()));
        dateTime.setText("At " + customer.getTime()+" the "+ customer.getDate());
        movieName.setText(String.valueOf(customer.getMovie()));
        totalPrice.setText(String.valueOf(sql.SQLGetPrice(customer.getMovie())*customer.getN_place()));//customer.getN_place()));
        nTickets.setText(String.valueOf(customer.getN_place()));
    }
    
    @FXML private Label dateTime;

    @FXML private Label movieName;

    @FXML private Label nTickets;
    
    @FXML private Label totalPrice;
    
    @FXML private TextField cbNumber;

    @FXML private TextField date;

    @FXML private TextField name;

    @FXML private TextField secret;



    @FXML
    void payButtonAction(ActionEvent event) throws IOException{
        SQLMgmt sql = new SQLMgmt();
        //System.out.println(customer.getDate()+customer.getMovie()+customer.getTime()+String.valueOf(customer.getScreen())+customer.getName()+customer.getF_name()+customer.getEmail()+customer.getN_place());
        //SQLAddreservation(String date_choisi, String movie_choisi, String time_choisi, String screen, String nom, String prenom, String mail, int nb_place)
        sql.SQLAddreservation(customer.getDate(),customer.getMovie(),customer.getTime(),String.valueOf(customer.getScreen()),customer.getName(),customer.getF_name(),customer.getEmail(),customer.getN_place());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLThanks.fxml")); //access the ressource of the page for the employee
        Parent root1 =(Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        //stage.setTitle("Employee Window");
        stage.show(); //shows the FXML page
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    
    
    
}
