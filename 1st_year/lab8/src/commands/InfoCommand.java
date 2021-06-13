package commands;

import collection.Collection;
import collection.Condition;
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
//

    @Override
    public String execute() {
        return info(Collection.getVector());
    }


    public String names(CopyOnWriteArrayList<StudyGroup> studyGroups) {

        String str = "";

        for (StudyGroup s : studyGroups) {

            str += (!s.getCondition().equals(Condition.DELETE) ? s.getName() + "\n" : "");

        }

        return str;

    }

    public int sizeOfVector(CopyOnWriteArrayList<StudyGroup> studyGroups) {

        int size = 0;

        for (StudyGroup s : studyGroups) {

            size += (!s.getCondition().equals(Condition.DELETE) ? 1 : 0);

        }

        return size;
    }

    public String info(CopyOnWriteArrayList<StudyGroup> vector) {

        String str = "";

        str += ("Информация о коллекции: \n Коллекция типа Vector, хранит объекты " +
                vector.get(0).getClass().toString() + " \n Количество элементов в коллекции: " + sizeOfVector(vector) + " \n " +
                "В коллекции лежат элементы с именами: \n" + names(vector));

        return str;

    }

}
