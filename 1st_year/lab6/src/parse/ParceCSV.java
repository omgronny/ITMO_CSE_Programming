package parse;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
     * method for parsing file to ArrayList of Strings. Separation by line
     * @return ArrayList of file-lines
     */

    public ArrayList<String> fileToArray(ResultSet rs) {

        //File file = new File("Data.csv");
        ArrayList<String> lines = new ArrayList<String>();



//        try {
//            while (rs.next()) {
//                String line = rs.ge
//                lines.add(line);
//            }
//        } catch (SQLException throwables) { }
//
        return lines;

    }

    /**
     * method that parsing the csv-file to main collection
     * @param vector collection for parsing
     * @return main collection
     */

    public Vector<StudyGroup> addCollectionToVector(ResultSet rs, Vector vector) throws SQLException {

        Parce parce = new Parce();

        //ArrayList<String> lines = fileToArray(file);
        //ArrayList<String> lines = ;
        ArrayList<String> line = new ArrayList<>();

        for (int i = 0; rs.next(); i++) {

            StudyGroup studyGroup = new StudyGroup();

            line.add(String.valueOf((rs.getInt("id"))));
            line.add((rs.getString("name")));
            line.add((rs.getString("xcord")));
            line.add((rs.getString("ycord")));
            line.add((rs.getString("createdate")));
            line.add((rs.getString("count")));
            line.add((rs.getString("typeofeducation")));
            line.add((rs.getString("semester")));
            line.add((rs.getString("adminname")));
            line.add((rs.getString("height")));
            line.add((rs.getString("color")));
            line.add((rs.getString("country")));
            line.add((rs.getString("adminxcoord")));
            line.add((rs.getString("adminycoord")));
            line.add((rs.getString("adminzcoord")));
            line.add((rs.getString("locationname")));
            line.add((rs.getString("userlogin")));
            //line = parce.arrayParce(lines.get(i));

            try {

                studyGroup.setCreationDate(LocalDateTime.parse(line.get(4)));

            } catch (IndexOutOfBoundsException e) {
                //vector.add(null);
                continue;
            } catch (DateTimeParseException e) {
                System.out.println("Проверьте данные в строке " + i + " Возможно, вы неверно указали время создания " +
                        "объекта или количество полей не соответствует нужному");
                continue;
            }



            try {

                studyGroup.setId(Integer.parseInt(line.get(0)));

            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода в файле. Строка " + i);
                continue;
            }


            // сразу проверить количество строк в листе, если оно сходу больше, то кинуть сообщение
            if (line.size() != 17) {
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
                if (line.get(6).equals("")) {
                    studyGroup.setFormOfEducation(null);
                } else {
                    System.out.println("Ошибка ввода в файле. Некоррктное значение поля formOfEducation в строке " + i);
                }
                continue;
            }


            try {
                studyGroup.setSemesterEnum(Semester.valueOf(line.get(7)));
            } catch (IllegalArgumentException e) {
                if (line.get(7).equals("")) {
                    studyGroup.setSemesterEnum(null);
                } else {
                    System.out.println("Ошибка ввода в файле. Некоррктное значение поля Semester в строке " + i);
                }
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

            studyGroup.setCreator(line.get(16));

            vector.add(studyGroup);

            line.clear();


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

    public HashMap<String, String> addUsersToVector(ResultSet rs, HashMap<String, String> hashMap) throws SQLException {

        while (rs.next()) {

            hashMap.put(rs.getString("login"), rs.getString("password"));

        }

        return hashMap;
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
