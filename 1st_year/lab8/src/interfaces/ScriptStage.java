package interfaces;

import commands.ExecuteScriptCommand;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

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

import java.io.File;
import java.net.URL;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

import static interfaces.AddStage.errorMessage;

public class ScriptStage extends Application implements Initializable {

    private static SocketChannel writeSocket;

    public ScriptStage(SocketChannel writeSocket) {
        this.writeSocket = writeSocket;
    }

    public ScriptStage() { }

    @FXML
    private AnchorPane button;

    @FXML
    private Button okTap;

    @FXML
    private TextField scriptTap;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("script.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("interfaces.bundles.locale", new Locale("ru")));

        Parent root = fxmlLoader.load();

        //root.setCom

        stage.setTitle("Execute script Command");

        stage.setScene(new Scene(root, 400, 200));

        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        okTap.setOnAction(e -> {
            if (!scriptTap.getText().equals("")) {

                File file = new File(scriptTap.getText());

                if(!file.exists()) {
                    errorMessage(new Label("Такого файла не существует, попробуйте снова"));
                    return;
                }

                Path pat = Paths.get(scriptTap.getText());

                if (!Files.isReadable(pat) && file.exists()) {
                    errorMessage(new Label("Ошибка прав при исполнении скрипта. Нужны права на чтение"));
                    return;
                }

            } else {
                errorMessage(new Label("Поле имени файла не может быть пустым, попробуйте снова"));
                return;
            }

            ExecuteScriptCommand.executeScript(scriptTap.getText(), writeSocket);

            Stage stage = (Stage) okTap.getScene().getWindow();
            stage.close();

        });

    }
}
