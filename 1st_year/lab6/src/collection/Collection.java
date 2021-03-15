package collection;

import java.util.Vector;

public class Collection {

    private static Vector<StudyGroup> vector = new Vector<>();

    public static Vector<StudyGroup> getVector() {
        return vector;
    }

    public static void setVector(Vector<StudyGroup> vector) {
        Collection.vector = vector;
    }
}
