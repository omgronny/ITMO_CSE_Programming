package commands;

import collection.Collection;
import collection.FormOfEducation;
import collection.StudyGroup;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Class that realization a sorted and filtered commands
 */

public class SortedFilterCommand extends Command {

    /**
     * method that print the sorted collection
     * @param vector main collection
     * @param isScript variable that check program to script
     * @return main collection
     */

    public static Vector<StudyGroup> reorder(Vector<StudyGroup> vector, boolean isScript) {

        Vector<StudyGroup> vector1 = new Vector<>();

        for (int i = vector.size()-1; i >= 0; i--) {
            vector1.add(vector.get(i));
        }

        if (isScript) {
            System.out.println("Коллекция отсортирована.");
        } else {
            System.out.println("Коллекция отсортирована. Введите следующую команду");
        }

        return vector1;
    }

    /**
     * method that print the sorted collection by count of students
     * @param vector main collection
     */

    public static void printStudentsCount(Vector<StudyGroup> vector) {

        List<StudyGroup> helpList = new Vector<>();

        for (StudyGroup s : vector) {
            helpList.add(s);
        }

        Collections.sort(helpList);

        for (int i = helpList.size()-1; i >=0 ; i--) {
            System.out.println(helpList.get(i).getStudentsCount());
        }

    }

    /**
     * method that print the filtered collection by type of education
     * @param vector main collection
     * @param typeOfEducation type of education (enum)
     * @return main collection
     */

    public static Vector<StudyGroup> filterByEducation(Vector<StudyGroup> vector, String typeOfEducation) {

        try {
            FormOfEducation.valueOf(typeOfEducation);
        } catch (IllegalArgumentException e) {
            System.out.println("Введено некорректное значение типа образования");
            return vector;
        }

        for (StudyGroup s : vector) {
            if (s.getFormOfEducation().toString().equals(typeOfEducation)) {
                System.out.println(s.getName() + s.getId());
            }
        }

        return vector;
    }






}
