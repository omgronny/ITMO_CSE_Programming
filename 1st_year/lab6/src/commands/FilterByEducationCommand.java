package commands;

import collection.Collection;
import collection.FormOfEducation;
import collection.StudyGroup;
import parse.Parce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class FilterByEducationCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
    private final String typeOfEducation;
//
    public FilterByEducationCommand(String typeOfEducation) {
//        this.vector = vector;
        this.typeOfEducation = typeOfEducation;
    }


    @Override
    public String execute() {
        return filterByEducation(Collection.getVector(), this.typeOfEducation);
    }

    /**
     * method that print the filtered collection by type of education
     * @param vector main collection
     * @param typeOfEducation type of education (enum)
     * @return main collection
     */

    public String filterByEducation(Vector<StudyGroup> vector, String typeOfEducation) {

        try {
            FormOfEducation.valueOf(typeOfEducation);
        } catch (IllegalArgumentException e) {
            return "Введено некорректное значение типа образования";
        }

        String str = "";

        for (StudyGroup s : vector) {

            try  {
                if (s.getFormOfEducation().toString().equals(typeOfEducation)) {
                    str += (s.getName() + " " + s.getId() + "\n");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

        return str;
    }

}
