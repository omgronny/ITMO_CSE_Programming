package collection;

import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.ByteBuffer.allocate;
import static java.nio.channels.SelectionKey.*;

/**
 * class that implements work with the console and script files
 */


public class Commander implements Invoker {

    //Vector<StudyGroup> vector = new Vector<>();
    //private String filename = System.getenv("INPUT_PATH");

    //private File file = new File(filename);
    private static int counter;
    private static ArrayDeque<byte[]> commandQueue = new ArrayDeque<>();

    private static ByteBuffer readBuffer = allocate(5000);
    private static ByteBuffer writeBuffer = ByteBuffer.allocate(5000);

    private static String login;
    private static String password;


    /**
     * The main method for parse the lines from console or script
     * @param scriptFile file for run the script
     * @param in Scanner that read the lines from console
     * @return your Vector
     */

    public void consoleInput(File scriptFile, Scanner in, long scriptParameter, int port, Selector selector,
                             SocketAddress a, SocketChannel s, SocketChannel readSocket)
                                                                throws IOException, CancelledKeyException {

//        SocketChannel channel = SocketChannel.open();
//
//        channel.configureBlocking(false);
//        channel.connect(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), port));

//        try {
//
//            s = SocketChannel.open(a);          // channel for write
//            s.configureBlocking(false);
//            int ops = s.validOps();
//            s.register(selector, ops, null);
//            //finishConnection(s.keyFor(selector), 0);
//
//            readSocket = SocketChannel.open(a);
//            readSocket.configureBlocking(false);    // channel for read
//            int ops1 = readSocket.validOps();
//            readSocket.register(selector, ops1, null);
//            //finishConnection(readSocket.keyFor(selector), 1);
//
//            s.register(selector, OP_WRITE);
//            readSocket.register(selector, OP_READ);
//
//        } catch (ConnectException e) {
//            System.out.println("Кажется, сервер наелся и спит, извините :(");
//            System.exit(0);
//        }


        Runnable runnable = () -> {
            while (true) {
                try {
                    selection(selector, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread thread = new Thread(runnable);
        thread.start();

        ManCommand manCommand = new ManCommand();
        byte[] testBytes = manCommand.toBytes();

        if (scriptFile == null) {

            System.out.println("Для работы с коллекцией нужно авторизоваться. Введите логин и пароль");

            String inputLogin = in.nextLine();
            String inputPassword = in.nextLine();

            Register register = new Register(inputLogin, inputPassword);
            login = inputLogin;
            password = inputPassword;

            testBytes = register.toBytes();

        }

        writeBuffer.clear();

        s.configureBlocking(false);
        writeBuffer.put(testBytes);
        writeBuffer.flip();
        s.write(writeBuffer);

        readBuffer.clear();

        int numRead = -1;

        while (true) {

            try {

                readBuffer = allocate(5000);

                numRead = readSocket.read(readBuffer);

                //System.out.println(Command.fromBytesToString(readBuffer.array()));
                if (Command.fromBytesToString(readBuffer.array()).equals("До скорой встречи!")) {
                    System.exit(0);
                }

                if (Command.fromBytesToString(readBuffer.array()).equals("Неверный пароль")) {

                    try {

                        consoleInput(scriptFile, in, scriptParameter, port, selector, a, s, readSocket);
                        return;

                    } catch (CancelledKeyException ignore) {

                    }

                }

                break;

            } catch (IOException | ClassNotFoundException | ClassCastException e) {

            }

        }

        readBuffer.clear();
        writeBuffer.clear();

        String filename;

        if (scriptFile != null) {

            filename = scriptFile.toString();

            //file = scriptFile;

            counter++;

        } else {
            counter = 0;
        }


        if (scriptFile == null) {
            System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                    "через запятую без пробелов)");
        }

        Parce parce = new Parce();

        boolean isNotACommand = false;
        /*

        new Thread(() -> {
            try {
                selection(selector);
            } catch (IOException e) {

            }
        }).start();

         */

        while (in.hasNextLine()) {

            if (scriptFile == null) {
                //selection(selector, false);
            }

            //ByteBuffer byteBuffer = ByteBuffer.allocate(5000);

            byte[] b = new byte[5000];

            String str = in.nextLine();
            ArrayList<String> strArray = tabSpliter(str, 1);

            if (scriptFile == null) {
                //selection(selector, false);
            }


            isNotACommand = true;

            if (strArray.get(0).equals("add")) {

                AddUpdateCommand serverAddCommand = null;

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));


                    if (scriptFile == null) {

                        //vector =
                        AddUpdateCommand addCommand = new AddUpdateCommand(null, 1);
                        serverAddCommand = addCommand.addUpdateCommand(arrayList, in, 1, login);

                    } else {

                        AddUpdateCommand addScriptCommand = new AddUpdateCommand(null, 5);
                        serverAddCommand = addScriptCommand.addUpdateCommand(arrayList, in, 5, login);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Некорректный ввод, у команды add должны быть аргументы" +
                            " имя, координтаты Х,У и количество студентов. попробуйте еще раз");
                    continue;
                }



                if (serverAddCommand != null) {

                    b = serverAddCommand.toBytes();

                } else {
                    continue;
                }






                isNotACommand = false;

            }

            if (strArray.get(0).equals("help")) {

                HelpCommand helpCommand = new HelpCommand();

                b = helpCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("info")) {

                InfoCommand infoCommand = new InfoCommand(System.getenv("INPUT_PATH"));
                b = infoCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("show")) {

                ShowCommand showCommand = new ShowCommand();

                b = showCommand.toBytes();

                isNotACommand = false;



            }

            if (strArray.get(0).equals("update")) {

                AddUpdateCommand serverCommand = null;

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    System.out.println(arrayList);

                    if (scriptFile == null) {

                        AddUpdateCommand updateCommand = new AddUpdateCommand(null, 2);
                        serverCommand = updateCommand.addUpdateCommand(arrayList, in, 2, login);

                    } else {

                        AddUpdateCommand updateScriptCommand = new AddUpdateCommand(null, 6);
                        serverCommand = updateScriptCommand.addUpdateCommand(arrayList, in, 6, login);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Некорректный ввод команды. update должна принимать на вход id одного из объектов" +
                            ". Их можно посмотреть с помощью команды show");
                    continue;
                }



                if (serverCommand != null) {

                    b = serverCommand.toBytes();

                } else {
                    continue;
                }





                isNotACommand = false;

            }

            if (strArray.get(0).equals("remove_by_id")) {

                try {

                    int id = Integer.parseInt(strArray.get(1));

                    RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(id, login);
                    b = removeByIdCommand.toBytes();

                } catch (NumberFormatException e) {

                    System.out.println("Некорректное значение id");
                    continue;

                } catch (IndexOutOfBoundsException ee) {
                    System.out.println("Некорректное значение id");
                    continue;
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("clear")) {

                ClearCommand clearCommand = new ClearCommand(login);
                b = clearCommand.toBytes();

                isNotACommand = false;

            }


            if (strArray.get(0).equals("execute_script")) {

                if (scriptFile != null && counter == 1) {

                    Scanner scriptScan = new Scanner(System.in);

                    System.out.println("Кажется, вы хотите запустить больше одного скрипта, пожалуйста, введите желаемую " +
                            "глубину");

                    try {

                        scriptParameter = scriptScan.nextLong();

                    } catch (InputMismatchException e) {

                        System.out.println("Параметр должен быть числом");

                    }
                }



                if (scriptParameter == 0) {
                    break;
                }


                try {

                    ArrayList<String> scriptArray = Parce.arrayParce(strArray.get(1));

                    for (String scriptFileName : scriptArray) {
                        //vector =
                        ExecuteScriptCommand.executeScript(scriptFileName, scriptParameter, port,
                                selector, a, s, readSocket);

                        //SocketAddress a = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), port);
                        //SocketChannel s = null;

                        //s.configureBlocking(false);

                        writeBuffer.clear();



                    }




                } catch (IndexOutOfBoundsException e) {

                    System.out.println("Кажется, вы не ввели имя файла. Попробуйте еще раз");

                } catch (StackOverflowError e) {

                    System.out.println("Кажется вы указали слишком большой параметр. Так лучше не делать, " +
                            "но я попробую обработать ваш скрипт!");

                }

                isNotACommand = false;

                continue;



            }


            if (strArray.get(0).equals("exit")) {

                //SaveCommand saveCommand = new SaveCommand(null, null);

                //b = saveCommand.toBytes();

                System.out.println("До скорой встречи!");
                System.exit(0);

                isNotACommand = false;

            }

            if (strArray.get(0).equals("save")) {

                SaveCommand saveCommand = new SaveCommand(login);

                b = saveCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("insert_at")) {

                AddUpdateCommand serverCommand = null;

                try {


                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    if (scriptFile == null) {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 3);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 3, login);

                    } else {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 7);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 7, login);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Кажется, вы не ввели индекс и/или объект. Попробуйте еще раз");
                    continue;
                }

