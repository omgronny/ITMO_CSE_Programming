package collection;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;


public class Collection {


    private static CopyOnWriteArrayList<StudyGroup> vector = new CopyOnWriteArrayList<>();

    private static HashMap<String, String> usersMap = new HashMap<>();

    private static String serverAdmin;

    public static CopyOnWriteArrayList<StudyGroup> getVector() {
        return vector;
    }

    public static void setVector(CopyOnWriteArrayList<StudyGroup> vector) {
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
