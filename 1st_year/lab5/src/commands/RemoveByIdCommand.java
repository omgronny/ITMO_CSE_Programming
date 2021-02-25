package commands;

import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 * class that realization the remove by id command
 */

public class RemoveByIdCommand extends Command {

    /**
     * class that realization the remove by id command
     * @param vector main collection
     * @param id id of deleted element
     * @return main collection without element
     */

    public static Vector<StudyGroup> remove(Vector<StudyGroup> vector, int id) {

        boolean isId = true;
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).getId() == id) {
                vector.remove(i);
                isId = false;
                break;
            }
        }

        if (isId) {
            System.out.println("Такого id нет");
        } else {
            System.out.println("Элемент удален. Посмотреть все элементы можно с помощью команды show");
        }


        try {

            File bufferFile = new File("Instr.txt");

            bufferFile.createNewFile();

            SaveCommand.saveFile(vector, "Instr.txt");

        } catch (FileNotFoundException e) {
            // если файла нет - нужно создать

        } catch (IOException e) {

        }

        return vector;
    }
}
