package commands;

import collection.*;
import exceptions.ValueException;
import parse.Parce;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * class that realization the add,update,insert at and add if max commands
 */

public class AddUpdateCommand extends Command {

    /**
     * class that realization the add,update,insert at and add if max commands
     * @param line ArrayList with first parameters of element (name,coordinates and count of students)
     * @param in Scanner for input reading
     * @param vector main collection
     * @param isAdd int variable that specifies a command
     * @return main collection
     */

    public static Vector<StudyGroup> addUpdateCommand(ArrayList<String> line, Scanner in, Vector<StudyGroup> vector,
                                               int isAdd) {

        StudyGroup studyGroup = new StudyGroup();

        int index = 0;
        long maxStudents = 0;
        boolean isCorrect = false;

        if (isAdd == 1 || isAdd == 5) {

            Double d = new Double(Math.random() * 10000);
            studyGroup.setId(studyGroup.hashCode() + d.intValue());

            studyGroup.setCreationDate(LocalDateTime.now());


        } else if (isAdd == 2 || isAdd == 6) {

            Commander commander = new Commander();
            ArrayList<String> idName = commander.tabSpliter(line.get(0), 1);

            line.set(0, idName.get(1));

            int inputId;

            try {
                inputId = Integer.parseInt(idName.get(0));
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение id");
                return vector;
            }

            boolean isId = false;

            for (int i = 0; i < vector.size(); i++) {
                StudyGroup s = vector.get(i);
                if (inputId == s.getId()) {
                    studyGroup = s;
                    index = i;
                    isId = true;
                }
            }

            if (!isId) {
                System.out.println("Элемента с таким id не нашлось ");
                return vector;
            }


        } else if (isAdd == 3) {

            Commander commander = new Commander();
            ArrayList<String> indName = commander.tabSpliter(line.get(0), 1);

            Double d = new Double(Math.random() * 10000);
            studyGroup.setId(studyGroup.hashCode() + d.intValue());

            studyGroup.setCreationDate(LocalDateTime.now());


            line.set(0, indName.get(1));

            try {
                index = Integer.parseInt(indName.get(0));
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение индекса");
                return vector;
            }

        } else if (isAdd == 4) {

            Double d = new Double(Math.random() * 10000);
            studyGroup.setId(studyGroup.hashCode() + d.intValue());

            studyGroup.setCreationDate(LocalDateTime.now());

            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i).getStudentsCount() > maxStudents) {
                    maxStudents = vector.get(i).getStudentsCount();
                }
            }

        }

        // add читается с именем

        if (line.size() != 4) {
            System.out.println("Ошибка ввода. Количество полей в строке " + "не соответствует нужному. " +
                    "Возможно, Вы использовали специальные символы в именах или забыли ввести какие-то поля.");
            return vector;
        }


        // тут уже проверять каждый
        if (line.get(0).length() != 0) {
            studyGroup.setName(line.get(0));
        } else {
            System.out.println("Ошибка ввода. Имя не может быть пустым");
            return vector;
        }


        try {
            Coordinates coordinates = new Coordinates(Double.parseDouble(line.get(1)), Integer.parseInt(line.get(2)));
            if (Double.parseDouble(line.get(1)) > 866) {
                throw new ValueException();
            }
            studyGroup.setCoordinates(coordinates);
        } catch (NumberFormatException | ValueException e) {
            System.out.println("Ошибка ввода. Проверьте, что передаваемые координаты в строке " +
                    " удовлетворяют требованиям (число Х - дробное и не больше 866, а У - целочисленное) и их " +
                    "ввод не пропущен");
            return vector;
        }


        try {
            studyGroup.setStudentsCount(Long.parseLong(line.get(3)));
            if (Long.parseLong(line.get(3)) <= 0) {
                throw new ValueException();
            }
        } catch (NumberFormatException | ValueException ee) {
            System.out.println("Ошибка ввода. Проверьте, что передаваемое поле studentsCount в строке " +
                    " больше нуля и его " + "ввод не пропущен");
            return vector;
        }

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите formOfEducation. Возможные варианты: DISTANCE_EDUCATION," +
                        " FULL_TIME_EDUCATION, EVENING_CLASSES");
            }

            if (!in.hasNext()) {
                break;
            }

            //line.get(x) больше не валиден

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return vector;
                }

                studyGroup.setFormOfEducation(FormOfEducation.valueOf(nextLine));
                isCorrect = true;
                break;

            } catch (IllegalArgumentException | NoSuchElementException e) {
                isCorrect = false;
                System.out.println("Ошибка ввода. Некоррктное значение поля formOfEducation в строке ");
                //return vector;
            }

        }


        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите Semester. Возможные варианты: SECOND," + " FOURTH," + " SIXTH," +
                        " SEVENTH," + " EIGHTH");
            }

            if (!in.hasNext()) {
                break;
            }

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return vector;
                }

                studyGroup.setSemesterEnum(Semester.valueOf(nextLine));
                isCorrect = true;

                break;
            } catch (IllegalArgumentException | NoSuchElementException e) {

                System.out.println("Ошибка ввода. Некоррктное значение поля Semester в строке ");
                isCorrect = false;
                //return vector;
            }

        }

        Person person = new Person();
        Parce parce = new Parce();

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите имя и рост админа (имя не может быть пустым, " +
                        "рост должен быть больше нуля");
            }

            if (!in.hasNext()) {
                break;
            }

            String nextLine = in.nextLine();

            if (nextLine.equals("exit")) {
                return vector;
            }

            ArrayList<String> nameHeightArray = parce.arrayParce(nextLine);

            if (nameHeightArray.get(0).length() != 0) {
                person.setName(nameHeightArray.get(0));
            } else {
                System.out.println("Ошибка ввода. Имя не может быть пустым");
                //return vector;
                continue;
            }


            try {

                person.setHeight(Long.parseLong(nameHeightArray.get(1)));

                if (Long.parseLong(nameHeightArray.get(1)) <= 0) {
                    throw new ValueException();
                }
                isCorrect = true;

                break;

            } catch (NumberFormatException | ValueException | IndexOutOfBoundsException | NoSuchElementException e) {
                System.out.println("Ошибка ввода. Проверьте, что передаваемое поле height в строке " +
                        " больше нуля и его " + "ввод не пропущен");
                isCorrect = false;
                //return vector;
                continue;
            }

        }

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите цвет глаз админа. Возможные варианты: YELLOW, ORANGE, WHITE, BROWN");
            }

            if (!in.hasNext()) {
                break;
            }

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return vector;
                }

                person.setEyeColor(Color.valueOf(nextLine));
                isCorrect = true;
                break;
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("Ошибка ввода. Некоррктное значение поля Color в строке ");
                isCorrect = false;
                //return vector;
            }
        }

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите национальность админа. Возможные варианты: USA," + " FRANCE," +
                        " ITALY," + " SOUTH_KOREA");
            }

            if (!in.hasNext()) {
                break;
            }

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return vector;
                }

                person.setNationality(Country.valueOf(nextLine));
                isCorrect = true;
                break;
            } catch (IllegalArgumentException  | NoSuchElementException e) {
                System.out.println("Ошибка ввода. Некоррктное значение поля Country в строке ");
                isCorrect = false;
                //return vector;
            }

        }

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите локацию админа. Число Х - дробное,  У и Z - целочисленные, и непустое " +
                        "поле name");
            }

            if (!in.hasNext()) {
                break;
            }

            String nextLine = in.nextLine();

            if (nextLine.equals("exit")) {
                return vector;
            }

            ArrayList<String> locationArray = parce.arrayParce(nextLine);

            try {

                Location location = new Location(Double.parseDouble(locationArray.get(0)),
                        Integer.parseInt(locationArray.get(1)), Integer.parseInt(locationArray.get(2)),
                        locationArray.get(3));

                if (locationArray.get(3).length() == 0) {
                    throw new IllegalArgumentException();
                }

                person.setLocation(location);

                isCorrect = true;

                break;

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Ошибка ввода. Проверьте, что передаваемые поля локации в строке " +
                        " удовлетворяют требованиям (число Х - дробное, а У и Z - целочисленные)" +
                        " и их " + "ввод не пропущен");

                isCorrect = false;
                //return vector;
            } catch (IllegalArgumentException | NoSuchElementException ee) {
                System.out.println("Ошибка ввода. Имя не может быть пустым");
                isCorrect = false;
                //return vector;
            }

        }

        studyGroup.setGroupAdmin(person);


        if (isAdd == 1 || isAdd == 5) {

            if (isCorrect) {
                vector.add(studyGroup);
            }
            if (isAdd != 5) {
                System.out.println("Элемент добавлен. Можете ввести следующую команду");
            }

        } else if (isAdd == 2 || isAdd == 6) {

            if (isCorrect) {

                vector.set(index, studyGroup);

            }

            if (isAdd != 6) {
                System.out.println("Элемент изменен. Можете ввести следующую команду");
            }

        } else if (isAdd == 3 || isAdd == 7) {

            if (isCorrect) {
                vector.add(index, studyGroup);
            }

            if (isAdd != 7) {
                System.out.println("Элемент добавлен. Можете ввести следующую команду");
            }

        } else if (isAdd == 4 || isAdd == 8) {

            if (maxStudents <= Long.parseLong(line.get(3))) {

                if (isCorrect) {
                    vector.add(index, studyGroup);
                }

                if (isAdd != 8) {
                    System.out.println("Элемент добавлен. Можете ввести следующую команду");
                }
            } else if (isAdd != 8) {

                System.out.println("Элемент не максимальный");

            }

        }

        Collections.sort(vector);

        try {

            File bufferFile = new File("Instr.txt");

            bufferFile.createNewFile();

            SaveCommand.saveFile(vector, "Instr.txt");

        } catch (FileNotFoundException e) {
            // если файла нет - нужно создать

        } catch (IOException e) {

        }


        return vector;





    }
}
