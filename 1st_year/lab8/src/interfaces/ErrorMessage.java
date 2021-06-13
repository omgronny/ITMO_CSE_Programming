package interfaces;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorMessage extends Application implements Initializable {

    private static Label label;

    public ErrorMessage() {
    }

    public ErrorMessage(Label label) {
        this.label = label;
    }

    public void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Error");
        stage.setHeight(100);
        stage.setWidth(450);

        Scene scene = new Scene(label);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
