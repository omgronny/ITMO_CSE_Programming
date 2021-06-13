package interfaces;

import collection.Collection;
import collection.Condition;
import collection.InterfaceGroup;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
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
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static interfaces.AddStage.errorMessage;

public class ShowStage extends Application implements Initializable {

    private static ArrayList<InterfaceGroup> interfaceGroups;

    public static HashMap<Circle, InterfaceGroup> getPairs() {
        return pairs;
    }

    private static double kX;
    private static double kY;

    public static void setkX(double kX) {
        ShowStage.kX = kX;
    }

    public static void setkY(double kY) {
        ShowStage.kY = kY;
    }

    private static HashMap<Circle, InterfaceGroup> pairs = new HashMap<>();

    private static String str;

    private static boolean isOk;

    public ShowStage(ArrayList<InterfaceGroup> interfaceGroups, String str) {
        this.interfaceGroups = interfaceGroups;
        this.str = str;
    }

    public ShowStage() { }

//    @FXML
//    private AnchorPane pane;
//
//    @FXML
//    private Circle circle;

    private static Group group;

    public static void setGroup(Group group) {
        ShowStage.group = group;
    }

    public static Group getGroup() {
        return group;
    }

    public static boolean isIsOk() {
        return isOk;
    }

    public static void setIsOk(boolean isOk) {
        ShowStage.isOk = isOk;
    }

    public static ArrayList<InterfaceGroup> getInterfaceGroups() {
        return interfaceGroups;
    }

    public static void setInterfaceGroups(ArrayList<InterfaceGroup> interfaceGroups) {
        ShowStage.interfaceGroups = interfaceGroups;
    }

    public static void deleter(InterfaceGroup interfaceGroup) {

        for (int i = 0; i < group.getChildren().size(); i++) {

            try {
                //System.out.println(((Circle) group.getChildren().get(i)).getCenterX());
                if (((Circle) group.getChildren().get(i)).getCenterX() - 400 == interfaceGroup.getCoordinates().getX()*kX &&
                        ((Circle) group.getChildren().get(i)).getCenterY()*(-1) + 400 == interfaceGroup.getCoordinates().getY()*kY) {

                    pairs.remove((Circle) group.getChildren().get(i));
                    group.getChildren().remove(group.getChildren().get(i));

                    break;

                }

            } catch (ClassCastException ignore) { }

        }

    }

    public static void updater(InterfaceGroup interfaceGroup) {

        InterfaceGroup old = null;

        for (Circle c : pairs.keySet()) {
            if (pairs.get(c).getId() == interfaceGroup.getId()) {
                old = pairs.get(c);
                break;
            }
        }

        deleter(old);

    }

    public static void changeColour(InterfaceGroup interfaceGroup) {

        for (int i = 0; i < group.getChildren().size(); i++) {

            try {
                //System.out.println(((Circle) group.getChildren().get(i)).getCenterX());
                if (((Circle) group.getChildren().get(i)).getCenterX() - 400 == interfaceGroup.getCoordinates().getX()*kX &&
                        ((Circle) group.getChildren().get(i)).getCenterY()*(-1) + 400 == interfaceGroup.getCoordinates().getY()*kY) {

                    Circle circle = (Circle) group.getChildren().get(i);

                    circle.setStroke(javafx.scene.paint.Color.rgb(243, 154, 35));
                    circle.setFill(javafx.scene.paint.Color.rgb(50, 180, 148));

                    pairs.remove(group.getChildren().get(i));

                    group.getChildren().remove(group.getChildren().get(i));
                    group.getChildren().add(circle);

                    pairs.put(circle, interfaceGroup);

                    break;

                }

            } catch (ClassCastException ignore) { }

        }

    }

    private static void mouser(Circle circle) {

        Stage stage = new Stage();

        InfoStage infoStage = new InfoStage(pairs.get(circle).shower(), 400, 400);

        try {
            infoStage.start(stage);
        } catch (Exception exception) {

        }

    }

