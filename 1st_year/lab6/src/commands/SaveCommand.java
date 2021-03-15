package commands;

import collection.Collection;
import collection.StudyGroup;
import exceptions.ValueException;
import parse.ParceCSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;

/**
 * class that realization the save command
 */

public class SaveCommand extends Command implements Serializable {

    private final Vector<StudyGroup> vector;
    private final String filename;

    public SaveCommand(Vector<StudyGroup> vector, String filename) {
        this.vector = vector;
        this.filename = filename;
    }

    @Override
    public String execute() {
        try {
             saveFile(Collection.getVector(), System.getenv("INPUT_PATH"));
        } catch (FileNotFoundException e) {
            return ("Файл не найден");
        }
        return "До скорой встречи!";

    }

    /**
     * method that realization the save command
     * @param vector main collection
     * @param filename file that contains elements of collection
     */

    public boolean saveFile(Vector<StudyGroup> vector, String filename) throws FileNotFoundException {

        File file = new File(filename);

        ArrayList<String> list = new ArrayList<>();

        for (StudyGroup s : vector) {
            try {
                list.add(ParceCSV.parceArrayToCSVFormat(s.getObject()));
            } catch (NullPointerException e) {
                list.add("\n");
            }
        }

        if (filename == null) {

            System.out.println("Файл не найден");
            return false;

        }

        try {

            Path path = Paths.get(filename);

            if (!(Files.isWritable(path))) {
                throw new ValueException();
            }

            ParceCSV.writerToCSV(file, list);

        } catch (ValueException e) {

            System.out.println("Ошибка прав, нужны права на запись");
            throw new FileNotFoundException();

        }

        return true;

    }

}

