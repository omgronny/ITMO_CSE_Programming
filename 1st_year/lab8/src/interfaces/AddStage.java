package interfaces;

import collection.Color;
import commands.AddUpdateCommand;
import commands.CountingByNameCommand;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import collection.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.ResourceBundle;


public class AddStage extends Application implements Initializable {

    private static String type;

    public AddStage() { }

    public AddStage(String type) {
        this.type = type;
    }

    private static SocketChannel writeSocket;

    public AddStage(SocketChannel writeSocket) {
        this.writeSocket = writeSocket;
    }

    public AddStage(String type, SocketChannel writeSocket) {
        this.type = type;
        this.writeSocket = writeSocket;
    }

    @FXML
    private AnchorPane button;

    @FXML
    private Button okTap;

    @FXML
    private TextField nameLine;

    @FXML
    private TextField xCoord;

    @FXML
    private TextField yCoord;

    @FXML
    private TextField studCount;

    @FXML
    private ComboBox<String> educationType;


//    private ObservableList<String> langs = FXCollections.observableArrayList(Semester.EIGHTH.toString(), Semester.FOURTH.toString(),
//            Semester.SECOND.toString(), Semester.SIXTH.toString(), Semester.SEVENTH.toString());
    @FXML
    private ComboBox<String> sem;

    @FXML
    private TextField admName;

    @FXML
    private TextField admHeight;

    @FXML
    private ComboBox<String> admEyes;

    @FXML
    private ComboBox<String> admCountry;

    @FXML
    private TextField locX;

    @FXML
    private TextField locY;

    @FXML
    private TextField locZ;

