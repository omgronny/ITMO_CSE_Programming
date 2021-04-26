package commands;

import collection.Collection;
import collection.StudyGroup;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class InfoCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
    private final String filename;
//
    public InfoCommand(String filename) {
//        this.vector = vector;
        this.filename = filename;
    }

    @Override
    public String execute() {
        return info(Collection.getVector(), this.filename);
    }

    /**
     * method for print the information about main collection
     * @param vector main collection
     * @param filename file with saved collection
     */


    public String info(CopyOnWriteArrayList<StudyGroup> vector, String filename) {

        String str = "";

        Path path = Paths.get(filename);
        BasicFileAttributes basicFileAttributes;

        try {

            basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            Date date = new Date(basicFileAttributes.creationTime().toMillis());

            str += ("Информация о коллекции: \n Инициализация: " + date + " \n Коллекция типа Vector, хранит объекты " +
                    vector.get(0).getClass().toString() + " \n Количество элементов в коллекции: " + vector.size() + " \n " +
                    "В коллекции лежат элементы с именами: \n");

        } catch (IOException e) {
            str = ("Что-то пошло не так при получении даты инициализации");
        }

        for (StudyGroup s : vector) {
            str += (s.getName() + "\n");
        }

        return str;

    }

}
