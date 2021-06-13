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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.ResourceBundle;

public class ContextStage extends Application implements Initializable {

    @FXML
    private AnchorPane button;

    @FXML
    private Button updateTap;

    @FXML
    private Button removeTap;

    private static int standardId;
    private static SocketChannel writeSocket;

    public ContextStage() { }

    public ContextStage(int standardId, SocketChannel writeSocket) {
        this.standardId = standardId;
        this.writeSocket = writeSocket;
    }


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contextFinder.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("interfaces.bundles.locale", new Locale("ru")));

        Parent root = fxmlLoader.load();

        stage.setTitle("Context Menu");

        stage.setScene(new Scene(root, 400, 200));

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateTap.setOnAction(e -> {

            UpdateStage updateStage = new UpdateStage("update", writeSocket, standardId);

            try {
                updateStage.start(new Stage());
            } catch (Exception exception) {

            }

            Stage stage = (Stage) updateTap.getScene().getWindow();
            stage.close();

        });

        removeTap.setOnAction(e -> {

            // send
            RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(standardId,
                    new RegisterStage().getRegister().getLogin());

            Commander commander = new Commander();
            commander.send(removeByIdCommand.toBytes(), writeSocket);
            Commander.setIsShower(true);

            Stage stage = (Stage) removeTap.getScene().getWindow();
            stage.close();

        });

    }


}
