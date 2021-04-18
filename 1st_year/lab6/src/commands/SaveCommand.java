package commands;

import collection.Collection;
import collection.StudyGroup;
import databasehelpers.DataBaseWorker;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * class that realization the save command
 */

public class SaveCommand extends Command implements Serializable {

    private String adminLogin;

    public SaveCommand(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    @Override
    public String execute() {

        if (this.adminLogin.equals(Collection.getServerAdmin())) {
            DataBaseWorker dataBaseWorker = new DataBaseWorker();
            try {
                dataBaseWorker.saveToDataBase();
            } catch (SQLException throwables) {
                System.out.println("При сохранении что-то пошло не так");
            }
        } else {
            return "У вас нет прав для этой команды";
        }

        return "До скорой встречи!  \n  До скорой встречи!  \n  Моя любовь к тебе навечно";
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

