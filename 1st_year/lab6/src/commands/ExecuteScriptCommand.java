package commands;

import collection.Commander;
import collection.StudyGroup;

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

    public static void executeScript(String filename, long scriptPar, int port, Selector selector, SocketAddress a,
                                     SocketChannel s, SocketChannel readSocket) throws StackOverflowError {

        scriptPar--;

        File file = new File(filename);

        Path pat = Paths.get(filename);

        if (!Files.isReadable(pat) && file.exists()) {
            System.out.println("Ошибка прав при исполнении скрипта. Нужны права на чтение");
            return;
        }

        Scanner scanner;

        try {

            scanner = new Scanner(pat);

            scanner.useDelimiter(System.getProperty("line.separator"));
            Commander commander = new Commander();

            commander.consoleInput(file, scanner, scriptPar, port, selector, a, s, readSocket);

            scanner.close();

            System.out.println("Скрипт " + filename + " выполнен.");

        } catch (NoSuchFileException e) {

            System.out.println("Файл не найден");

        } catch (IOException e) {

        }


    }
}
