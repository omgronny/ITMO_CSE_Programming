package commands;

import collection.Collection;
import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * class that realization the remove by id command
 */

public class RemoveByIdCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
    private final int id;

    private boolean isId = true;
//
    public RemoveByIdCommand(int id) {
//        this.vector = vector;
        this.id = id;
    }

    @Override
    public String execute() {

        Collection.setVector(remove(Collection.getVector(), this.id));

        if (this.isId) {
            return ("Такого id нет");
        } else {
            return ("Элемент удален. Посмотреть все элементы можно с помощью команды show");
        }

    }

    /**
     * class that realization the remove by id command
     * @param vector main collection
     * @param id id of deleted element
     * @return main collection without element
     */

    public Vector<StudyGroup> remove(Vector<StudyGroup> vector, int id) {

        boolean isId = true;
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).getId() == id) {
                vector.remove(i);
                isId = false;
                break;
            }
        }

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

        this.isId = isId;

        return vector;


    }

}
