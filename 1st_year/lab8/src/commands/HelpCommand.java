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

    //@Override
    public String man() {
        return null;
    }

    /**
     * method for print the available commands
     */

    public String help() {
        return ("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                "количество элементов и т.д.)\n" +

                "add {element} : добавить новый элемент в коллекцию.\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному." +
                "\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +


                "execute_script file_name : считать и исполнить скрипт из указанного файла. " +
                "\n" +
                "exit : завершить программу\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию.\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение максимально. " +
                "\n" +
                "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +

                "clear_my_elements - удалить все элементы текущего пользователя");

    }



}
