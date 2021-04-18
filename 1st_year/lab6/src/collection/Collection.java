package collection;

import java.util.HashMap;
import java.util.Vector;

public class Collection {

    private static Vector<StudyGroup> vector = new Vector<>();

    private static HashMap<String, String> usersMap = new HashMap<>();

    private static String serverAdmin;

    public static Vector<StudyGroup> getVector() {
        return vector;
    }

    public static void setVector(Vector<StudyGroup> vector) {
        Collection.vector = vector;
    }



    public static HashMap<String, String> getUsersMap() {
        return usersMap;
    }

    public static void setUsersMap(HashMap<String, String> usersMap) {
        Collection.usersMap = usersMap;
    }


    public static String getServerAdmin() {
        return serverAdmin;
    }

    public static void setServerAdmin(String serverAdmin) {
        Collection.serverAdmin = serverAdmin;
    }
}
