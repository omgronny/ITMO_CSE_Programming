package interfaces;

import commands.AddUpdateCommand;
import commands.CountingByNameCommand;
import commands.Register;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import collection.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.ResourceBundle;

import static interfaces.AddStage.errorMessage;

public class RegisterStage extends Application implements Initializable {

    private static Register register;

    private static SocketChannel writeSocket;
    private static SocketChannel readSocket;
    private static java.nio.channels.Selector selector;
    private static ByteBuffer buffer1;
    private static ByteBuffer buffer2;

    public RegisterStage(java.nio.channels.Selector selector, SocketChannel writeSocket, SocketChannel readSocket,
                         ByteBuffer buffer1, ByteBuffer buffer2) {
        this.writeSocket = writeSocket;
        this.readSocket = readSocket;
        this.selector = selector;
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;

    }

    public RegisterStage() { }

    public Register getRegister() {
        return this.register;
    }

    @FXML
    private AnchorPane button;

    @FXML
    private Button signIn;

    @FXML
    private TextField loginTap;

    @FXML
    private PasswordField passwordTap;

    public void testMethod() {
        System.out.println(this.writeSocket);
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Register.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("interfaces.bundles.locale", new Locale("ru")));

        Parent root = fxmlLoader.load();

        //root.setCom

        stage.setTitle("Вход");

        stage.setScene(new Scene(root, 500, 300));

        stage.show();
        stage.toFront();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        signIn.setOnAction(e -> {

            Register register = new Register(null, null);

            if (!loginTap.getText().equals("")) {

                register.setLogin(loginTap.getText());

            } else {
                errorMessage(new Label("Имя пользователя не может быть пустым, попробуйте снова"));
                return;
            }

            if (!passwordTap.getText().equals("")) {

                register.setPassword(passwordTap.getText());

                this.register = register;

            } else {
                errorMessage(new Label("Пароль не может быть пустым, попробуйте снова"));
                return;
            }

            Stage stage1 = (Stage) signIn.getScene().getWindow();
            stage1.close();

            Commander commander = new Commander();
            try {

                System.out.println(this.writeSocket);
                String str = commander.signIn(selector, this, this.writeSocket, this.readSocket);

                if (str.equals("Неверный пароль")) {
                    //testMethod();

                    passwordTap.setText("");
                    stage1.show();
                    return;

                }

                Commander.setIsShower(true);

                Stage stage = new Stage();

                MainStage mainStage = new MainStage(selector, writeSocket, readSocket, buffer1, buffer2);

                try {
                    mainStage.start(stage);
                } catch (Exception exception) {
                    System.out.println("11111111");
                }

            } catch (IOException ioException) {
                System.out.println("Оштбка в методе сигн ин");
            }




        });

    }
}
