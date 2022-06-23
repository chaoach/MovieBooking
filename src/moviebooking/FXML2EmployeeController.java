/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;

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
        populateMovieSelect();
        LocalDate date;
        dateSession.setValue(LocalDate.now());
        setAvailableDates();
        populateScreenSelect();
        setSessionTable(); 
    }

    @FXML
    private TextField movieName;

    @FXML
    private TextField price;

    @FXML
    private Label firstLabel;
    
    @FXML
    private Label secondLabel;

    @FXML
    void addMovieButton(ActionEvent event) {
        double priceDouble = Double.parseDouble(price.getText());
        if(testMovieInDB()==true){
            firstLabel.setText("The movie is already in DB");
        } else {
            addMovie(movieName.getText(), priceDouble);
            firstLabel.setText("The movie as been imported to DB");
            populateMovieSelect();
        }
        
    }
    
    public boolean testMovieInDB(){
        SQLMgmt sql = new SQLMgmt();

        ArrayList<String> list_movie = sql.SQLListMoviesForEmployee();
        
        for (String elem : list_movie) {
            
            if(elem.equals(movieName.getText())){
                return true; //return true if list is in movie DB
            }
            
        }
        
        return false;
    }

    public void addMovie(String name, double price) {
        SQLMgmt sql = new SQLMgmt();
        sql.SQLAddMovie(name, price);
    }

    @FXML
    private Button addPictureButton;

    @FXML
    void addPicture(ActionEvent event) {
        FileChooser fc = new FileChooser();                                                         //create object to handle file browsing and selecting
        fc.getExtensionFilters().add(new ExtensionFilter("Images Files", "*.jpg"));                  //precise that it accepts only .jpg
        File f = fc.showOpenDialog(null);                                                           //opens browsing dialog window
        String pathImage;                                                                           //initialise string of the path of the image
        String currentDirectory = System.getProperty("user.dir");                                   // initialise string of workif java directory
        File source;
        File dest = new File(currentDirectory + "\\src\\moviebooking\\" + movieName.getText() + ".jpg");    //sets file of destination for the copying of the image
        if (f != null && !movieName.getText().equals("")) {                                          //if a file is chosen and the movie has a name:

            pathImage = f.getAbsolutePath();                                                        //get the path of the chosen image
            source = new File(pathImage);                                                           //create the variable for the source of the copying

            try {       
                FileUtils.copyFile(source, dest);                                                   //copy the image in a directory in the java project file
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(dest);
            firstLabel.setText("Image successfully imported !");                                    //message of the successfull selection

        } else {
            firstLabel.setText("No files/incompatible file OR name of movie not entered.");
        }   //message if the file dont corresptond or if the name is not entered (the name of the movie is the name of the .jpg image for coding and printing purposes)

    }

    @FXML
    private ChoiceBox<String> movieSelect;

    @FXML
    private ImageView imageMovie;

    //AJOUTER LES NOMBRES DE TICKETS DANS LA SESSION
    @FXML
    void CB_movie(ActionEvent event) { //executed on selection of the movie

        try {
            Image myImage = new Image(getClass().getResourceAsStream(movieSelect.getValue().toString() + ".jpg"));
            imageMovie.setImage(myImage);
        } catch (Exception e) {
            
            System.out.println(e);

        }

    }

    public void populateMovieSelect() {
        SQLMgmt sql = new SQLMgmt();

        ArrayList<String> list_movie = sql.SQLListMoviesForEmployee();

        movieSelect.getItems().clear();

        for (String elem : list_movie) {
            movieSelect.getItems().addAll(elem);
        }
    }
    
    @FXML
    private ChoiceBox<String> screenSelect;
    
    public void populateScreenSelect(){
        SQLMgmt sql = new SQLMgmt();

        ArrayList<String> list_screen = sql.SQLListScreensForEmployee();

        screenSelect.getItems().clear();

        for (String elem : list_screen) {
            
            screenSelect.getItems().addAll(elem);
            
        }
    }
    
    @FXML
    private Button addSession;
    
    @FXML
    void addSession(ActionEvent event) {
        SQLMgmt sql = new SQLMgmt();
        if(testTime(timeSession.getText())== false){
            secondLabel.setText("The time is not usable");
        } else {
            //screenSelect.getValue()
            //System.out.println(dateSession.getValue());
            sql.SQLAddSession(movieSelect.getValue(),Integer.valueOf(screenSelect.getValue()),dateSession.getValue(),timeSession.getText());
            secondLabel.setText("The session is added");
        }
    }

    @FXML
    void removeSession(ActionEvent event) {
        SQLMgmt sql = new SQLMgmt();
        sql.SQLRemoveSession(movieSelect.getValue(),Integer.valueOf(screenSelect.getValue()),dateSession.getValue(),timeSession.getText());
        secondLabel.setText("The session is removed");
        
    }
    
    
    @FXML private Button removeSession;

    @FXML private TextField timeSession;
    
    @FXML private DatePicker dateSession; 
    
    private void setAvailableDates(){
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                    dateSession.getValue())//.plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                    }
                };
            }
        };
        dateSession.setDayCellFactory(dayCellFactory);
    }
    
    private boolean testTime(String Stime){ //test the syntax of the time to assure it is suitable for our use
        String[] arrOfStr = Stime.split(":");
        if(arrOfStr.length>2){
            //System.out.println("error in time");
            return false;
        }
        int hour, min;
        hour = Integer.valueOf(arrOfStr[0]);
        min = Integer.valueOf(arrOfStr[1]);
        if(hour<00 || hour>23){
            System.out.println("hour not exact");
            return false;
        }
        if(min<00 || min>59){
            System.out.println("min is not exact");
            return false;
        }
        
        return true;
    }
    
    @FXML private TableColumn<Session, String> movieColumn;
    
    @FXML private TableColumn<Session, Integer> screenColumn;
    
    @FXML private TableColumn<Session, String> dateColumn;

    @FXML private TableColumn<Session, String> timeColumn;
    
    //@FXML private TableColumn<Session, Double> ticketColumn;
    
    @FXML private TableView<Session> tableSession;
    
    ObservableList<Session> listSessions = FXCollections.observableArrayList(
        //new Session("Avatar",2,"20-04","16:20",250)
    
    );
    
    public void setSessionTable(){
        movieColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("name"));
        screenColumn.setCellValueFactory(new PropertyValueFactory<Session, Integer>("screen"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("time"));
        //ticketColumn.setCellValueFactory(new PropertyValueFactory<Session, Double>("tickets"));
        
        SQLMgmt sql = new SQLMgmt();
        
        listSessions = sql.SQLTableSessionEmployee();
        
        tableSession.setItems(listSessions);
    }
    
    @FXML
    void ticketSalesButton(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTicketsForEmployee.fxml")); //access the ressource of the page for the employee
        Parent root1 =(Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Employee Window");
        stage.show(); //shows the FXML page
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }
    
    @FXML
    void backButton(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml")); //access the ressource of the page for the employee
        Parent root1 =(Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Employee Window");
        stage.show(); //shows the FXML page
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }
}
