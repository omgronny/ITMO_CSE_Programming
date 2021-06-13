package interfaces;

import collection.Commander;
import commands.RemoveByIdCommand;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.ResourceBundle;

import static interfaces.AddStage.errorMessage;

public class RemoveStage extends Application implements Initializable {

    private static SocketChannel writeSocket;

    public RemoveStage(SocketChannel writeSocket) {
        this.writeSocket = writeSocket;
    }

    public RemoveStage() { }

    @FXML
    private AnchorPane button;

    @FXML
    private Button okTap;

    @FXML
    private TextField idTap;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("removvve.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("interfaces.bundles.locale", new Locale("ru")));

        Parent root = fxmlLoader.load();

        stage.setTitle("Remove Command");

        stage.setScene(new Scene(root, 400, 200));

        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        okTap.setOnAction(e -> {

            if (!idTap.getText().equals("")) {

                try {

                    int y = Integer.parseInt(idTap.getText());
                    // send
                    RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(y,
                            new RegisterStage().getRegister().getLogin());

                    Commander commander = new Commander();
                    commander.send(removeByIdCommand.toBytes(), writeSocket);
                    Commander.setIsShower(true);


                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Id должен быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Поле Id не может быть пустым, попробуйте снова"));
                return;
            }

            Stage stage = (Stage) okTap.getScene().getWindow();
            stage.close();

        });

    }


}
