package interfaces;

import commands.HelpCommand;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;

import java.util.ResourceBundle;



public class HelpStage extends Application implements Initializable {

    //private Stage stage;

    @FXML
    private AnchorPane button;

    @FXML
    private Button okTap;

    @Override
    public void start(Stage stage) throws Exception {

        //this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("help.fxml"));
        //root.setCom

        stage.setTitle("Help Command");

        stage.setScene(new Scene(root, 800, 500));

        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        okTap.setOnAction(e -> {

            Stage stage = (Stage) okTap.getScene().getWindow();
            stage.close();

        });

    }
}

