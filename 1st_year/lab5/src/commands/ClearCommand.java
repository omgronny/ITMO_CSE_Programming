package commands;

import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 * Class that realization Clear-Command
 */

public class ClearCommand extends Command {

    /**
     * method that realization Clear-Command
     * @param vector Vector which contains the objects of StudyGroup
     * @return Vector which contains the objects of StudyGroup
     */

    public static Vector<StudyGroup> clear(Vector<StudyGroup> vector) {

        for (int i = 0; i < vector.size(); i++) {

            vector.remove(i);

        }

        try {

            File bufferFile = new File("Instr.txt");

            bufferFile.createNewFile();

            SaveCommand.saveFile(vector, "Instr.txt");

        } catch (FileNotFoundException e) {
            // если файла нет - нужно создать

        } catch (IOException e) {

        }

        System.out.println("Коллекция очищена");

        return vector;
    }
}
