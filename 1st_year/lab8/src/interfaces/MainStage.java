package interfaces;

import collection.*;
import commands.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import parse.Parce;

import static java.nio.ByteBuffer.allocate;
import static java.nio.channels.SelectionKey.OP_READ;


public class MainStage extends Application implements Initializable {

    private static SocketChannel s;
    private static SocketChannel readSocket;
    private static ByteBuffer writeBuffer;
    private static ByteBuffer readBuffer;
    private static Selector selector;

    public MainStage(Selector selector, SocketChannel s, SocketChannel readSocket, ByteBuffer buffer1, ByteBuffer buffer2) {
        this.s = s;
        this.readSocket = readSocket;
        this.writeBuffer = buffer1;
        this.readBuffer = buffer2;
        this.selector = selector;
    }

    private static String infoText;

    private static int filterId;
    private static long filterCount;
    private static long filterAdmHeight;
    private static String filterName;
    private static String filterAdmName;
    private static String filterType;
    private static String filterSem;
    private static String filterColor;
    private static String filterCountry;


    public MainStage() {
    }

    @FXML
    private AnchorPane button;

    @FXML
    private Button addTap;

    @FXML
    private Button updateTap;

    @FXML
    private Button removeTap;

    @FXML
    private Button insertTap;

    @FXML
    private Button addIfMaxTap;

    @FXML
    private Button helpTap;

    @FXML
    private Button infoTap;

    @FXML
    private Button showTap;

    @FXML
    private Button clearTap;

    @FXML
    private Button scriptTap;

    @FXML
    private Button exitTap;

    @FXML
    private Button userTap;

    @FXML
    private ComboBox<Integer> filterIdTap;

    @FXML
    private ComboBox<String> filterNameTap;

    @FXML
    private ComboBox<Long> filterCountTap;

    @FXML
    private ComboBox<String> filterSemTap;

    @FXML
    private ComboBox<String> filterAdmNameTap;

    @FXML
    private ComboBox<Long> filterAdmHeightTap;

    @FXML
    private ComboBox<String> filterColorTap;

    @FXML
    private ComboBox<String> filterCountryTap;

    @FXML
    private ComboBox<String> filterTypeTap;

    @FXML
    private TableView<InterfaceGroup> table;

    @FXML
    private TableColumn<InterfaceGroup, Integer> IdCol= new TableColumn<InterfaceGroup, Integer>("Id");

    @FXML
    private TableColumn<InterfaceGroup, String> nameColumn = new TableColumn<InterfaceGroup, String>("name");

    @FXML
    private TableColumn<InterfaceGroup, Long> countColumn = new TableColumn<InterfaceGroup, Long>("count");

    @FXML
    private TableColumn<InterfaceGroup, FormOfEducation> typeColumn = new TableColumn<InterfaceGroup, FormOfEducation>("type");

    @FXML
    private TableColumn<InterfaceGroup, Semester> semColumn = new TableColumn<InterfaceGroup, Semester>("sem");

    @FXML
    private TableColumn<InterfaceGroup, String> admNameColumn = new TableColumn<InterfaceGroup, String>("admName");

    @FXML
    private TableColumn<InterfaceGroup, Long> admHeightColumn = new TableColumn<InterfaceGroup, Long>("admHeight");

    @FXML
    private TableColumn<InterfaceGroup, Color> colorColumn = new TableColumn<InterfaceGroup, Color>("color");

    @FXML
    private TableColumn<InterfaceGroup, Country> countryColumn = new TableColumn<InterfaceGroup, Country>("country");

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.resourceBundle = resourceBundle;

        nameColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, String>("name"));
        IdCol.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, Integer>("id"));
        countColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, Long>("studentsCount"));

        typeColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, FormOfEducation>("formOfEducation"));
        semColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, Semester>("semesterEnum"));

        admHeightColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, Long>("height"));
        admNameColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, String>("adminName"));

        colorColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, Color>("eyeColor"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<InterfaceGroup, Country>("nationality"));

        table.setItems(people);

        filterSemTap.getItems().setAll("", Semester.EIGHTH.toString(), Semester.FOURTH.toString(),
                Semester.SECOND.toString(), Semester.SIXTH.toString(), Semester.SEVENTH.toString());

        filterTypeTap.getItems().setAll("", FormOfEducation.EVENING_CLASSES.toString(),
                FormOfEducation.DISTANCE_EDUCATION.toString(), FormOfEducation.FULL_TIME_EDUCATION.toString());

        filterColorTap.getItems().setAll("", Color.BROWN.toString(), Color.ORANGE.toString(), Color.WHITE.toString(), Color.YELLOW.toString());

        filterCountryTap.getItems().setAll("", Country.FRANCE.toString(), Country.ITALY.toString(),
                Country.SOUTH_KOREA.toString(), Country.USA.toString());

        filterIdTap.getItems().setAll(-1);
        filterCountTap.getItems().setAll((long) -1);
        filterCountTap.getItems().setAll((long) -1);

        filterNameTap.getItems().setAll("");
        filterAdmNameTap.getItems().setAll("");

        /*
        filterIdTap.setValue(-1);
        filterCountTap.setValue((long) -1);
        filterAdmHeightTap.setValue((long) -1);

        filterNameTap.setValue("");
        filterAdmNameTap.setValue("");

         */

        allId.add(-1);
        allCount.add((long) -1);
        allAdminHeights.add((long) -1);

        allNames.add("");
        allAdminNames.add("");



        filterIdTap.getItems().setAll(allId);
        filterNameTap.getItems().setAll(allNames);
        filterCountTap.getItems().setAll(allCount);
        filterAdmNameTap.getItems().setAll(allAdminNames);
        filterAdmHeightTap.getItems().setAll(allAdminHeights);

