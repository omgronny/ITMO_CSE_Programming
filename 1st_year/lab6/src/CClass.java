import collection.Collection;
import collection.StudyGroup;
import commands.Command;
import commands.SaveCommand;
import parse.ParceCSV;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class CClass implements Server {

    private static Selector selector = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ParceCSV parceCSV = new ParceCSV();

        String filename = System.getenv("INPUT_PATH");

        Scanner inn = new Scanner(System.in);

        try {

            File file = new File(filename);
            File backUpFile = new File("Instr.txt");

            Path path = Paths.get("Instr.txt");


            if (backUpFile.exists() && Files.isReadable(path)) {

                System.out.println("Кажется, последний раз программа была завершена некорректно или без сохранения. " +
                        " Вы хотите восстановить последние изменения коллекции? (Если вы хотите выйти, " +
                        "напишите 'Да/Yes')");

                String isAgree = inn.nextLine();

                if (isAgree.toLowerCase().equals("да") || isAgree.toLowerCase().equals("yes")
                        || isAgree.toLowerCase().equals("lf")) {

                    System.out.println(parceCSV.fileToArray(backUpFile));

                    Collection.setVector(parceCSV.addCSVToVector(backUpFile, Collection.getVector()));

                } else {

                    System.out.println(parceCSV.fileToArray(file));

                    //vector =
                    Collection.setVector(parceCSV.addCSVToVector(file, Collection.getVector()));



                }

            } else {


                System.out.println(parceCSV.fileToArray(file)); // !!!

                //vector =
                Collection.setVector(parceCSV.addCSVToVector(file, Collection.getVector()));

            }

        } catch (NullPointerException e) {

            //System.out.println("Переменная не найдена");
            //System.exit(0);

        }

        //System.out.println(Collection.getVector());

        // IO

        System.out.println("Укажите порт");

        Scanner in = new Scanner(System.in);
        int port = 8000;
        try {
            port = in.nextInt();
            System.out.println("Значение установлено");
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение порта, Установлен порт по умолчанию (8000)");

        }

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            if (bufferedReader.ready()) {

                SaveCommand saveCommand = new SaveCommand(Collection.getVector(), System.getenv("INPUT_PATH"));
                saveCommand.execute();

                System.exit(0);
            }

            ByteBuffer b = ByteBuffer.allocate(5000);

            Socket s = serverSocket.accept();

            InputStream i = s.getInputStream();
            try {
                i.read(b.array());
            } catch (SocketException e) {
                //System.out.println("Похоже пользователей не осталось, до свидания");
                continue;
            }


            Command command = Command.fromBytes(b.array());

            String outString = command.execute();

            OutputStream outputStream = s.getOutputStream();

            outputStream.write(Command.toBytesFromString(outString));

            outputStream.flush();



        }




    }


}