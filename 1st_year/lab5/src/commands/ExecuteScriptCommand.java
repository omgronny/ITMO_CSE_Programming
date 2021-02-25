package commands;

import collection.Commander;
import collection.StudyGroup;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Vector;

public class ExecuteScriptCommand {

    public static Vector<StudyGroup> executeScript(String filename, Vector<StudyGroup> vector)
            throws StackOverflowError {

        File file = new File(filename);

        Path pat = Paths.get(filename);

        Scanner scanner;

        try {

            scanner = new Scanner(pat);

            scanner.useDelimiter(System.getProperty("line.separator"));
            Commander commander = new Commander();



            vector = commander.consoleInput(vector, file, scanner);

            scanner.close();

            System.out.println("Скрипт " + filename + " выполнен.");

        } catch (NoSuchFileException e) {

            System.out.println("Файл не найден");

        } catch (IOException e) {

        }

        return vector;


    }
}
