package commands;

import collection.StudyGroup;

import java.util.Vector;

public class Clear {

    public static Vector<StudyGroup> clear(Vector<StudyGroup> vector) {

        for (int i = 0; i < vector.size(); i++) {

            vector.remove(i);

        }

        System.out.println("Коллекция очищена");

        return vector;
    }
}
