package commands;

import collection.Collection;
import collection.FormOfEducation;
import collection.StudyGroup;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class SortedFilterCommand {


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
