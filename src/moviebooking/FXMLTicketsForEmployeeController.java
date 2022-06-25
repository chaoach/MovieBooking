/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXMLTicketsForEmployeeController implements Initializable {

    ObservableList<Ticket> list_ticket = FXCollections.observableArrayList();
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setCellValueFactory(new PropertyValueFactory<Ticket, String>("date"));
        f_name.setCellValueFactory(new PropertyValueFactory<Ticket, String>("f_name"));
        mail.setCellValueFactory(new PropertyValueFactory<Ticket, String>("mail"));
        movie.setCellValueFactory(new PropertyValueFactory<Ticket, String>("movie"));
        screen.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("screen"));
        name.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
        tickets.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("tickets"));
        time.setCellValueFactory(new PropertyValueFactory<Ticket, String>("time"));
        
        SQLMgmt sql = new SQLMgmt();
        
        list_ticket = sql.SQLTableTicketEmployee();
        
        tableView.setItems(list_ticket);
    }    
    
    @FXML private TableColumn<Ticket, String> date;

    @FXML private TableColumn<Ticket, String> f_name;

    @FXML private TableColumn<Ticket, String> mail;

    @FXML private TableColumn<Ticket, String> movie;

    @FXML private TableColumn<Ticket, String> name;

    @FXML private TableColumn<Ticket, Integer> screen;

    @FXML private TableColumn<Ticket, Integer> tickets;

    @FXML private TableColumn<Ticket, String> time;

    @FXML private TableView<Ticket> tableView;
    
    @FXML
    void backButton(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML2Employee.fxml"));
        Parent root1 =(Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Employee Window");
        stage.show(); //shows the FXML page
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    
}
