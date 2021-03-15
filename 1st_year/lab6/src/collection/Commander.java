package collection;

import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

/**
 * class that implements work with the console and script files
 */


public class Commander implements Invoker {

    //Vector<StudyGroup> vector = new Vector<>();
    private String filename = System.getenv("INPUT_PATH");
    private File file = new File(filename);
    private static int counter;

    /**
     * The main method for parse the lines from console or script
     * @param scriptFile file for run the script
     * @param in Scanner that read the lines from console
     * @return your Vector
     */

    public void consoleInput(File scriptFile, Scanner in, long scriptParameter, int port) throws IOException {


        if (scriptFile != null) {

            filename = scriptFile.toString();

            file = scriptFile;

            counter++;

        } else {
            counter = 0;
        }

        if (filename == null) {
            System.out.println("Файл не найден");
            System.exit(0);
        }

        Path path = Paths.get(filename);
        if (!Files.isReadable(path)) {
            System.out.println("Ошибка прав");
            //return vector;
        }


        if (scriptFile == null) {
            System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                    "через запятую без пробелов)");
        }

        Parce parce = new Parce();

        boolean isNotACommand = false;

        while (in.hasNextLine()) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(5000);

            byte[] b = new byte[5000];

            String str = in.nextLine();
            ArrayList<String> strArray = tabSpliter(str, 1);


            isNotACommand = true;

            if (strArray.get(0).equals("add")) {

                AddUpdateCommand serverAddCommand = null;

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));


                    if (scriptFile == null) {

                        //vector =
                        AddUpdateCommand addCommand = new AddUpdateCommand(null, 1);
                        serverAddCommand = addCommand.addUpdateCommand(arrayList, in, 1);

                    } else {

                        AddUpdateCommand addScriptCommand = new AddUpdateCommand(null, 5);
                        serverAddCommand = addScriptCommand.addUpdateCommand(arrayList, in, 5);

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

                InfoCommand infoCommand = new InfoCommand(filename);
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
                        serverCommand = updateCommand.addUpdateCommand(arrayList, in, 2);

                    } else {

                        AddUpdateCommand updateScriptCommand = new AddUpdateCommand(null, 6);
                        serverCommand = updateScriptCommand.addUpdateCommand(arrayList, in, 6);

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

                    RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(id);
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

                ClearCommand clearCommand = new ClearCommand();
                b = clearCommand.toBytes();

                isNotACommand = false;

            }
            /*

            if (strArray.get(0).equals("save")) {
                // сохранение в файл через PrintWriter

                String savedFileName = System.getenv("INPUT_PATH");


                SaveCommand saveCommand = new SaveCommand(vector, savedFileName);

                try {
                    saveCommand.saveFile(vector, savedFileName);
                    System.out.println("Файл сохранен");
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден");
                }



                if (scriptFile == null) {

                    System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                            "через запятую без пробелов)");

                }

                File file = new File("Instr.txt");

                file.delete();

                isNotACommand = false;

            }

             */


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
                        ExecuteScriptCommand.executeScript(scriptFileName, scriptParameter, port);

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

                SaveCommand saveCommand = new SaveCommand(null, null);

                /*

                if (scriptFile == null) {

                    File file = new File("Instr.txt");

                    if (file.exists()) {

                        System.out.println("Вы уверены, что хотите завершить программу? Все не сохраненные данные будут утеряны. " +
                                "(Если вы хотите выйти, напишите 'Да/Yes')");

                        String isYes = in.nextLine();

                        if (isYes.toLowerCase().equals("да") || isYes.toLowerCase().equals("lf")
                                || isYes.toLowerCase().equals("yes")) {


                            System.exit(0);

                        } else {

                            System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                                    "через запятую без пробелов");
                        }
                    } else {


                        System.exit(0);
                    }

                }

                 */

                b = saveCommand.toBytes();

                isNotACommand = false;

            }

            if (strArray.get(0).equals("insert_at")) {

                AddUpdateCommand serverCommand = null;

                try {


                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    if (scriptFile == null) {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 3);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 3);

                    } else {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 7);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 7);

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
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 4);

                    } else {

                        AddUpdateCommand addUpdateCommand = new AddUpdateCommand(null, 8);
                        serverCommand = addUpdateCommand.addUpdateCommand(arrayList, in, 8);

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


            if (isNotACommand) {

                if (scriptFile == null) {
                    System.out.println("Упс, кажется, такой команды нет. Посмотреть мписок команд можно, введя help");
                }

            } else {

                SocketAddress a = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), port);
                SocketChannel s = null;


                try {
                    s = SocketChannel.open(a);
                } catch (ConnectException e) {
                    System.out.println("Кажется, сервер наелся и спит, извините :(");
                    System.exit(0);
                }

                s.configureBlocking(false);

                byteBuffer.put(b);

                byteBuffer.flip();
                s.write(byteBuffer);
                byteBuffer.clear();

                byteBuffer.clear();

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

            }

            byteBuffer.clear();

        }

        if (scriptFile == null) {
            System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                    "через запятую без пробелов)");
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

}
