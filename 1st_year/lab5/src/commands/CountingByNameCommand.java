package commands;

import collection.StudyGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CountingByNameCommand {

    public static void counting(Vector<StudyGroup> vector) {

        HashMap<String, Integer> map = new HashMap();

        for (StudyGroup s : vector) {
            if (map.containsKey(s.getName())) {
                int count = map.get(s.getName());
                map.remove(s.getName());
                map.put(s.getName(), count+1);
            } else {
                map.put(s.getName(), 1);
            }
        }

        System.out.println(map);

    }
}
