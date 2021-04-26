package commands;

import collection.Collection;
import collection.StudyGroup;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrintStudentsCountCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
//
//    public PrintStudentsCountCommand(Vector<StudyGroup> vector) {
//        this.vector = vector;
//    }

    @Override
    public String execute() {
        return printStudentsCount(Collection.getVector());
    }


    /**
     * method that print the sorted collection by count of students
     * @param vector main collection
     */

    public String printStudentsCount(CopyOnWriteArrayList<StudyGroup> vector) {

        String str = "";

        List<StudyGroup> helpList = new Vector<>();

        for (StudyGroup s : vector) {
            helpList.add(s);
        }

        Collections.sort(helpList);

        for (int i = helpList.size()-1; i >=0 ; i--) {
            str += (helpList.get(i).getStudentsCount() + "\n");
        }

        return str;

    }
}
