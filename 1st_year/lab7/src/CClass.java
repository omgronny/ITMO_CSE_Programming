import collection.Collection;
import commands.Command;
import commands.SaveCommand;
import databasehelpers.DataBaseWorker;

import java.io.*;
import java.net.*;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

import static java.nio.ByteBuffer.allocate;
import static java.nio.channels.SelectionKey.*;

public class CClass implements Server {

    private static Selector mainSelector =  null;

    private static int usersCount = 0;
    //private static final ByteBuffer readBuffer = allocate(5000);
    //private static final ByteBuffer writeBuffer = ByteBuffer.allocate(5000);


    private CClass() throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        InetSocketAddress isa = new InetSocketAddress(8000);
        serverChannel.socket().bind(isa);

        mainSelector = SelectorProvider.provider().openSelector();
        serverChannel.register(mainSelector, OP_ACCEPT);

    }


    //private static HashMap<SocketChannel, SocketChannel> channelsMap = new HashMap<>();
    private static final ArrayList<SocketChannel> readChannels = new ArrayList<>();
    private static final ArrayList<SocketChannel> writeChannels = new ArrayList<>();

    private static ArrayList<ByteBuffer> readBuffers = new ArrayList<>();
    private static ArrayList<ByteBuffer> writeBuffers = new ArrayList<>();

    private static ArrayList<Selector> selectors = new ArrayList<>();
    private static ArrayList<Integer> numberOfChannelsForSelector = new ArrayList<>();

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);

    public static void main(String[] args) {

        // testing

        executor.setCorePoolSize(1);
//
//        executor.setCorePoolSize(1);
//
//        executor.setMaximumPoolSize(100);
//
//        executor.submit(() -> {
//            Thread.sleep(500);
//            System.out.println("f1");
//            return null;
//        });
//        executor.submit(() -> {
//            Thread.sleep(500);
//            System.out.println("f2");
//            return null;
//        });
//        executor.submit(() -> {
//            Thread.sleep(500);
//            System.out.println("f3");
//            return null;
//        });
//        System.out.println(executor.getPoolSize());
//        System.out.println(executor.getQueue().size());



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


        Runnable runnable = () -> {


            //SaveCommand saveCommand = new SaveCommand(Collection.getVector(), System.getenv("INPUT_PATH"));
            //saveCommand.execute();

            while (true) {

                Scanner inn = new Scanner(System.in);
                String str = inn.nextLine();

                if (str.equals("exit")) {

                    System.exit(0);

                }

                if (str.equals("users")) {

                    System.out.println("users = " + usersCount);

                }

                if (str.equals("save")) {

                    DataBaseWorker dataBaseWorkerr = new DataBaseWorker();
                    try {
                        dataBaseWorkerr.saveToDataBase();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }

        };

        Thread scannerThread = new Thread(runnable);
        scannerThread.start();


        while (true) {

            try {

                //clientWorker(selectors.get(0), port);
                mainWorker(port);

                //mainSelector.close();

                System.out.println("mainworkerfinish");

                for (Selector s : selectors) {
                    s.close();
                }

                numberOfChannelsForSelector.clear();

                selectors.clear();

                for (ByteBuffer buffer : readBuffers) {
                    buffer.clear();
                }

                for (ByteBuffer buffer : writeBuffers) {
                    buffer.clear();
                }


            } catch (IOException ignore) {

            }



            //writeBuffer.clear();
            //readBuffer.clear();

        }

    }

    private static class Writer extends RecursiveAction {

        private Selector selector;
        private SocketChannel client;
        private SocketChannel writeChannel;
        private ByteBuffer readBuffer;
        private ByteBuffer writeBuffer;
        private String outString;

        public Writer(Selector selector, SocketChannel client, SocketChannel writeChannel,
                      ByteBuffer readBuffer, ByteBuffer writeBuffer, String outString) {

            this.selector = selector;
            this.client = client;
            this.writeChannel = writeChannel;
            this.readBuffer = readBuffer;
            this.writeBuffer = writeBuffer;
            this.outString = outString;

        }

        @Override
        protected void compute() {

            try {
                handleWrite(this.selector, this.client, this.writeChannel, this.readBuffer, this.writeBuffer, this.outString);
            } catch (IOException e) {
                try {
                    this.client.close();
                } catch (IOException ioException) {

                }
            }

        }
    }

    private static void clientWorker(Selector selector, int port, ServerSocketChannel socket,
                                     ServerSocket serverSocket) throws IOException {


//        System.out.println(1);
//
//        //SocketAddress socketAddress = new InetSocketAddress(port);
//        selector = Selector.open();
//
//        ServerSocketChannel socket = ServerSocketChannel.open();
//
//        ServerSocket serverSocket = socket.socket();
//
//        try {
//            serverSocket.bind(new InetSocketAddress("localhost", port));        // channel for read
//        } catch (BindException ignore) {
//
//        }
//
//        socket.configureBlocking(false);
//
//        int ops = socket.validOps();
//        socket.register(selector, ops, null);

        //SocketChannel client = null;
        //SocketChannel secondClient = null;
        int a = 0;

        while (true) {

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
                        ByteBuffer byteBuffer = allocate(5000);
                        readBuffers.add(byteBuffer);

                        a++;

                    } else {

                        //System.out.println(5);

                        SocketChannel secondClient = socket.accept();
                        secondClient.configureBlocking(false);

                        secondClient.register(selector, SelectionKey.OP_WRITE);

                        writeChannels.add(secondClient);

                        //key.interestOps(SelectionKey.OP_WRITE);
                        ByteBuffer byteBuffer = allocate(5000);
                        writeBuffers.add(byteBuffer);

                        a++;

                    }


                } else if (key.isReadable()) {

                    try {

                        handle(selector, key, (SocketChannel) key.channel(),
                                writeChannels.get(readChannels.indexOf(key.channel())),
                                readBuffers.get(readChannels.indexOf(key.channel())),
                                writeBuffers.get(readChannels.indexOf(key.channel())));

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

                        ((SocketChannel) key.channel()).close();
                        (writeChannels.get(readChannels.indexOf(key.channel()))).close();

                        writeChannels.remove(readChannels.indexOf(key.channel()));
                        readChannels.remove(key.channel());

                        usersCount--;
                        //System.out.println("selector count: " + selector.selectNow());



//                        if (selector.selectNow() == 0) {
//                            return;
//                        } else {
//                            break;
//                        }
                        numberOfChannelsForSelector.set(selectors.indexOf(selector),
                                numberOfChannelsForSelector.get(selectors.indexOf(selector)) - 1);

                        System.out.println("S c = " + numberOfChannelsForSelector.get(selectors.indexOf(selector)));

                        if (numberOfChannelsForSelector.get(selectors.indexOf(selector)) == 0) {

                            numberOfChannelsForSelector.remove(selectors.indexOf(selector));
                            selectors.remove(selectors.indexOf(selector));
                            return;

                        } else {

                            break;

                        }

                    } catch (ClassNotFoundException e) {

                    }

                }

                i.remove();

                //System.out.println(22);

            }

            //System.out.println("after cycle");

        }

        //System.out.println("endOfClientWorker");

    }

    private static void mainWorker(int port) throws IOException {
        //SocketAddress socketAddress = new InetSocketAddress(port);

        mainSelector = Selector.open();

        ServerSocketChannel socket = ServerSocketChannel.open();

        ServerSocket serverSocket = socket.socket();

        try {
            serverSocket.bind(new InetSocketAddress("localhost", port));        // channel for read
        } catch (BindException ignore) {

        }

        socket.configureBlocking(false);

        int ops = socket.validOps();
        socket.register(mainSelector, ops, null);

        //SocketChannel client = null;
        //SocketChannel secondClient = null;
        int a = 0;

        while (true) {

            mainSelector.select();

            Set<SelectionKey> selectedKeys = mainSelector.selectedKeys();
            Iterator<SelectionKey> i = selectedKeys.iterator();

            while (i.hasNext()) {

                SelectionKey key = i.next();

                if (key.isAcceptable()) {

                    if (a % 2 == 0) {


                        System.out.println("new client, connect read channel");

                        SocketChannel client = socket.accept();
                        client.configureBlocking(false);

                        if (usersCount % 3 == 0) {

                            Selector newSelector = Selector.open();

                            client.register(newSelector, SelectionKey.OP_READ);
                            selectors.add(newSelector);

                            numberOfChannelsForSelector.add(1);

                        } else {

                            numberOfChannelsForSelector.set(numberOfChannelsForSelector.size()-1,
                                    numberOfChannelsForSelector.get(numberOfChannelsForSelector.size()-1) + 1);

                            client.register(selectors.get(selectors.size()-1), SelectionKey.OP_READ);


                        }

                        readChannels.add(client);
                        //key.interestOps(SelectionKey.OP_READ);
                        ByteBuffer byteBuffer = allocate(5000);
                        readBuffers.add(byteBuffer);

                        usersCount++;

                        a++;

                    } else {

                        System.out.println("new client, connect write channel");

                        SocketChannel secondClient = socket.accept();
                        secondClient.configureBlocking(false);

                        secondClient.register(selectors.get(selectors.size()-1), SelectionKey.OP_WRITE);

                        writeChannels.add(secondClient);

                        //key.interestOps(SelectionKey.OP_WRITE);
                        ByteBuffer byteBuffer = allocate(5000);
                        writeBuffers.add(byteBuffer);


                        if (usersCount % 3 == 1) {

                            executor.setCorePoolSize(executor.getCorePoolSize()+1);

                            executor.submit(() -> {

                                synchronized (serverSocket) {
                                    try {

                                        clientWorker(selectors.get(selectors.size() - 1), port, socket, serverSocket);

                                        //usersCount--;
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {

                                        //selectors.remove(selectors.size() - 1);

                                        System.out.println("empty selector");

                                        executor.setCorePoolSize(executor.getCorePoolSize() - 1);

                                    }
                                }

                            });





                        }

                        a++;

                    }


                }

                i.remove();

            }

            System.out.println("users count: " + usersCount);
            if (usersCount == 0) {
                break;
            }

        }

    }

    private static ByteBuffer handleRead(Selector selector,  SocketChannel client, SocketChannel writeChannel,
                               ByteBuffer readBuffer, ByteBuffer writeBuffer) throws IOException{

        client.read(readBuffer);

        return readBuffer;

    }

    private static void handle(Selector selector, SelectionKey key, SocketChannel client, SocketChannel writeChannel,
                                   ByteBuffer readBuffer, ByteBuffer writeBuffer)
            throws IOException, ClassNotFoundException {

        //System.out.println("start Handle");

        handleRead(selector, client, writeChannel, readBuffer, writeBuffer);

        /*
        execute
         */

        Runnable runnable = () -> {

            try {

                synchronized (Collection.getVector()) {

                    handleExecute(selector, client, writeChannel, readBuffer, writeBuffer);

                }

            } catch (IOException e) {

                try {
                    ((SocketChannel) key.channel()).close();
                } catch (IOException ioException) {

                }

            } catch (ClassNotFoundException e) {

            } finally {
                writeBuffer.clear();
                readBuffer.clear();
            }

        };

        Thread thread = new Thread(runnable);
        thread.start();

        /*
        write
         */

        //handleWrite(selector, client, writeChannel, readBuffer, writeBuffer, outString);



    }

    private static String handleExecute(Selector selector, SocketChannel client, SocketChannel writeChannel,
                                   ByteBuffer readBuffer, ByteBuffer writeBuffer)
            throws IOException, ClassNotFoundException {

            Command command = Command.fromBytes(readBuffer.array());

            String outString = command.execute();

            //client.register(selector, SelectionKey.OP_WRITE);
            readBuffer.clear();
            writeBuffer.clear();

            ForkJoinTask writer = new Writer(selector, client, writeChannel, readBuffer, writeBuffer, outString);
            new ForkJoinPool().invoke(writer);

            //handleWrite(selector, client, writeChannel, readBuffer, writeBuffer, outString);

            //if (outString.equals("Неверный пароль")) {
            //    throw new IOException();
            //}

            return outString;

    }

    private static void handleWrite(Selector selector, SocketChannel client, SocketChannel writeChannel,
                                   ByteBuffer readBuffer, ByteBuffer writeBuffer, String outString)
            throws IOException, BufferOverflowException {

        writeBuffer.clear();

            writeBuffer.put(Command.toBytesFromString(outString));

            //System.out.println(outString);

            writeBuffer.flip();

            try {
                writeChannel.write(writeBuffer);
            } catch (BufferOverflowException ignore) { }

            writeBuffer.clear();

            client.register(selector, SelectionKey.OP_READ);
            writeChannel.register(selector, SelectionKey.OP_WRITE);


    }




}