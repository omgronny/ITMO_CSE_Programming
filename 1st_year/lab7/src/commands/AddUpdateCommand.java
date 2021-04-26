package commands;

import collection.*;
import collection.Collection;
import exceptions.ValueException;
import parse.Parce;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * class that realization the add,update,insert at and add if max commands
 */

public class AddUpdateCommand extends Command implements Serializable {

    private final int isAdd;
    private final StudyGroup studyGroup;
    private ArrayList<String> line;


    private int indexId;


    public AddUpdateCommand(StudyGroup studyGroup, int isAdd, ArrayList<String> line) {
        this.studyGroup = studyGroup;
        this.isAdd = isAdd;
        this.line = line;
    }

    public AddUpdateCommand(StudyGroup studyGroup, int isAdd) {     // constructor for add
        this.studyGroup = studyGroup;
        this.isAdd = isAdd;
    }

    public AddUpdateCommand(StudyGroup studyGroup, int isAdd, int indexId) {     // constructor for update and insert at
        this.studyGroup = studyGroup;
        this.isAdd = isAdd;
        this.indexId = indexId;
    }



    @Override
    public String execute() {

        String out = "";

        CopyOnWriteArrayList<StudyGroup> vector = Collection.getVector();

        int index = 0;
        long maxStudents = 0;

        if (this.isAdd == 1 || this.isAdd == 5) {

            int maxId = 0;

            if (vector.size() > 0) {
                for (StudyGroup s : vector) {
                    if (s.getId() > maxId) {
                        maxId = s.getId();
                    }
                }
            }

            Double d = new Double(Math.random() * 10);
            this.studyGroup.setId(maxId + d.intValue());

            this.studyGroup.setCreationDate(LocalDateTime.now());

            this.studyGroup.setCondition(Condition.INSERT);

            vector.add(this.studyGroup);

            Collection.setVector(vector);


        } else if (isAdd == 2 || isAdd == 6) {

            int inputId = this.indexId;

            boolean isId = false;

            StudyGroup updateStudy = null;
            for (int i = 0; i < vector.size(); i++) {
                StudyGroup s = vector.get(i);
                if (inputId == s.getId()) {
                    updateStudy = s;
                    index = i;
                    isId = true;
                }
            }

            if (!updateStudy.getCreator().equals(this.studyGroup.getCreator())) {
                return "Элемент с таким id пренадлежит не вам";
            }

            this.studyGroup.setId(updateStudy.getId());
            this.studyGroup.setCreationDate(updateStudy.getCreationDate());

            this.studyGroup.setCondition(Condition.UPDATE);


            if (!isId) {
                return "Элемента с таким id не нашлось ";
            }

            vector.set(index, this.studyGroup);

            Collection.setVector(vector);


        } else if (isAdd == 3) {

            Double d = new Double(Math.random() * 10000);
            this.studyGroup.setId(this.studyGroup.hashCode() + d.intValue());

            this.studyGroup.setCreationDate(LocalDateTime.now());

            this.studyGroup.setCondition(Condition.INSERT);

            vector.add(this.indexId, this.studyGroup);

            Collection.setVector(vector);



        } else if (isAdd == 4) {

            Double d = new Double(Math.random() * 10000);
            this.studyGroup.setId(this.studyGroup.hashCode() + d.intValue());

            this.studyGroup.setCreationDate(LocalDateTime.now());

            this.studyGroup.setCondition(Condition.INSERT);

            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i).getStudentsCount() > maxStudents) {
                    maxStudents = vector.get(i).getStudentsCount();
                }
            }

            if (this.studyGroup.getStudentsCount() >= maxStudents) {
                vector.add(this.studyGroup);
                Collection.setVector(vector);
                out =  "Элемент добавлен";
            } else {
                return "Элемент был не максимальный";
            }

        }

        Collections.sort(Collection.getVector());

        //Collection.setVector(vector);


        return out; //addUpdateCommand(this.line, this.in, this.vector, this.isAdd);
    }

    /**
     * method that realization the add,update,insert at and add if max commands
     * @param line ArrayList with first parameters of element (name,coordinates and count of students)
     * @param in Scanner for input reading
     * @param isAdd int variable that specifies a command
     * @return main collection
     */


    public AddUpdateCommand addUpdateCommand(ArrayList<String> line, Scanner in,
                                               int isAdd, String login) {

        StudyGroup studyGroup = new StudyGroup();

        int idIndex = 0;

        boolean isCorrect = false;

        if (isAdd == 2 || isAdd == 3 || isAdd == 6 || isAdd == 7) {

            if (line.size() != 5) {
                System.out.println("Ошибка ввода. Количество полей в строке " + "не соответствует нужному. " +
                        "Возможно, Вы использовали специальные символы в именах или забыли ввести какие-то поля.");
                return null;
            }

            try {

                idIndex = Integer.parseInt(line.get(0));

                line.set(0, line.get(1));
                line.set(1, line.get(2));
                line.set(2, line.get(3));
                line.set(3, line.get(4));
                line.remove(4);

            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Проверьте, что вы верно указали все поля");
                return null;
            }



        }


        // add читается с именем

        if (line.size() != 4) {
            System.out.println("Ошибка ввода. Количество полей в строке " + "не соответствует нужному. " +
                    "Возможно, Вы использовали специальные символы в именах или забыли ввести какие-то поля.");
            return null;
        }


        // тут уже проверять каждый
        if (line.get(0).length() != 0) {
            studyGroup.setName(line.get(0));
        } else {
            System.out.println("Ошибка ввода. Имя не может быть пустым");
            return null;
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

            return null;
        }


        try {
            studyGroup.setStudentsCount(Long.parseLong(line.get(3)));
            if (Long.parseLong(line.get(3)) <= 0) {
                throw new ValueException();
            }
        } catch (NumberFormatException | ValueException ee) {

            System.out.println("Ошибка ввода. Проверьте, что передаваемое поле studentsCount в строке " +
                    " больше нуля и его " + "ввод не пропущен");
            return null;
        }

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите formOfEducation. Возможные варианты: DISTANCE_EDUCATION," +
                        " FULL_TIME_EDUCATION, EVENING_CLASSES");

            }

