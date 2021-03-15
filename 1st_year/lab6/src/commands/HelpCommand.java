package commands;

import collection.StudyGroup;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Vector;

/**
 * Class that realization a technical-inf-commands
 */

public class HelpCommand extends Command implements Serializable {

    @Override
    public String execute() {
        return help();
    }

    /**
     * method for print the available commands
     */

    public String help() {
        return ("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                "количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию. Нужно в этой же строке через запятую без " +
                "пробелов ввести имя, координаты X,Y и количество студентов.\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному. Нужно в этой" +
                " же строке через запятую без" +
                "пробелов ввести имя, координаты X,Y и количество студентов\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию. Нужно в этой же строке " +
                "через запятую без " +
                "пробелов ввести имя, координаты X,Y и количество студентов\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение. Нужно в этой же строке " +
                "через запятую без " +
                "пробелов ввести имя, координаты X,Y и количество студентов " +
                "превышает значение наибольшего элемента этой коллекции\n" +
                "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                "group_counting_by_name : сгруппировать элементы коллекции по значению поля name, " +
                "вывести количество элементов в каждой группе\n" +
                "filter_by_form_of_education formOfEducation : вывести элементы, значение поля formOfEducation " +
                "которых равно заданному\n" +
                "print_field_descending_students_count : вывести значения поля studentsCount всех элементов " +
                "в порядке убывания");

    }



}
