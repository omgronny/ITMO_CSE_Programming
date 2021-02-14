package commands;

import collection.StudyGroup;

import java.util.Vector;

public class InfoHelpShowCommand {
    public static void help() {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                "group_counting_by_name : сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе\n" +
                "filter_by_form_of_education formOfEducation : вывести элементы, значение поля formOfEducation которых равно заданному\n" +
                "print_field_descending_students_count : вывести значения поля studentsCount всех элементов в порядке убывания");
    }


    public static void info(Vector<StudyGroup> vector) {

        System.out.println("Информация о коллекции: \n Коллекция типа Vector, хранит объекты " +
                vector.get(0).getClass().toString() + " \n Количество элементов в коллекции: " + vector.size() + " \n " +
                "В коллекции лежат элементы с именами: ");

        for (StudyGroup s : vector) {
            System.out.println(s.getName());
        }

    }

    public static void show(Vector<StudyGroup> vector) {
        for (StudyGroup s : vector) {
            System.out.println(s.getName() + " " + s.getId() + " " + " " +
                            s.getFormOfEducation().toString() + " " + s.getGroupAdmin().getName() + " " +
                    s.getSemesterEnum().toString() + " " + s.getStudentsCount());
        }
    }



}
