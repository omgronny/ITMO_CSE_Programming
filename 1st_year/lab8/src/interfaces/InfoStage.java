package interfaces;
import collection.*;
import commands.AddUpdateCommand;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static interfaces.AddStage.errorMessage;

import java.net.URL;
import java.nio.channels.SocketChannel;
import java.util.ResourceBundle;


public class InfoStage extends Application implements Initializable {

    private static String text;
    private static int x;
    private static int y;

    public InfoStage(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public InfoStage() { }

    @FXML
    private AnchorPane button;

    @FXML
    private Button okTap;

    @FXML
    private Label infoLable;


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
        //root.setCom

        stage.setTitle("Info Command");

        stage.setScene(new Scene(root, this.x, this.y));

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        infoLable.setText(text);

        okTap.setOnAction(e -> {

            Stage stage = (Stage) okTap.getScene().getWindow();
            stage.close();

        });

    }
}
