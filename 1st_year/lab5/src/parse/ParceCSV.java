package parse;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import collection.*;
import collection.Color;
import exceptions.*;

/**
 * Class that realization parsing from CSV-format or parsing to CSV-format
 */


public class ParceCSV extends Parce {

    /**
     * method for parsing file to ArrayList of Strings. Separation by lines
     * @param file file foe parsing
     * @return ArrayList of file-lines
     */

    public ArrayList<String> fileToArray(File file) {

        //File file = new File("Data.csv");
        ArrayList<String> lines = new ArrayList<String>();

        String filename = file.toString();

        if (filename == null) {
            System.out.println("Значение заданной переменной окружения не задано или такой переменной не существует." +
                    " Пожалуйста, задайте значение переменной и запустите программу снова");
            System.exit(0);
        }

        Path path = Paths.get(filename);

        try {

            if (!(Files.isReadable(path))) {
                throw new ValueException();
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lines.add(line);
            }

            sc.close();

        } catch (FileNotFoundException e) {

            System.out.println("Файл не найден");
            System.exit(0);

        } catch (ValueException e) {
            System.out.println("Ошибка прав, нужны права на чтение");
            System.exit(0);
        }

        return lines;
    }

    /**
     * method that parsing the csv-file to main collection
     * @param file csv-file
     * @param vector collection for parsing
     * @return main collection
     */

    public Vector<StudyGroup> addCSVToVector(File file, Vector vector) {

        Parce parce = new Parce();

        ArrayList<String> lines = fileToArray(file);
        ArrayList<String> line = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            StudyGroup studyGroup = new StudyGroup();

            line = parce.arrayParce(lines.get(i));

            try {

                studyGroup.setCreationDate(LocalDateTime.parse(line.get(4)));

            } catch (IndexOutOfBoundsException e) {
                //vector.add(null);
                continue;
            } catch (DateTimeParseException e) {
                System.out.println("Проверьте данные в строке " + i + " Возможно, вы неверно указали время создания " +
                        "объекта или количество полей не соответствует нужному");
            }



            studyGroup.setId(Integer.parseInt(line.get(0)));


            // сразу проверить количество строк в листе, если оно сходу больше, то кинуть сообщение
            if (line.size() != 16) {
                System.out.println("Ошибка ввода в файле. Количество полей в строке " + i + " не соответствует нужному. Возможно, " +
                        "Вы использовали специальные символы в именах или забыли ввести какие-то поля.");
                continue;
            }


            // тут уже проверять каждый
            if (line.get(1).length() != 0) {
                studyGroup.setName(line.get(1));
            } else {
                System.out.println("Ошибка ввода в файле. Имя не может быть пустым. Строка " + i);
                continue;
            }


            try {
                Coordinates coordinates = new Coordinates(Double.parseDouble(line.get(2)), Integer.parseInt(line.get(3)));
                if (Double.parseDouble(line.get(2)) > 866) {
                    throw new ValueException();
                }
                studyGroup.setCoordinates(coordinates);
            } catch (NumberFormatException | ValueException e) {
                System.out.println("Ошибка ввода в файле. Проверьте, что передаваемые координаты в строке " + i +
                        " удовлетворяют требованиям (число Х - дробное и не больше 866, а У - целочисленное) и их " +
                        "ввод не пропущен");
                continue;
            }


            try {
                studyGroup.setStudentsCount(Long.parseLong(line.get(5)));
                if (Long.parseLong(line.get(5)) <= 0) {
                    throw new ValueException();
                }
            } catch (NumberFormatException | ValueException e) {
                System.out.println("Ошибка ввода в файле. Проверьте, что передаваемое поле studentsCount в строке " + i +
                        " больше нуля и его " + "ввод не пропущен");
                continue;
            }


            try {
                studyGroup.setFormOfEducation(FormOfEducation.valueOf(line.get(6)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля formOfEducation в строке " + i);
                continue;
            }


            try {
                studyGroup.setSemesterEnum(Semester.valueOf(line.get(7)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля Semester в строке " + i);
                continue;
            }

            Person person = new Person();

            if (line.get(8).length() != 0) {
                person.setName(line.get(8));
            } else {
                System.out.println("Ошибка ввода в файле. Имя не может быть пустым. Строка " + i);
                continue;
            }


            try {
                person.setHeight(Long.parseLong(line.get(9)));
                if (Long.parseLong(line.get(9)) <= 0) {
                    throw new ValueException();
                }
            } catch (NumberFormatException | ValueException e) {
                System.out.println("Ошибка ввода в файле. Проверьте, что передаваемое поле height в строке " + i +
                        " больше нуля и его " + "ввод не пропущен");
                continue;
            }


            try {
                person.setEyeColor(Color.valueOf(line.get(10)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля Color в строке " + i);
                continue;
            }


            try {
                person.setNationality(Country.valueOf(line.get(11)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля Country в строке " + i);
                continue;
            }


            try {
                Location location = new Location(Double.parseDouble(line.get(12)), Integer.parseInt(line.get(13)),
                        Integer.parseInt(line.get(14)), line.get(15));
                if (line.get(15).length() == 0) {
                    throw new IllegalArgumentException();
                }
                person.setLocation(location);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода в файле. Проверьте, что передаваемые поля локации в строке " + i +
                        " удовлетворяют требованиям (число Х - дробное, а У и Z - целочисленные)" +
                        " и их " + "ввод не пропущен");
                continue;
            } catch (IllegalArgumentException ee) {
                System.out.println("Ошибка ввода в файле. Имя не может быть пустым. Строка " + i);
                continue;
            }

            studyGroup.setGroupAdmin(person);

            vector.add(studyGroup);



        }

        return vector;

    }

    /**
     * method that parsing ArrayList to CSV-format-string
     * @param arrayList ArrayList of strings
     * @return CSV-format-string
     */

    public static String parceArrayToCSVFormat (ArrayList<String> arrayList) {

        String finalString = "";

        for (int i = 0; i < arrayList.size(); i++) {

            finalString += arrayList.get(i);

            if (i != arrayList.size() - 1) {
                finalString += ",";
            } else {
                finalString += "\n";
            }

        }

        return finalString;

    }

    /**
     * method that writing from Array list of strings to CSV-file
     * @param file csv-format-file
     * @param list ArrayList of Strings for write to file
     */

    public static void writerToCSV (File file, ArrayList<String> list) {

        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (String s : list) {
                printWriter.append(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла не существует");
        }





    }

}
