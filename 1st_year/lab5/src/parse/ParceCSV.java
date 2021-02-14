package parse;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import collection.*;
import collection.Color;
import exceptions.ValueException;

public class ParceCSV {

    public ArrayList<String> fileToArray(File file) {

        //File file = new File("Data.csv");
        ArrayList<String> lines = new ArrayList<String>();

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public ArrayList<String> lineToArray(File file) {

        //File file = new File("Data.csv");
        ArrayList<String> lines = new ArrayList<String>();

        try {

            Scanner sc = new Scanner(file);

            String line = sc.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public Vector<StudyGroup> addCSVToVector(File file, Vector vector) {

        ParceCSV parceCSV = new ParceCSV();
        Parce parce = new Parce();
        ArrayList<String> lines = parceCSV.fileToArray(file);
        ArrayList<String> line = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            StudyGroup studyGroup = new StudyGroup();


            line = parce.arrayParce(lines.get(i));

//            line.get(4);        // 2021-02-14T16:53:19.254626900
//
//            int year = Integer.parseInt(String.valueOf((line.get(4).charAt(0)) + (line.get(4).charAt(1)) +
//                    (line.get(4).charAt(2)) + (line.get(4).charAt(3))));
//
//            int month = Integer.parseInt(String.valueOf((line.get(4).charAt(5)) + (line.get(4).charAt(6))));
//            int day = Integer.parseInt(String.valueOf((line.get(4).charAt(8)) + (line.get(4).charAt(9))));
//            int hour = Integer.parseInt(String.valueOf((line.get(4).charAt(11)) + (line.get(4).charAt(12))));
//            int minute = Integer.parseInt(String.valueOf((line.get(4).charAt(14)) + (line.get(4).charAt(15))));
//            int second = Integer.parseInt(String.valueOf((line.get(4).charAt(17)) + (line.get(4).charAt(18))));
//
//
//
//            LocalDate localDate = new LocalDate(year, month, day);
//            LocalTime localTime = new LocalTime(hour, minute, second, 0);
//            LocalDateTime localDateTime = new LocalDateTime(localDate, localTime);

            studyGroup.setCreationDate(LocalDateTime.parse(line.get(4)));




            studyGroup.setId(Integer.parseInt(line.get(0)));


            // сразу проверить количество строк в листе, если оно сходу больше, то кинуть сообщение
            if (line.size() != 16) {
                System.out.println("Ошибка ввода в файле. Количество полей в строке " + i + " не соответствует нужному. Возможно, " +
                        "Вы использовали специальные символы в именах или забыли ввести какие-то поля.");
                break;
            }


            // тут уже проверять каждый
            if (line.get(1).length() != 0) {
                studyGroup.setName(line.get(1));
            } else {
                System.out.println("Ошибка ввода в файле. Имя не может быть пустым. Строка " + i);
                break;
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
                break;
            }


            try {
                studyGroup.setStudentsCount(Long.parseLong(line.get(5)));
                if (Long.parseLong(line.get(5)) <= 0) {
                    throw new ValueException();
                }
            } catch (NumberFormatException | ValueException e) {
                System.out.println("Ошибка ввода в файле. Проверьте, что передаваемое поле studentsCount в строке " + i +
                        " больше нуля и его " + "ввод не пропущен");
                break;
            }


            try {
                studyGroup.setFormOfEducation(FormOfEducation.valueOf(line.get(6)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля formOfEducation в строке " + i);
                break;
            }


            try {
                studyGroup.setSemesterEnum(Semester.valueOf(line.get(7)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля Semester в строке " + i);
                break;
            }

            Person person = new Person();

            if (line.get(8).length() != 0) {
                person.setName(line.get(8));
            } else {
                System.out.println("Ошибка ввода в файле. Имя не может быть пустым. Строка " + i);
                break;
            }


            try {
                person.setHeight(Long.parseLong(line.get(9)));
                if (Long.parseLong(line.get(9)) <= 0) {
                    throw new ValueException();
                }
            } catch (NumberFormatException | ValueException e) {
                System.out.println("Ошибка ввода в файле. Проверьте, что передаваемое поле height в строке " + i +
                        " больше нуля и его " + "ввод не пропущен");
                break;
            }


            try {
                person.setEyeColor(Color.valueOf(line.get(10)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля Color в строке " + i);
                break;
            }


            try {
                person.setNationality(Country.valueOf(line.get(11)));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода в файле. Некоррктное значение поля Country в строке " + i);
                break;
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
                break;
            } catch (IllegalArgumentException ee) {
                System.out.println("Ошибка ввода в файле. Имя не может быть пустым. Строка " + i);
                break;
            }

            studyGroup.setGroupAdmin(person);

            vector.add(studyGroup);


        }

        return vector;

    }

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
