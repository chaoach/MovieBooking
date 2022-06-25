/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rakul
 */
public class FXML2memberController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String date_choisi;
    private String movie_choisi;
    private String time_choisi;
    private String screen_choisi;
    private String nom;
    private String prenom;
    private String mail;
    private int nombre_place = 0;
    private int ticket_restant = 0;

    @FXML
    private DatePicker dateMenu;

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

    @FXML
    private ImageView image_view_guest;

    @FXML
    private Label lb_nombre_place;

    @FXML
    private Button bp_moins;

    @FXML
    private Button bp_plus;

    @FXML
    private TextArea txt_ticket;

    @FXML
    private Label lb_title;
    @FXML
    private Button bp_history;
    @FXML
    private Button bp_book;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        FXMLLoginController login_page = new FXMLLoginController(); // constructeur pour avoir acces au login
        nom = login_page.getLogin();

        lb_title.setText("Welcome " + nom + "!");
        txt_mail.setText(nom);
        dateMenu.setValue(LocalDate.now());
        setAvailableDates();
    }

    /*movie*/
    @FXML
    void f_date(ActionEvent event) {/* action qui permet de faire le munu deroulant */

        load_sql_movie();
    }

    void load_sql_movie() {

        LocalDate localDate = dateMenu.getValue();
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
        lb_nombre_place.setText("Nombre de places : ??? " );

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

        Image myImage = new Image(getClass().getResourceAsStream(movie_choisi + ".jpg"));
        image_view_guest.setImage(myImage);

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

        f_reaming_ticket();

    }

    void f_reaming_ticket() {
        int val = 0;
        SQLMgmt sql_remaining_ticket = new SQLMgmt();
        val = sql_remaining_ticket.sql_remainticket(date_choisi, movie_choisi, time_choisi, screen_choisi);
        ticket_restant = val;

        lb_nombre_place.setText("Nombre de places : " + val);
    }

    @FXML
    void f_plus_ticket(ActionEvent event) {
        nombre_place++;

        if (nombre_place < 0) {
            nombre_place = 0;

        }

        if (ticket_restant == 0 || ticket_restant >= nombre_place) {

            txt_ticket.setText("" + nombre_place);
            bp_book.setVisible(true);
        } else {
            bp_book.setVisible(false);
            txt_ticket.setText("no place or your booking too much");
        }

    }

    @FXML
    void f_moins_ticket(ActionEvent event) {
        nombre_place--;
        if (nombre_place < 0) {
            nombre_place = 0;
        }
        if (ticket_restant == 0 || ticket_restant >= nombre_place) {

            bp_book.setVisible(true);

            txt_ticket.setText("" + nombre_place);
        } else {
            bp_book.setVisible(false);
            txt_ticket.setText("no place or your booking too much");
        }

    }



    @FXML
    void show_history(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhistory_member.fxml")); //access the ressource of the page for the employee
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("History Member Window");
        stage.show(); //shows the FXML page

    }

    private void setAvailableDates() {
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(
                                dateMenu.getValue())//.plusDays(1))
                                ) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        dateMenu.setDayCellFactory(dayCellFactory);
    }
    

    
    
    @FXML
        void bookButton(ActionEvent event) throws IOException{
        nom = txt_nom.getText();
        prenom = txt_prenom.getText();
        mail = txt_mail.getText();
        Customer customer = new Customer(mail,nom,prenom,date_choisi,time_choisi,movie_choisi,Integer.valueOf(screen_choisi),nombre_place);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMemberPayment.fxml"));
        Parent root1 = (Parent) loader.load();    
        FXMLMemberPaymentController sController = loader.getController();
        sController.transferObject(customer);;
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Payment Window");
        stage.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

}
