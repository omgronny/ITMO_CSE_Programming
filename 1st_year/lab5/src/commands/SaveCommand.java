package commands;

import collection.StudyGroup;
import parse.ParceCSV;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;

/**
 * class that realization the save command
 */

public class SaveCommand extends Command {

    /**
     * method that realization the save command
     * @param vector main collection
     * @param file file that contains elements of collection
     */

    public static void saveFile(Vector<StudyGroup> vector, File file) {

        String filename = System.getenv("INPUT_PATH");

        if (filename == null) {
            System.out.println("Переменная не найдена");
            System.exit(0);
        }
        Path path = Paths.get(filename);
        if (!(Files.isReadable(path) && Files.isExecutable(path) && Files.isWritable(path))) {
            System.out.println("Ошибка прав");
            System.exit(0);
        }

        ArrayList<String> list = new ArrayList<>();

        for (StudyGroup s : vector) {
            list.add(ParceCSV.parceArrayToCSVFormat(s.getObject()));

        }

        ParceCSV.writerToCSV(file,list);

        System.out.println("Файл сохранен");

    }

}

