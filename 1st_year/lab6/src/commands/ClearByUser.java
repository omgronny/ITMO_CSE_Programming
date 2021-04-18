package commands;

import collection.Collection;
import collection.Condition;
import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * Class that realization Clear-Command
 */

public class ClearByUser extends Command implements Serializable {

    private String login;

    public ClearByUser(String login) {
        this.login = login;
    }

    @Override
    public String execute() {

        Collection.setVector(clearByThisUser(Collection.getVector()));

        return "Элементы удалены"; //clear(this.vector);
    }



    /**
     * method that realization Clear-Command
     * @param vector1 Vector which contains the objects of StudyGroup
     * @return Vector which contains the objects of StudyGroup
     */

    public Vector<StudyGroup> clearByThisUser(Vector<StudyGroup> vector1) {

        for (StudyGroup s : vector1) {
            if (s.getCreator().equals(this.login)) {
                s.setCondition(Condition.DELETE);
            }
        }

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
