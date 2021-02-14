package collection;

import commands.*;
import parse.Parce;
import parse.ParceCSV;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Collection {

    Vector<StudyGroup> vector = new Vector<>();
    String filename = System.getenv("INPUT_PATH");
    File file = new File(filename);

    public Vector<StudyGroup> consoleInput(Vector<StudyGroup> vector, File scriptFile, Scanner in) {

        if (scriptFile != null) {
            filename = scriptFile.toString();
            file = scriptFile;
        }

        if (filename == null) {
            System.out.println("Файл не найдена");
            System.exit(0);
        }
        Path path = Paths.get(filename);
        if (!(Files.isReadable(path) && Files.isExecutable(path) && Files.isWritable(path))) {
            System.out.println("Ошибка прав");
            System.exit(0);
        }


        if (scriptFile == null) {
            System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                    "через запятую без пробелов)");
        }

        Parce parce = new Parce();

        while (in.hasNext()) {

            String str = in.nextLine();
            ArrayList<String> strArray = tabSpliter(str, 1);


            boolean isNotACommand = true;

            if (strArray.get(0).equals("add")) {

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));


                    if (scriptFile == null) {

                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 1);
                    } else {
                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 5);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Некорректный ввод, у команды add должны быть аргументы" +
                            " имя, координтаты Х,У и количество студентов. попробуйте еще раз");
                }
                isNotACommand = false;

            }

            if (strArray.get(0).equals("help")) {

                InfoHelpShowCommand.help();
                isNotACommand = false;

            }

            if (strArray.get(0).equals("info")) {

                InfoHelpShowCommand.info(vector);
                isNotACommand = false;

            }

            if (strArray.get(0).equals("show")) {

                InfoHelpShowCommand.show(vector);
                isNotACommand = false;

            }

            if (strArray.get(0).equals("update")) {

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    if (scriptFile == null) {

                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 2);
                    } else {

                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 6);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Некорректный ввод команды. update должна принимать на вход id одного из объектов" +
                            ". Их можно посмотреть с помощью команды show");
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("remove_by_id")) {

                try {

                    int id = Integer.parseInt(strArray.get(1));
                    vector = RemoveByIdCommand.remove(vector, id);

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

                vector = Clear.clear(vector);
                isNotACommand = false;

            }

            if (strArray.get(0).equals("save")) {
                // сохранение в файл через PrintWriter
                SaveCommand.saveFile(vector, file);

                isNotACommand = false;

            }

            if (strArray.get(0).equals("execute_script")) {

                try {

                    File file = new File(strArray.get(1));

                    String fileName = strArray.get(1);
                    Path pat = Paths.get(fileName);
                    Scanner scanner = null;

                    try {

                        scanner = new Scanner(pat);

                        scanner.useDelimiter(System.getProperty("line.separator"));
                        consoleInput(vector, file, scanner);

                        scanner.close();
                        System.out.println("Скрипт выполнен. Можете вводить команды дальше");

                    } catch (NoSuchFileException e) {
                        System.out.println("Файл не найден");

                    } catch (IOException e) {

                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Кажется, вы не ввели имя файла. Попробуйте еще раз");
                }

                isNotACommand = false;


            }

            if (strArray.get(0).equals("exit")) {

                if (scriptFile == null) {
                    System.out.println("Вы уверены, что хотите завершить программу без сохранения? Все данные будут утеряны. " +
                            "(Если вы хотите выйти, напишите 'Да')");

                    if (in.nextLine().equals("Да")) {
                        break;
                    } else {
                        System.out.println("Пожалуйста, введите команды. (При добавлении элемента перечисляйте поля " +
                                "через запятую без пробелов");
                    }
                } else {
                    System.exit(0);
                }

            }

            if (strArray.get(0).equals("insert_at")) {

                try {


                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    if (scriptFile == null) {

                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 3);
                    } else {
                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 7);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Кажется, вы не ввели индекс и/или объект. Попробуйте еще раз");
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("add_if_max")) {

                try {

                    ArrayList<String> arrayList = parce.arrayParce(strArray.get(1));

                    if (scriptFile == null) {

                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 4);
                    } else {
                        vector = AddUpdateCommand.addUpdateCommand(arrayList, in, vector, 8);

                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Некорректный ввод, у команды add_if_max должны быть аргументы" +
                            " имя, координтаты Х,У и количество студентов. попробуйте еще раз");
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("reorder")) {

                vector = SortedFilterCommand.reorder(vector, scriptFile == null);
                isNotACommand = false;

            }

            if (strArray.get(0).equals("group_counting_by_name")) {

                CountingByNameCommand.counting(vector);
                isNotACommand = false;


            }

            if (strArray.get(0).equals("filter_by_form_of_education")) {

                try {

                    String educationForm = strArray.get(1);

                    vector = SortedFilterCommand.filterByEducation(vector, educationForm);

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Кажется, вы не ввели тип образования. Доступные варианты: DISTANCE_EDUCATION," +
                            "FULL_TIME_EDUCATION, EVENING_CLASSES. Попробуйте еще раз");
                }

                isNotACommand = false;

            }

            if (strArray.get(0).equals("print_field_descending_students_count")) {

                SortedFilterCommand.printStudentsCount(vector);
                isNotACommand = false;

            }


            if (isNotACommand) {

                if (scriptFile == null) {
                    System.out.println("Упс, кажется, такой команды нет. Посмотреть мписок команд можно, введя help");
                }

            }

        }

        return vector;

    }

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
