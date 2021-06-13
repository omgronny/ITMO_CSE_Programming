package commands;

import collection.Commander;
import collection.StudyGroup;
import exceptions.ValueException;

import java.io.File;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Vector;

public class ExecuteScriptCommand {

    public static void executeScript(String filename, SocketChannel s)
            throws StackOverflowError {


        File file = new File(filename);

        Path pat = Paths.get(filename);

//        if (!Files.isReadable(pat) && file.exists()) {
//            throw new ValueException();
//        }

        Scanner scanner;

        try {

            scanner = new Scanner(pat);

            scanner.useDelimiter(System.getProperty("line.separator"));
            Commander commander = new Commander();

            commander.consoleInput(file, scanner, s);

            scanner.close();

            System.out.println("Скрипт " + filename + " выполнен.");

        }  catch (IOException e) {

        }


    }
}