    public static void changer(InterfaceGroup interfaceGroup) {

        Condition condition = interfaceGroup.getCondition();

        Circle circle = new Circle(interfaceGroup.getCoordinates().getX()*kX + 400, interfaceGroup.getCoordinates().getY() * kY * (-1) + 400,
                15);

        circle.setStroke(javafx.scene.paint.Color.rgb(243, 154, 35));
        circle.setFill(javafx.scene.paint.Color.rgb(50, 180, 148));
        circle.setStrokeWidth(2);

        circle.setOnMouseClicked(event -> {
            mouser(circle);
        });

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                try {

                    if (condition.equals(Condition.INSERT)) {

                        group.getChildren().add(circle);
                        pairs.put(circle, interfaceGroup);

                    } else if (condition.equals(Condition.DELETE)) {

                        deleter(interfaceGroup);

                    } else if (condition.equals(Condition.UPDATE)) {

                        System.out.println("prum pum pum");

                        updater(interfaceGroup);

                        group.getChildren().add(circle);
                        pairs.put(circle, interfaceGroup);

                    } else {
                        changeColour(interfaceGroup);
                    }


                    if (!condition.equals(Condition.DELETE) && !condition.equals(Condition.STANDARD)) {

                        //System.out.println(interfaceGroup.getCondition());

                        try {

                            TranslateTransition translateTransition = new TranslateTransition();
                            translateTransition.setDuration(Duration.millis(1500));
                            translateTransition.setNode(circle);

                            //double p = (50^2)/Math.sqrt(circle.getCenterX()*circle.getCenterX() + circle.getCenterY()*circle.getCenterY());
                            //double k = Math.sqrt(circle.getCenterX()*circle.getCenterX() + circle.getCenterY()*circle.getCenterY())/p;

                            if (circle.getCenterY() - 400 > 0) {
                                translateTransition.setToY(2*circle.getCenterY()*(-1) + 800);
                            } else {
                                translateTransition.setToY(2*circle.getCenterY()*(-1) + 800);
                            }

                            if (circle.getCenterX() - 400 > 0) {
                                translateTransition.setToX((-1)*(2*circle.getCenterX() - 800));
                            } else {
                                translateTransition.setToX((-1)*(2*circle.getCenterX() - 800));
                            }

                            translateTransition.setAutoReverse(true);
                            translateTransition.setCycleCount(2);

                            circle.setStroke(javafx.scene.paint.Color.rgb(50, 180, 148));
                            circle.setFill(javafx.scene.paint.Color.rgb(243, 154, 35));

                            translateTransition.play();

                            //Thread.sleep(1400);

//                            circle.setStroke(javafx.scene.paint.Color.rgb(243, 154, 35));
//                            circle.setFill(javafx.scene.paint.Color.rgb(50, 180, 148));


                        } catch (Exception e) {
                            System.out.println("Duration error");
                        }

                    }



                } catch (NullPointerException ignore) {

                }

            }

        };

        Platform.runLater(runnable);

    }


    @Override
    public void start(Stage stage) throws Exception {

        //Parent root = FXMLLoader.load(getClass().getResource("show.fxml"));

        Group group1 = new Group();

        Line line = new Line(400, 0, 400, 800);
        Line line2 = new Line(0, 400, 800, 400);


        Line arrowY1 = new Line(395, 5, 400, 0);
        Line arrowY2 = new Line(400, 0, 405, 5);

        Line arrowX1 = new Line(795, 395, 800, 400);
        Line arrowX2 = new Line(800, 400, 795, 405);

        Text textX = new Text("X");

        textX.setLayoutX(790);
        textX.setLayoutY(415);

        group1.getChildren().add(textX);

        Text textY = new Text("Y");

        textY.setLayoutX(385);
        textY.setLayoutY(10);

        group1.getChildren().add(textY);

        group1.getChildren().add(line);
        group1.getChildren().add(line2);

        group1.getChildren().add(arrowY1);
        group1.getChildren().add(arrowY2);

        group1.getChildren().add(arrowX1);
        group1.getChildren().add(arrowX2);

        for (InterfaceGroup interfaceGroup : interfaceGroups) {

            if (interfaceGroup.getCondition().equals(Condition.DELETE)) {
                continue;
            }

            Circle circle = new Circle((interfaceGroup.getCoordinates().getX())*kX + 400, interfaceGroup.getCoordinates().getY()*kY * (-1) + 400,
                    15);

            circle.setStroke(javafx.scene.paint.Color.rgb(243, 154, 35));
            circle.setFill(javafx.scene.paint.Color.rgb(50, 180, 148));
            circle.setStrokeWidth(2);

            circle.setOnMouseClicked(event -> {
                mouser(circle);
            });

            if (!group1.getChildren().contains(circle)) {

                group1.getChildren().add(circle);

                if (!pairs.containsKey(interfaceGroup)) {
                    pairs.put(circle, interfaceGroup);
                }

            }

        }

        group = group1;

        stage.setTitle("Coordinates");

        stage.setScene(new Scene(group, 800, 800));

//        System.out.println(interfaceGroups);
//
//        stage.setScene(new Scene(group, 800, 800));
//
//        stage.setResizable(true);

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                while (true) {
//
//                    Group group1 = new Group();
//                    Line line = new Line(400, 0, 400, 800);
//                    Line line2 = new Line(0, 400, 800, 400);
//
//                    //group.getChildren().add(line);
//                    //group.getChildren().add(line2);
//
//                    for (InterfaceGroup interfaceGroup : interfaceGroups) {
//
//                        Circle circle = new Circle(interfaceGroup.getCoordinates().getX() + 400, interfaceGroup.getCoordinates().getY() * (-1) + 400,
//                                15);
//
//                        circle.setStroke(javafx.scene.paint.Color.rgb(243, 154, 35));
//                        circle.setFill(javafx.scene.paint.Color.rgb(50, 180, 148));
//                        circle.setStrokeWidth(2);
//
//                        group.getChildren().add(circle);
//
//                    }
//
//                    //group = group1;
//
//                    try {
//                        Thread.sleep(2500);
//                    } catch (InterruptedException e) {
//
//                    }
//
//                }
//
//            }
//        };
//
//        Platform.runLater(runnable);

        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                while (true) {
//
//                    //Group group1 = new Group();
//                    Line line = new Line(400, 0, 400, 800);
//                    Line line2 = new Line(0, 400, 800, 400);
//
//                    //group.getChildren().add(line);
//                    //group.getChildren().add(line2);
//
//                    for (InterfaceGroup interfaceGroup : interfaceGroups) {
//
//                        Circle circle = new Circle(interfaceGroup.getCoordinates().getX() + 400, interfaceGroup.getCoordinates().getY() * (-1) + 400,
//                                15);
//
//                        circle.setStroke(javafx.scene.paint.Color.rgb(243, 154, 35));
//                        circle.setFill(javafx.scene.paint.Color.rgb(50, 180, 148));
//                        circle.setStrokeWidth(2);
//
//                        System.out.println(121212);
//                        group.getChildren().add(circle);
//
//                    }
//
//                    //group = group1;
//
//                    try {
//                        Thread.sleep(2500);
//                    } catch (InterruptedException e) {
//
//                    }
//
//                }
//
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();


    }


}
