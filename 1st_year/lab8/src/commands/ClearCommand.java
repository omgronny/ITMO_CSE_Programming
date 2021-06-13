package commands;

import collection.Collection;
import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that realization Clear-Command
 */

public class ClearCommand extends Command implements Serializable {

    private String login;

    public ClearCommand(String login) {
        this.login = login;
    }

//    private final Vector<StudyGroup> vector;
//
//    public ClearCommand(Vector<StudyGroup> vector) {
//        this.vector = vector;
//    }

    @Override
    public String execute() {

        if (this.login.equals(Collection.getServerAdmin())) {
            Collection.setVector(clear(Collection.getVector()));
        } else {
            return "У вас недостаточно прав для очистки коллекции";
        }

        return "Коллекция очищена"; //clear(this.vector);
    }



    /**
     * method that realization Clear-Command
     * @param vector1 Vector which contains the objects of StudyGroup
     * @return Vector which contains the objects of StudyGroup
     */

    public CopyOnWriteArrayList<StudyGroup> clear(CopyOnWriteArrayList<StudyGroup> vector1) {

        CopyOnWriteArrayList<StudyGroup> vector = new CopyOnWriteArrayList<>();

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
