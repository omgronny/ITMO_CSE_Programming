package commands;

import collection.Collection;
import collection.FormOfEducation;
import collection.StudyGroup;
import parse.Parce;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that realization a sorted and filtered commands
 */

public class ReorderCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
   private final boolean isScript;
//
    public ReorderCommand(boolean isScript) {
//        this.vector = vector;
        this.isScript = isScript;
    }

    @Override
    public String execute() {

        Collection.setVector(reorder(Collection.getVector(), this.isScript));

        if (this.isScript) {

            return ("Коллекция отсортирована.");

        } else {

            return ("Коллекция отсортирована. Введите следующую команду");

        }

    }

    /**
     * method that print the sorted collection
     * @param vector main collection
     * @param isScript variable that check program to script
     * @return main collection
     */

    public CopyOnWriteArrayList<StudyGroup> reorder(CopyOnWriteArrayList<StudyGroup> vector, boolean isScript) {

        CopyOnWriteArrayList<StudyGroup> vector1 = new CopyOnWriteArrayList<>();

        for (int i = vector.size()-1; i >= 0; i--) {
            vector1.add(vector.get(i));
        }

        vector = vector1;

        return vector;


/*
        try {

            File bufferFile = new File("Instr.txt");

            bufferFile.createNewFile();

            SaveCommand saveCommand = new SaveCommand(vector, "Instr.txt");

            saveCommand.execute();

        } catch (FileNotFoundException e) {
            // если файла нет - нужно создать

        } catch (IOException e) {

        }

 */

    }



}