    @FXML
    void initialize() {

    }


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("interfaces.bundles.locale", new Locale("ru")));

        Parent root = fxmlLoader.load();
        //root.setCom

        if (this.type.equals("add")) {
            stage.setTitle("Add Command");
        } else {
            stage.setTitle("Add if max Command");
        }

        stage.setScene(new Scene(root, 500, 800));

        stage.show();
    }

    public static void errorMessage(Label label) {

        JFrame frame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(new Rectangle((int)dimension.getWidth()/2 - 50, (int)dimension.getHeight()/2 - 225, 450, 100));
        frame.setTitle("Error");

        frame.setLayout(new FlowLayout());

        frame.add(new JLabel(label.getText()));

        frame.setVisible(true);
        /*

        Stage stage = new Stage();

        stage.setTitle("Error");
        stage.setHeight(100);
        stage.setWidth(450);

        Scene scene = new Scene(label);
        stage.setScene(scene);
        stage.show();

         */

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sem.getItems().setAll(Semester.EIGHTH.toString(), Semester.FOURTH.toString(),
                Semester.SECOND.toString(), Semester.SIXTH.toString(), Semester.SEVENTH.toString());

        sem.setValue(Semester.SECOND.toString());

        educationType.getItems().setAll(FormOfEducation.EVENING_CLASSES.toString(),
                FormOfEducation.DISTANCE_EDUCATION.toString(), FormOfEducation.FULL_TIME_EDUCATION.toString());

        educationType.setValue(FormOfEducation.EVENING_CLASSES.toString());

        admEyes.getItems().setAll(Color.BROWN.toString(), Color.ORANGE.toString(), Color.WHITE.toString(), Color.YELLOW.toString());
        admEyes.setValue(Color.BROWN.toString());

        admCountry.getItems().setAll(Country.FRANCE.toString(), Country.ITALY.toString(),
                Country.SOUTH_KOREA.toString(), Country.USA.toString());

        admCountry.setValue(Country.ITALY.toString());

        okTap.setOnAction(e -> {

            StudyGroup studyGroup = new StudyGroup();
            //studyGroup.setCreationDate("2021-04-17T18:05:41.392306700");

            //boolean isCorrect = false;

            //System.out.println(nameLine.getAccessibleText());
            //System.out.println(nameLine.getText());
            if (!nameLine.getText().equals("")) {
                studyGroup.setName(nameLine.getText());
            } else {
                errorMessage(new Label("Поле имени не может быть пустым, попробуйте снова"));
                return;
            }

            if (!xCoord.getText().equals("")) {

                try {

                    double x = Double.parseDouble(xCoord.getText());
                    //System.out.println(x);
                    Coordinates c = new Coordinates(x);
                    c.setX(x);
                    studyGroup.setCoordinates(c);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Координата Х  группы должна быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Поле координаты Х группы не может быть пустым, попробуйте снова"));
                return;
            }

            if (!yCoord.getText().equals("")) {

                try {

                    int y = Integer.parseInt(yCoord.getText());
                    Coordinates c = studyGroup.getCoordinates();
                    c.setY(y);
                    studyGroup.setCoordinates(c);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Координата Y группы должна быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Поле координаты Y группы не может быть пустым, попробуйте снова"));
                return;
            }

            if (!studCount.getText().equals("")) {

                try {

                    int y = Integer.parseInt(studCount.getText());

                    studyGroup.setStudentsCount((long) y);

                    //System.out.println("y = " + y);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Количество студентов должно быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Количество студентов не может быть пустым, попробуйте снова"));
                return;
            }

            studyGroup.setSemesterEnum(Semester.valueOf(sem.getValue()));

            studyGroup.setFormOfEducation(FormOfEducation.valueOf(educationType.getValue()));

            //System.out.println(admEyes.getValue());
            Person person = new Person();
            person.setEyeColor(Color.valueOf(admEyes.getValue()));
            studyGroup.setGroupAdmin(person);

            person.setNationality(Country.valueOf(admCountry.getValue()));

            if (!admName.getText().equals("")) {

                Person p = studyGroup.getGroupAdmin();
                p.setName(admName.getText());

                studyGroup.setGroupAdmin(p);

                //System.out.println(admName.getText());

            } else {
                errorMessage(new Label("Поле имени админа не может быть пустым, попробуйте снова"));
                return;
            }

            if (!admHeight.getText().equals("")) {

                try {

                    int y = Integer.parseInt(admHeight.getText());

                    Person p = studyGroup.getGroupAdmin();
                    p.setHeight(y);

                    studyGroup.setGroupAdmin(p);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Рост админа должен быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Рост админа не может быть пустым, попробуйте снова"));
                return;
            }


            if (!locX.getText().equals("")) {

                try {

                    double x = Double.parseDouble(locX.getText());

                    Person p = studyGroup.getGroupAdmin();
                    Location location = new Location(x);

                    p.setLocation(location);

                    studyGroup.setGroupAdmin(p);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Координата Х должна быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Поле координаты Х не может быть пустым, попробуйте снова"));
                return;
            }

            if (!locY.getText().equals("")) {

                try {

                    int y = Integer.parseInt(locY.getText());

                    Person p = studyGroup.getGroupAdmin();
                    Location location = studyGroup.getGroupAdmin().getLocation();

                    location.setY(y);
                    p.setLocation(location);

                    studyGroup.setGroupAdmin(p);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Координата Y группы должна быть числом"));
                    return;
                }

            } else {
                errorMessage(new Label("Поле координаты Y группы не может быть пустым, попробуйте снова"));
                return;
            }

            if (!locZ.getText().equals("")) {

                try {

                    int z = Integer.parseInt(locZ.getText());

                    Person p = studyGroup.getGroupAdmin();
                    Location location = studyGroup.getGroupAdmin().getLocation();
                    location.setName("loc");

                    location.setY(z);
                    p.setLocation(location);

                    studyGroup.setGroupAdmin(p);

                } catch (NumberFormatException ex) {
                    errorMessage(new Label("Координата Z группы должна быть числом"));
                    return;
                }


            } else {
                errorMessage(new Label("Поле координаты Z группы не может быть пустым, попробуйте снова"));
                return;
            }

            // send with add / addifmax



            Commander commander = new Commander();
            RegisterStage register = new RegisterStage();
            studyGroup.setCreator(register.getRegister().getLogin());
            //System.out.println(studyGroup);
            AddUpdateCommand addUpdateCommand;
            if (this.type.equals("add")) {
                addUpdateCommand = new AddUpdateCommand(studyGroup, 1);
            } else {
                addUpdateCommand = new AddUpdateCommand(studyGroup, 4);
            }
            commander.send(addUpdateCommand.toBytes(), this.writeSocket);
            Commander.setIsShower(true);

            Stage stage = (Stage) okTap.getScene().getWindow();
            stage.close();

        });


    }
}
