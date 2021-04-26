package commands;

import collection.Collection;
import collection.Condition;
import collection.StudyGroup;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShowCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
//
//    public ShowCommand(Vector<StudyGroup> vector) {
//        this.vector = vector;
//    }

    @Override
    public String execute() {

        return show(Collection.getVector()); //show(this.vector);
    }

    /**
     * method for print the elements of main collection
     * @param vector main collection
     */

    public String show(CopyOnWriteArrayList<StudyGroup> vector) {

        String outString = "";

        for (StudyGroup s : vector) {
            try {
                /* System.out.println(s.getName() + " " + s.getId() + " " + " " +
                        s.getFormOfEducation().toString() + " " + s.getGroupAdmin().getName() + " " +
                        s.getSemesterEnum().toString() + " " + s.getStudentsCount()); */
                //System.out.println(s.getObject());
                if (!s.getCondition().equals(Condition.DELETE)) {
                    outString += (s.toString() + "\n");
                }

            } catch (NullPointerException e) {
                outString += ("null " + " \n");
            }
        }

        return outString;

    }
}