//            if (!in.hasNextLine()) {
//                studyGroup.setFormOfEducation(null);
//                System.out.println("aaa");
//                break;
//            }

//            if (!in.hasNext()) {
//                break;
//            }

            //line.get(x) больше не валиден

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return null;
                }

                if (nextLine.equals("")) {
                    studyGroup.setFormOfEducation(null);
                    break;
                }

                studyGroup.setFormOfEducation(FormOfEducation.valueOf(nextLine));
                isCorrect = true;
                break;

            } catch (IllegalArgumentException e) {
                isCorrect = false;
                System.out.println("Ошибка ввода. Некоррктное значение поля formOfEducation в строке ");
                //return vector;
            } catch (NoSuchElementException e) {
                studyGroup.setFormOfEducation(null);
                break;
            }

        }


        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите Semester. Возможные варианты: SECOND," + " FOURTH," + " SIXTH," +
                        " SEVENTH," + " EIGHTH");
            }

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return null;
                }

                if (nextLine.equals("")) {
                    studyGroup.setSemesterEnum(null);
                    break;
                }

                studyGroup.setSemesterEnum(Semester.valueOf(nextLine));
                isCorrect = true;

                break;
            } catch (IllegalArgumentException e) {

                System.out.println("Ошибка ввода. Некоррктное значение поля Semester в строке ");
                isCorrect = false;
                //return vector;
            } catch (NoSuchElementException e) {
                studyGroup.setSemesterEnum(null);
                break;
            }

        }

        Person person = new Person();
        Parce parce = new Parce();

        while (true) {

            if (isAdd < 5) {

                System.out.println("Пожалйста, введите имя и рост админа (имя не может быть пустым, " +
                        "рост должен быть больше нуля");
            }

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return null;
                }

                ArrayList<String> nameHeightArray = parce.arrayParce(nextLine);

                if (nameHeightArray.get(0).length() != 0) {
                    person.setName(nameHeightArray.get(0));
                } else {
                    System.out.println("Ошибка ввода. Имя не может быть пустым");
                    //return vector;
                    continue;
                }

                person.setHeight(Long.parseLong(nameHeightArray.get(1)));

                if (Long.parseLong(nameHeightArray.get(1)) <= 0) {
                    throw new ValueException();
                }
                isCorrect = true;

                break;

            } catch (NumberFormatException | ValueException | IndexOutOfBoundsException e) {
                System.out.println("Ошибка ввода. Проверьте, что передаваемое поле height в строке " +
                        " больше нуля и его " + "ввод не пропущен");
                isCorrect = false;
                //return vector;
                continue;
            } catch (NoSuchElementException e) {
                return null;
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
                    return null;
                }



                person.setEyeColor(Color.valueOf(nextLine));
                isCorrect = true;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода. Некоррктное значение поля Color в строке ");
                isCorrect = false;
                //return vector;
            } catch (NoSuchElementException e) {
                return null;
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
                    return null;
                }

                person.setNationality(Country.valueOf(nextLine));
                isCorrect = true;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода. Некоррктное значение поля Country в строке ");
                isCorrect = false;
                //return vector;
            } catch (NoSuchElementException e) {
                return null;
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

            try {

                String nextLine = in.nextLine();

                if (nextLine.equals("exit")) {
                    return null;
                }

                ArrayList<String> locationArray = parce.arrayParce(nextLine);



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
            } catch (IllegalArgumentException ee) {
                System.out.println("Ошибка ввода. Имя не может быть пустым");
                isCorrect = false;
                //return vector;
            } catch (NoSuchElementException e) {
                return null;
            }

        }

        studyGroup.setGroupAdmin(person);
        studyGroup.setCreator(login);

        AddUpdateCommand addUpdateCommand = null;
        if (isCorrect) {
            //vector.add(studyGroup);




            if (isAdd == 1 || isAdd == 5) {

                addUpdateCommand = new AddUpdateCommand(studyGroup, isAdd);


                if (isAdd != 5) {
                    System.out.println("Элемент добавлен. Можете ввести следующую команду");
                }

            } else if (isAdd == 2 || isAdd == 6) {

                if (isCorrect) {

                    addUpdateCommand = new AddUpdateCommand(studyGroup, isAdd, idIndex);

                    //vector.set(index, studyGroup);  // на сервере парсить по другому

                }

                if (isAdd != 6) {
                    System.out.println("Элемент изменен. Можете ввести следующую команду");
                }

            } else if (isAdd == 3 || isAdd == 7) {

                if (isCorrect) {

                    addUpdateCommand = new AddUpdateCommand(studyGroup, isAdd, idIndex);

                    //vector.add(index, studyGroup);
                }

            } else if (isAdd == 4 || isAdd == 8) {

                addUpdateCommand = new AddUpdateCommand(studyGroup, isAdd);

//                if (maxStudents <= Long.parseLong(line.get(3))) {               // создать еще конструкторов и передать
//                                                                                // эти поля (и искать на сервере)
//
//                    if (isCorrect) {
//                        //vector.add(index, studyGroup);
//                    }
//
//                    if (isAdd != 8) {
//                        System.out.println("Элемент добавлен. Можете ввести следующую команду");
//                    }
//                } else if (isAdd != 8) {
//
//                    System.out.println("Элемент не максимальный");
//
//                }
//
            }
        }

        //Collections.sort(vector);

//        try {
//
//            File bufferFile = new File("Instr.txt");
//
//            bufferFile.createNewFile();
//
//            SaveCommand saveCommand = new SaveCommand(vector, "Instr.txt");
//            saveCommand.saveFile(vector, "Instr.txt");
//
//        } catch (FileNotFoundException e) {
//            // если файла нет - нужно создать
//
//        } catch (IOException e) {

//        }

        return addUpdateCommand;




    }
}
