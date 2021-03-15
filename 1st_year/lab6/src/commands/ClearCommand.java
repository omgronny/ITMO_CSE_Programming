package commands;

import collection.Collection;
import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * Class that realization Clear-Command
 */

public class ClearCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
//
//    public ClearCommand(Vector<StudyGroup> vector) {
//        this.vector = vector;
//    }

    @Override
    public String execute() {

        Collection.setVector(clear(Collection.getVector()));

        return "Коллекция очищена"; //clear(this.vector);
    }

    /**
     * method that realization Clear-Command
     * @param vector1 Vector which contains the objects of StudyGroup
     * @return Vector which contains the objects of StudyGroup
     */

    public Vector<StudyGroup> clear(Vector<StudyGroup> vector1) {

        Vector<StudyGroup> vector = new Vector<>();

        vector1 = vector;

//        try {
//
//            File bufferFile = new File("Instr.txt");
//
//            bufferFile.createNewFile();
//
//            SaveCommand saveCommand = new SaveCommand(vector, "Instr.txt");
//
//            saveCommand.execute();
//
//        } catch (FileNotFoundException e) {
//            // если файла нет - нужно создать
//
//        } catch (IOException e) {
//
//        }

        //System.out.println("Коллекция очищена");

        return vector1;

    }
}
