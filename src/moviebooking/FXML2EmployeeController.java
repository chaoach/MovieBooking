/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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

    }   
    
    @FXML
    private TextField movieName;

    @FXML
    private TextField price;
    
    @FXML
    private Label firstLabel;

    @FXML
    void addMovieButton(ActionEvent event) {
        double priceDouble = Double.parseDouble(price.getText());
        addMovie(movieName.getText(), priceDouble);
        firstLabel.setText("The movie as been imported to DB");  
        populateMovieSelect();
    }
    
    public void addMovie(String name, double price){
        SQLMgmt sql = new SQLMgmt();
        sql.SQLAddMovie(name,price);
    }
    
    @FXML
    private Button addPictureButton;
    
    @FXML
    void addPicture(ActionEvent event) {
        FileChooser fc = new FileChooser();                                                         //create object to handle file browsing and selecting
        fc.getExtensionFilters().add(new ExtensionFilter("Images Files","*.jpg"));                  //precise that it accepts only .jpg
        File f = fc.showOpenDialog(null);                                                           //opens browsing dialog window
        String pathImage;                                                                           //initialise string of the path of the image
        String currentDirectory = System.getProperty("user.dir");                                   // initialise string of workif java directory
        File source;                                                                                
        File dest = new File(currentDirectory + "/src/movie_images/" + movieName.getText() +".jpg");    //sets file of destination for the copying of the image
        if (f != null && !movieName.getText().equals("")){                                          //if a file is chosen and the movie has a name:
            
            pathImage = f.getAbsolutePath();                                                        //get the path of the chosen image
            source = new File(pathImage);                                                           //create the variable for the source of the copying
            
            try {       
                FileUtils.copyFile(source, dest);                                                   //copy the image in a directory in the java project file
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(dest);
            firstLabel.setText("Image successfully imported !");                                    //message of the successfull selection
            
        } else { firstLabel.setText("No files/incompatible file OR name of movie not entered.");}   //message if the file dont corresptond or if the name is not entered (the name of the movie is the name of the .jpg image for coding and printing purposes)
        
    }
    
    @FXML
    private ChoiceBox<String> movieSelect;
    
    @FXML
    private ImageView imageMovie;
    
    //AJOUTER LES NOMBRES DE TICKETS DANS LA SESSION
    
    @FXML
    void CB_movie(ActionEvent event){ //executed on selection of the movie
        String imagePath = System.getProperty("user.dir").toString() + "/src/movie_images/" + movieSelect.getValue().toString() + ".jpg";
        System.out.println(imagePath);
        Image image = new Image(imagePath);
        
        imageMovie.setImage(image);
    }
    
    public void populateMovieSelect(){
        SQLMgmt sql = new SQLMgmt();

        ArrayList<String> list_movie = sql.SQLListMoviesForEmployee();

        movieSelect.getItems().clear();

        for (String elem : list_movie) {
            movieSelect.getItems().addAll(elem);
        }
    }

    
}
