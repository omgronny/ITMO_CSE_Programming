package commands;

import collection.Collection;
import collection.StudyGroup;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Class which realization counting by name command
 */

public class CountingByNameCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
//
//    public CountingByNameCommand(Vector<StudyGroup> vector) {
//        this.vector = vector;
//    }

    @Override
    public String execute() {
        return counting(Collection.getVector());
    }

    /**
     * method which realization counting by name command
     * @param vector Vector which contains the objects of StudyGroup
     */

    public String counting(Vector<StudyGroup> vector) {

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

        //System.out.println(map);

        return map.toString();

    }
}
