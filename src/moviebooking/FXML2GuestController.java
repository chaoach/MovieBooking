/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FXML2GuestController implements Initializable {

    /**
     * Initializes the controller class. Class qui définit la page de booking
     * guest
     */
    private String date_choisi;
    private String movie_choisi;
    private String time_choisi;
    private String screen_choisi;
    private String nom;
    private String prenom;
    private String mail;
    private String nombre_place;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> movieChoiceBox;

    @FXML
    private ChoiceBox<String> screenChoiceBox;

    @FXML
    private ChoiceBox<String> timeChoiceBox;

    @FXML
    private TextField txt_mail;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;
    
    /*movie*/
    @FXML
    void f_date(ActionEvent event) {/* action qui permet de faire le munu deroulant */

        load_sql_movie();
    }

    void load_sql_movie() {

        LocalDate localDate = datePicker.getValue();
        date_choisi = localDate.toString();
        SQLMgmt sql_movie = new SQLMgmt();
        System.out.println(localDate + " la date choisie \n");

        ArrayList<String> list_movie = sql_movie.SQLmovie(date_choisi);

        movieChoiceBox.getItems().clear();

        for (String elem : list_movie) {
            movieChoiceBox.getItems().addAll(elem);
        }

    }

    /*time*/
    @FXML
    void f_movie(ActionEvent event) {/* action qui permet de faire le munu deroulant */

        load_sql_time();

        System.out.println(" BP movie ");

    }

    void load_sql_time() {

        SQLMgmt sql_time = new SQLMgmt();
        System.out.println(date_choisi + " la date choisie  et le film est ");

        movie_choisi = movieChoiceBox.getValue();

        ArrayList<String> list_time = sql_time.SQLtime(date_choisi, movie_choisi);

        timeChoiceBox.getItems().clear();

        for (String elem : list_time) {
            timeChoiceBox.getItems().addAll(elem);
        }

    }

    /*Screen*/
    @FXML
    void f_time(ActionEvent event) {
        /* action qui permet de faire le munu deroulant */

        load_sql_screen();
        

        System.out.println(" BP time ");

    }

    void load_sql_screen() {

        SQLMgmt sql_screen = new SQLMgmt();

        time_choisi = timeChoiceBox.getValue();

        ArrayList<String> list_screen = sql_screen.SQLscreen(date_choisi, movie_choisi, time_choisi);

        screenChoiceBox.getItems().clear();

        for (String elem : list_screen) {
            screenChoiceBox.getItems().addAll(elem);
        }

    }
    
    @FXML
    void f_screen(ActionEvent event) {
        /* action qui permet de faire le menu deroulant */

        screen_choisi = screenChoiceBox.getValue();
        

        System.out.println(" BP screen ");

    }
    @FXML
    void selectButtonAction(MouseEvent event) {
        
        SQLMgmt sql_reservation = new SQLMgmt();
        nom = txt_nom.getText();
        prenom = txt_prenom.getText();
        mail = txt_mail.getText();
        //sql_reservation.SQLAddreservation(date_choisi, movie_choisi, time_choisi, screen_choisi,nom,prenom,mail,nombre_place);

    }
}