                if (serverCommand != null) {

                    b = serverCommand.toBytes();

                } else {
                    continue;
                }

                isNotACommand = false;

            }


            if (strArray.get(0).equals("add_if_max")) {

                AddUpdateCommand serverCommand = null;

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    if (scriptFile == null) {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 4);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 4, login);

                    } else {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 8);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 8, login);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Некорректный ввод, у команды add_if_max должны быть аргументы" +
                            " имя, координтаты Х,У и количество студентов. попробуйте еще раз");
                    continue;
                }

                if (serverCommand != null) {

                    b = serverCommand.toBytes();

                } else {
                    continue;
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("reorder")) {

                ReorderCommand reorderCommand = new ReorderCommand(scriptFile != null);

                b = reorderCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("group_counting_by_name")) {

                CountingByNameCommand countingByNameCommand = new CountingByNameCommand();
                b = countingByNameCommand.toBytes();

                isNotACommand = false;


            }

            if (strArray.get(0).equals("filter_by_form_of_education")) {

                try {

                    String educationForm = strArray.get(1);

                    //vector =
                    FilterByEducationCommand filterByEducationCommand = new FilterByEducationCommand(educationForm);
                    b = filterByEducationCommand.toBytes();

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Кажется, вы не ввели тип образования. Доступные варианты: DISTANCE_EDUCATION," +
                            "FULL_TIME_EDUCATION, EVENING_CLASSES. Попробуйте еще раз");
                    continue;
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("print_field_descending_students_count")) {

                PrintStudentsCountCommand printStudentsCountCommand = new PrintStudentsCountCommand();

                b = printStudentsCountCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("long")) {

                LongCommand longCommand = new LongCommand();

                b = longCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("clear_my_elements")) {

                ClearByUser clearByUser = new ClearByUser(login);

                b = clearByUser.toBytes();

                isNotACommand = false;

            }


            if (isNotACommand) {

                if (scriptFile == null) {
                    System.out.println("Упс, кажется, такой команды нет. Посмотреть мписок команд можно, введя help");
                }

            } else {

                if (scriptFile == null) {
                    //selection(selector, false);
                }

                System.out.println("Команда отправлена");

                writeBuffer.clear();

                s.configureBlocking(false);
                writeBuffer.put(b);
                writeBuffer.flip();
                s.write(writeBuffer);

                if (scriptFile == null) {

                    //s.register(selector, OP_CONNECT);

                    /*

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }

                     */

                    for (int j = 0; j < 3; j++) {

                        //selection(selector, false);

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

                                //read(key, readBuffer);

                                //readSocket.register(selector, OP_CONNECT);

                            }

                            if (key.isWritable()) {
                                //System.out.println(3);
                                //key.interestOps(OP_WRITE);
                            }

                            i.remove();


                        }



                        //System.out.println("----");

//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//
//                        }


                    }


                    /*

                    while (true) {

                        s.read(byteBuffer);

                        try {

                            System.out.println(Command.fromBytesToString(byteBuffer.array()));
                            if (Command.fromBytesToString(byteBuffer.array()).equals("До скорой встречи!")) {
                                System.exit(0);
                            }

                            break;

                        } catch (ClassNotFoundException e) {

                            System.out.println("Что-то пошло не так");

                        } catch (ClassCastException e) {
                            // ignore
                        }

                    }

                     */



                    writeBuffer.clear();




                } else {

                    read(readSocket.keyFor(selector), readBuffer);

                }



            }

            /*

            if (scriptFile == null) {
                System.out.println("Пожалуйста, введите команду. (При добавлении элемента перечисляйте поля " +
                        "через запятую без пробелов)");
            }

             */



        }
        //return vector;




    }

    /**
     * method for separated the string
     * @param str String that you want to split
     * @param count Number of separations
     * @return split ArrayList
     */

    public static ArrayList<String> tabSpliter(String str, int count) {

        ArrayList<String> strArray = new ArrayList<>();

        String helpStr = "";
        int b = 0;
        for (int i = 0; i < str.length(); i++) {

            if (String.valueOf(str.charAt(i)).equals(" ") && b < count) {
                strArray.add(helpStr);
                helpStr = "";
                b++;
            } else {
                helpStr += String.valueOf(str.charAt(i));
            }

        }
        strArray.add(helpStr);

        return strArray;
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

            System.out.println(Command.fromBytesToString(readBuffer.array()));
            if (Command.fromBytesToString(readBuffer.array()).
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

    private void selection(Selector selector, boolean isScript) throws IOException, CancelledKeyException {

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
