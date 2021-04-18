import collection.Collection;
import commands.Command;
import commands.SaveCommand;
import databasehelpers.DataBaseWorker;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.sql.SQLException;
import java.util.*;

import static java.nio.ByteBuffer.allocate;
import static java.nio.channels.SelectionKey.*;

public class CClass implements Server {

    private static Selector selector = null;
    private static final ByteBuffer readBuffer = allocate(5000);
    private static final ByteBuffer writeBuffer = ByteBuffer.allocate(5000);

    private CClass() throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        InetSocketAddress isa = new InetSocketAddress(8000);
        serverChannel.socket().bind(isa);

        selector = SelectorProvider.provider().openSelector();
        serverChannel.register(selector, OP_ACCEPT);

    }
    //private static HashMap<SocketChannel, SocketChannel> channelsMap = new HashMap<>();
    private static final ArrayList<SocketChannel> readChannels = new ArrayList<>();
    private static final ArrayList<SocketChannel> writeChannels = new ArrayList<>();

    public static void main(String[] args) {

        // testing


        DataBaseWorker dataBaseWorker = new DataBaseWorker();
        try {
            dataBaseWorker.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //*********************************************************************
        //*********************************************************************
        //*********************************************************************

        /*

        ParceCSV parceCSV = new ParceCSV();

        String filename = System.getenv("INPUT_PATH");


        if (filename == null) {
            System.out.println("Файл не найден");
            System.exit(0);
        }

        Path path = Paths.get(filename);
        if (!Files.isReadable(path)) {
            System.out.println("Ошибка прав");
            //return vector;
        }


        Scanner inn = new Scanner(System.in);

        try {

            File file = new File(filename);
            File backUpFile = new File("Instr.txt");

            Path path1 = Paths.get("Instr.txt");


            if (backUpFile.exists() && Files.isReadable(path1)) {

                System.out.println("Кажется, последний раз программа была завершена некорректно или без сохранения. " +
                        " Вы хотите восстановить последние изменения коллекции? (Если вы хотите выйти, " +
                        "напишите 'Да/Yes')");

                String isAgree = inn.nextLine();

                if (isAgree.toLowerCase().equals("да") || isAgree.toLowerCase().equals("yes")
                        || isAgree.toLowerCase().equals("lf")) {

                    //System.out.println(parceCSV.fileToArray(backUpFile));

                    Collection.setVector(parceCSV.addCSVToVector(backUpFile, Collection.getVector()));

                } else {

                    //System.out.println(parceCSV.fileToArray(file));

                    //vector =
                    Collection.setVector(parceCSV.addCSVToVector(file, Collection.getVector()));



                }

            } else {


                //System.out.println(parceCSV.fileToArray(file)); // !!!

                //vector =
                Collection.setVector(parceCSV.addCSVToVector(file, Collection.getVector()));

            }

        } catch (NullPointerException e) {

            //System.out.println("Переменная не найдена");
            //System.exit(0);

        }

        */

        System.out.println("Укажите порт");

        Scanner in = new Scanner(System.in);
        int port = 8000;
        try {
            port = in.nextInt();
            System.out.println("Значение установлено");
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение порта, Установлен порт по умолчанию (8000)");

        }

        //System.out.println(Collection.getVector());

        // IO

        while (true) {

            try {
                clientWorker(port);
            } catch (IOException ignore) {

            }


            writeBuffer.clear();
            readBuffer.clear();

        }

            /*

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(socketAddress);

            ByteBuffer b = ByteBuffer.allocate(5000);

            SocketChannel s = serverSocketChannel.accept();

            serverSocketChannel.configureBlocking(false);

             */



            /*
            Command command = null;

            while (true) {

                try {

                    byte[] bytes = b.array();

                    s.read(b);
                    command = Command.fromBytes(b.array());

                    break;

                } catch (SocketException e) {
                    //System.out.println("Похоже пользователей не осталось, до свидания");
                    continue;
                } catch (ClassCastException | NullPointerException e) {
                    // ignore
                    // b.clear();
                }

            }

            b.clear();

            String outString = command.execute();

            b.put(Command.toBytesFromString(outString));

            b.flip();
            s.write(b);

            b.clear();


            s.close();
            serverSocketChannel.close();

             */





    }

    private static void clientWorker(int port) throws IOException {

        //SocketAddress socketAddress = new InetSocketAddress(port);
        selector = Selector.open();

        ServerSocketChannel socket = ServerSocketChannel.open();

        ServerSocket serverSocket = socket.socket();

        try {
            serverSocket.bind(new InetSocketAddress("localhost", port));        // channel for read
        } catch (BindException ignore) {

        }

        socket.configureBlocking(false);

        int ops = socket.validOps();
        socket.register(selector, ops, null);

        //SocketChannel client = null;
        //SocketChannel secondClient = null;
        int a = 0;

        while (true) {

            // to close and save
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            if (bufferedReader.ready()) {

                Scanner inn = new Scanner(System.in);

                //SaveCommand saveCommand = new SaveCommand(Collection.getVector(), System.getenv("INPUT_PATH"));
                //saveCommand.execute();
                DataBaseWorker dataBaseWorker = new DataBaseWorker();
                try {
                    dataBaseWorker.saveToDataBase();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if (!inn.nextLine().equals("save")) {

                    System.exit(0);

                }
            }
            // continue


            selector.select();

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> i = selectedKeys.iterator();


            while (i.hasNext()) {

                SelectionKey key = i.next();

                if (key.isAcceptable()) {

                    if (a % 2 == 0) {

                        //System.out.println(4);

                        SocketChannel client = socket.accept();
                        client.configureBlocking(false);

                        client.register(selector, SelectionKey.OP_READ);

                        readChannels.add(client);
                        //key.interestOps(SelectionKey.OP_READ);

                        a++;

                    } else {

                        //System.out.println(5);

                        SocketChannel secondClient = socket.accept();
                        secondClient.configureBlocking(false);

                        secondClient.register(selector, SelectionKey.OP_WRITE);

                        writeChannels.add(secondClient);

                        //key.interestOps(SelectionKey.OP_WRITE);
                        a++;

                    }


                } else if (key.isReadable()) {

                    try {

                        handleRead(key, (SocketChannel) key.channel(),
                                writeChannels.get(readChannels.indexOf(key.channel())));

                    } catch (SocketException e) {

//                        socket.close();
//                        client.close();
//                        secondClient.close();
                        //serverSocket.bind(new InetSocketAddress("localhost", 8100));
                        serverSocket.close();
                        selector.close();

                        return;
                    } catch (IOException e) {
                        System.out.println("mmm");
                    }

                }

                i.remove();

            }

        }

    }

    private static void handleRead(SelectionKey key, SocketChannel client, SocketChannel writeChannel)
            throws IOException {

        //System.out.println("2");

        //SocketChannel client = (SocketChannel) key.channel();

        client.read(readBuffer);

        //System.out.println(3);

        try {

            Command command = Command.fromBytes(readBuffer.array());

            String outString = command.execute();

            //client.register(selector, SelectionKey.OP_WRITE);

            readBuffer.clear();
            writeBuffer.clear();

            writeBuffer.put(Command.toBytesFromString(outString));

            //System.out.println(outString);

            writeBuffer.flip();
            writeChannel.write(writeBuffer);

            writeBuffer.clear();

            client.register(selector, SelectionKey.OP_READ);
            writeChannel.register(selector, SelectionKey.OP_WRITE);


        } catch (ClassNotFoundException e) {
            // ignore
        }

    }


}