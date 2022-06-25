/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXMLMemberPaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    int tickets;
    double discount = 0.2;
    
    private Customer customer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
   
    
    public void transferObject(Customer customer){
        SQLMgmt sql = new SQLMgmt();
        
        nTickets.setText(String.valueOf(customer.getN_place()));
        dateTime.setText("At " + customer.getTime()+" the "+ customer.getDate());
        movieName.setText(String.valueOf(customer.getMovie()));
        priceBefore.setText(String.valueOf(sql.SQLGetPrice(customer.getMovie())*customer.getN_place()));
        totalPrice.setText(String.valueOf((sql.SQLGetPrice(customer.getMovie())*customer.getN_place())-(discount*(sql.SQLGetPrice(customer.getMovie())*customer.getN_place()))));
        nTickets.setText(String.valueOf(customer.getN_place()));
    }
    
    @FXML private Label dateTime;

    @FXML private Label movieName;

    @FXML private Label nTickets;
    
    @FXML private Label priceBefore;
    
    @FXML private Label totalPrice;
    
    @FXML private TextField cbNumber;

    @FXML private TextField date;

    @FXML private TextField name;

    @FXML private TextField secret;



    @FXML
    void payButtonAction(ActionEvent event) {

    }

    
    
    
}
