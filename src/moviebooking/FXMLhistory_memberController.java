/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebooking;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rakul
 */
public class FXMLhistory_memberController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String url = "jdbc:mysql://localhost:3306/site_cinema"; //url of DB
    private String user = "root";
    private String password = "";

    private ObservableList<ObservableList> data;
    private TableView tableview;
    @FXML
    private Label lb_history;

    @FXML
    private TableView<String> table_view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SQLMgmt sql = new SQLMgmt();

        FXMLLoginController login_page = new FXMLLoginController(); // constructeur pour avoir acces au login
        String login = login_page.getLogin();

        String list_history = sql.SQLListhistory(login);
        System.out.println(list_history);

        lb_history.setText(list_history);
        
        //buildData();
        // TODO
    }

    public void buildData() {
        String list_history = "name_movie\t\tdate_movie\t\ttime_movie\t\tnom\t\tprenom\t\tmail\t\tscreen\t\tnb_ticket \n";;

        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM site_reservation ");

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                table_view.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

}