//        try {
//
//            filterId = filterIdTap.getValue();
//            filterName = filterNameTap.getValue();
//            filterSem = filterSemTap.getValue();
//            filterType = filterTypeTap.getValue();
//            filterColor = filterColorTap.getValue();
//            filterCountry = filterCountryTap.getValue();
//            filterCount = filterCountTap.getValue();
//            filterAdmName = filterAdmNameTap.getValue();
//            filterAdmHeight = filterAdmHeightTap.getValue();
//
//        } catch (NullPointerException e) {
//            System.out.println("nulllll");
//        }

        /*
        SocketChannel finalReadSocket = readSocket;
        SocketChannel finalS = s;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //shower(finalS, finalReadSocket, writeBuffer, readBuffer);

            }

        });


        thread.start();

         */

        Runnable showRunnable = () -> {
            while (true) {

                try {

                    showSender();

                } catch (CancelledKeyException e) {
                    //e.printStackTrace();
                }
            }
        };

        Thread showThread = new Thread(showRunnable);
        showThread.start();

        Runnable runnable = () -> {
            while (true) {

                try {

                    selection(selector, false);

                } catch (CancelledKeyException e) {
                    //e.printStackTrace();
                } catch (IOException e) {

                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        RegisterStage register = new RegisterStage();
        userTap.setText(register.getRegister().getLogin());

        helpTap.setOnAction(e -> {

            Stage stage = new Stage();

            HelpStage helpStage = new HelpStage();

            try {
                helpStage.start(stage);
            } catch (Exception exception) {

            }

        });

        infoTap.setOnAction(e -> {

            Stage stage = new Stage();

            // какая-то специальная обработка send
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException interruptedException) {
//
//            }

            InfoStage infoStage = new InfoStage(infoText, 800, 500);

            try {
                infoStage.start(stage);
            } catch (Exception exception) { }

            stage.show();

        });

        addTap.setOnAction(e -> {

            Stage stage = new Stage();
            AddStage addStage = new AddStage("add", s);

            try {
                addStage.start(stage);
            } catch (Exception exception) { }

        });

        updateTap.setOnAction(e -> {

            Stage stage = new Stage();
            UpdateStage updateStage = new UpdateStage("update", s, 0);

            try {
                updateStage.start(stage);
            } catch (Exception exception) { }

        });

        removeTap.setOnAction(e -> {

            Stage stage = new Stage();
            RemoveStage removeStage = new RemoveStage(s);

            try {
                removeStage.start(stage);
            } catch (Exception exception) { exception.printStackTrace(); }

        });

        insertTap.setOnAction(e -> {

            Stage stage = new Stage();
            UpdateStage updateStage = new UpdateStage("insert", s);

            try {
                updateStage.start(stage);
            } catch (Exception exception) { }

        });

        addIfMaxTap.setOnAction(e -> {

            Stage stage = new Stage();
            AddStage addStage = new AddStage("max", s);

            try {
                addStage.start(stage);
            } catch (Exception exception) { }

        });

        clearTap.setOnAction(e -> {

            ClearByUser clearByUser = new ClearByUser(new RegisterStage().getRegister().getLogin());
            Commander commander = new Commander();
            commander.send(clearByUser.toBytes(), s);

        });

        scriptTap.setOnAction(e -> {

            Stage stage = new Stage();
            ScriptStage scriptStage = new ScriptStage(s);

            try {
                scriptStage.start(stage);
            } catch (Exception exception) { }

        });

        showTap.setOnAction(e -> {

            Stage stage = new Stage();
            ShowStage showStage = new ShowStage(interfaceGroups, "aboba");

            try {
                showStage.start(stage);
            } catch (Exception exception) { }

        });

        exitTap.setOnAction(e -> System.exit(0));

        table.setRowFactory(tv -> {

            TableRow<InterfaceGroup> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    InterfaceGroup clickedRow = row.getItem();

                    try {

                        new ContextStage(clickedRow.getId(), s).start(new Stage());

                    } catch (Exception exception) {

                    }


                }

            });

            return row;

        });



    }

    private ObservableList<InterfaceGroup> people = FXCollections.observableArrayList(

    );

    public void setTableOfPeople(ObservableList<InterfaceGroup> people) {
        table.setItems(people);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parce parce = new Parce();

        //System.out.println(parce.lineToArray(new File("lang.txt")));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainUi.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("interfaces.bundles.locale", new Locale(parce.lineToArray(new File("lang.txt")).get(0))));

        Parent root = fxmlLoader.load();

        primaryStage.setTitle("VT Lab8");
        primaryStage.setScene(new Scene(root, 1000, 700));

        primaryStage.show();


    }


    public void shower(SocketChannel writeChannel, SocketChannel readChannel, ByteBuffer buffer1, ByteBuffer buffer2) {

        try {

            readChannel.configureBlocking(false);
            writeChannel.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            if(!Commander.getIsShower()) {
                continue;
            }

            buffer1.clear();

            ShowCommand showCommand = new ShowCommand();
            buffer1.put(showCommand.toBytes());
            buffer1.flip();

            try {
                writeChannel.write(buffer1);
            } catch (IOException e) {

            }

            buffer2.clear();

            int numRead = -1;

            String nowAnswer;

            while (true) {

                try {

                    buffer2 = allocate(5000);

                    numRead = readChannel.read(buffer2);

                    nowAnswer = Command.fromBytesToString(buffer2.array());

                    if (Commander.getIsShower()) {

                        //System.out.println(nowAnswer);

                        ObservableList<InterfaceGroup> people = FXCollections.observableArrayList(

                        );

                        for (String s : Parce.separateByEnter(nowAnswer)) {

                            if (!s.equals("")) {

                                InterfaceGroup interfaceGroup = Parce.stringToInterfaceGroup(s);
                                people.add(interfaceGroup);
                                interfaceGroups.add(interfaceGroup);

                            }

                        }

                        table.setItems(people);

                    }

                    Runnable runnable = () -> {
                        while (true) {

                            try {

                                selection(selector, false);

                            } catch (CancelledKeyException e) {
                                //e.printStackTrace();
                            } catch (IOException e) {

                            }
                        }
                    };

                    Thread thread = new Thread(runnable);
                    thread.start();

                    return;

                } catch (IOException | ClassNotFoundException | ClassCastException e) {

                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }

            }


        }


    }

    public void showSender() {

        while (true) {

            ShowCommand showCommand = new ShowCommand();
            Commander commander = new Commander();
            commander.send(showCommand.toBytes(), s);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }

            InfoCommand infoCommand = new InfoCommand();
            commander.send(infoCommand.toBytes(), s);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }


        }

    }

    ArrayList<Integer> allId = new ArrayList<>();
    ArrayList<Long> allCount = new ArrayList<>();
    ArrayList<String> allNames = new ArrayList<>();
    ArrayList<String> allAdminNames = new ArrayList<>();
    ArrayList<Long> allAdminHeights = new ArrayList<>();

    private static ArrayList<InterfaceGroup> interfaceGroups = new ArrayList<>();

    public void simpleShower(String nowAnswer) throws IllegalStateException {

        if (Commander.getIsShower()) {

            ObservableList<InterfaceGroup> people = FXCollections.observableArrayList(

            );

            ArrayList<InterfaceGroup> interfaceGroups1 = new ArrayList<>();

            //System.out.println(ShowStage.getPairs());

            for (String s : Parce.separateByEnter(nowAnswer)) {

                if (!s.equals("")) {

                    InterfaceGroup interfaceGroup = Parce.stringToInterfaceGroup(s);
                    interfaceGroups1.add(interfaceGroup);

                    if (interfaceGroup.getCondition().equals(Condition.DELETE)) {
                        continue;
                    }

                    if (!allId.contains(interfaceGroup.getId())) {
                        allId.add(interfaceGroup.getId());
                        filterIdTap.getItems().add(interfaceGroup.getId());

                    }

                    if (!allCount.contains(interfaceGroup.getStudentsCount())) {
                        allCount.add(interfaceGroup.getStudentsCount());
                        filterCountTap.getItems().add(interfaceGroup.getStudentsCount());
                    }

                    if (!allNames.contains(interfaceGroup.getName())) {
                        allNames.add(interfaceGroup.getName());
                        filterNameTap.getItems().add(interfaceGroup.getName());

                    }

                    if (!allAdminNames.contains(interfaceGroup.getAdminName())) {
                        allAdminNames.add(interfaceGroup.getAdminName());
                        filterAdmNameTap.getItems().add(interfaceGroup.getAdminName());

                    }

                    if (!allAdminHeights.contains(interfaceGroup.getHeight())) {

                        allAdminHeights.add(interfaceGroup.getHeight());
                        filterAdmHeightTap.getItems().add(interfaceGroup.getHeight());

                    }

                    try {

                        if ((filterIdTap.getValue() == null || interfaceGroup.getId() == filterIdTap.getValue() || filterIdTap.getValue() == -1) &&
                                (filterNameTap.getValue() == null || interfaceGroup.getName().equals(filterNameTap.getValue()) || filterNameTap.getValue().equals("")) &&
                                (filterCountTap.getValue() == null || filterCountTap.getValue().equals(interfaceGroup.getStudentsCount()) || filterCountTap.getValue() == -1) &&
                                (filterAdmNameTap.getValue() == null || interfaceGroup.getAdminName().equals(filterAdmNameTap.getValue()) || filterAdmNameTap.getValue().equals("")) &&
                                (filterAdmHeightTap.getValue() == null || interfaceGroup.getHeight().equals(filterAdmHeightTap.getValue()) || filterAdmHeightTap.getValue() == -1) &&
                                (filterTypeTap.getValue() == null || filterTypeTap.getValue().equals(interfaceGroup.getFormOfEducation().toString()) || filterTypeTap.getValue().equals("")) &&
                                (filterSemTap.getValue() == null || filterSemTap.getValue().equals(interfaceGroup.getSemesterEnum().toString()) || filterSemTap.getValue().equals("")) &&
                                (filterColorTap.getValue() == null || filterColorTap.getValue().equals(interfaceGroup.getEyeColor().toString()) || filterColorTap.getValue().equals("")) &&
                                (filterCountryTap.getValue() == null || filterCountryTap.getValue().equals(interfaceGroup.getNationality().toString()) || filterCountryTap.getValue().equals(""))) {

                            people.add(interfaceGroup);

                        }

                    } catch (NullPointerException e) {

                    }


                }

            }

            interfaceGroups = interfaceGroups1;

            ShowStage.setInterfaceGroups(interfaceGroups);

            //Platform.setImplicitExit(false);

            //ArrayList<Circle> circles = new ArrayList<>();

            double maxX = 1;
            int maxY = 1;

            for (InterfaceGroup interfaceGroup : interfaceGroups) {

                maxX = Math.max(Math.abs(interfaceGroup.getCoordinates().getX()), maxX);
                maxY = Math.max(Math.abs(interfaceGroup.getCoordinates().getY()), maxY);

            }

            ShowStage.setkX(1);
            ShowStage.setkY(1);


            for (InterfaceGroup interfaceGroup : interfaceGroups) {

                try {
                    ShowStage.changer(interfaceGroup);
                } catch (Exception e) {
                    System.out.println("error on changer" + a);
                }

            }



            //if (a == 0) {
                //ShowStage.setGroup(group1);
            //}

            a++;

            table.setItems(people);

//            filterIdTap.getItems().removeAll(allId);
//            filterIdTap.getItems().addAll(allId);
//            filterNameTap.getItems().setAll(allNames);
//            filterCountTap.getItems().setAll(allCount);
//            filterAdmNameTap.getItems().setAll(allAdminNames);
//            filterAdmHeightTap.getItems().setAll(allAdminHeights);



            //filterIdTap.setValue(pr);


        }


    }

    private static int a = 0;

    public static int getA() {
        return MainStage.a;
    }

    private void finishConnection(SelectionKey key, int par) throws IOException {

        SocketChannel socketChannel = (SocketChannel) key.channel();

        try {
            socketChannel.finishConnect();
        } catch (IOException e) {

            key.cancel();
            return;
        }

        if (par == 0) {
            key.interestOps(SelectionKey.OP_WRITE);
        } else {
            key.interestOps(SelectionKey.OP_READ);
        }

    }

    private void read(SelectionKey key, ByteBuffer readBuffer) throws IOException {

        SocketChannel socketChannel = (SocketChannel) key.channel();

        readBuffer.clear();


        int numRead = -1;

        try {

            numRead = socketChannel.read(readBuffer);

            String nowAnswer = Command.fromBytesToString(readBuffer.array());
            //System.out.println(Command.fromBytesToString(readBuffer.array()));

            try {

                Integer.parseInt(String.valueOf(0));

                try {
                    simpleShower(nowAnswer);
                } catch (Exception ignore) { }

            } catch (NumberFormatException e) {

            }

            if (nowAnswer.equals("Элемент был не максимальный") ||
                    nowAnswer.equals("Элемент с таким id пренадлежит не вам") ||
                    nowAnswer.equals("Элемента с таким id не нашлось") ||
                    nowAnswer.equals("Такого id нет")) {

                AddStage.errorMessage(new Label(nowAnswer));

            }

            if (Parce.arrayParce(nowAnswer).get(0).equals("Информация")) {
                infoText = nowAnswer;
            }

            if (nowAnswer.
                    equals("До скорой встречи!  \n  До скорой встречи!  \n  Моя любовь к тебе навечно")) {
                System.exit(0);
            }

        } catch (IOException e) {

            key.cancel();
            socketChannel.close();
            return;

        } catch (ClassNotFoundException e) {
            // ignore
        }

        if (numRead == -1) {

            key.channel().close();
            key.cancel();

        }

        key.interestOps(OP_READ);

    }

    public void selection(Selector selector, boolean isScript) throws IOException, CancelledKeyException {

        while (true) {

            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> i = selectedKeys.iterator();

            //System.out.println(selectedKeys);

            while (i.hasNext()) {

                int p = 0;

                //System.out.println(1);

                SelectionKey key = i.next();

                //System.out.println(key.isWritable());

                if (key.isConnectable()) {

                    //System.out.println(1);

                    this.finishConnection(key, p);
                    p++;

                }

                if (key.isReadable()) {

                    //System.out.println(2);

                    //ByteBuffer readBuffer = ByteBuffer.allocate(5000);

                    read(key, readBuffer);

                    if (isScript) {
                        return;
                    }

                    //readSocket.register(selector, OP_CONNECT);

                }

                if (key.isWritable()) {
                    //System.out.println(3);
                    //key.interestOps(OP_WRITE);
                }

                try {
                    i.remove();
                } catch (ConcurrentModificationException e) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignore) {

                    }
                    return;
                }

            }

            if (!isScript) {
                break;
            }

        }

    }

}
