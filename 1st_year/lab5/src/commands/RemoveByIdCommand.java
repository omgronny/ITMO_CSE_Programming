package commands;

import collection.StudyGroup;

import java.util.Vector;

public class RemoveByIdCommand {

    public static Vector<StudyGroup> remove(Vector<StudyGroup> vector, int id) {

        boolean isId = true;
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).getId() == id) {
                vector.remove(i);
                isId = false;
                break;
            }
        }

        if (isId) {
            System.out.println("Такого id нет");
        }

        return vector;
    }
}
