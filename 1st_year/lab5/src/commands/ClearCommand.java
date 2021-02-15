package commands;

import collection.StudyGroup;

import java.util.Vector;

/**
 * Class that realization Clear-Command
 */

public class ClearCommand extends Command {

    /**
     * method that realization Clear-Command
     * @param vector Vector which contains the objects of StudyGroup
     * @return Vector which contains the objects of StudyGroup
     */

    public static Vector<StudyGroup> clear(Vector<StudyGroup> vector) {

        for (int i = 0; i < vector.size(); i++) {

            vector.remove(i);

        }

        System.out.println("Коллекция очищена");

        return vector;
    }
}
