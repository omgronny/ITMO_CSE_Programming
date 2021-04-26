import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

public class Client {

    public static void main(String[] args) throws IOException {

        // NIO
        System.out.println("Укажите порт");

        Scanner scanner = new Scanner(System.in);
        int port = 8000;
        try {
            port = scanner.nextInt();
            System.out.println("Значение установлено");
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение порта, Установлен порт по умолчанию (8000)");

        }

        Scanner in = new Scanner(System.in);

        Selector selector = Selector.open();

        SocketAddress a = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), port);
        SocketChannel s = null;

        SocketChannel readSocket = null;

        try {

            s = SocketChannel.open(a);          // channel for write
            s.configureBlocking(false);
            int ops = s.validOps();
            s.register(selector, ops, null);
            //finishConnection(s.keyFor(selector), 0);

            readSocket = SocketChannel.open(a);
            readSocket.configureBlocking(false);    // channel for read
            int ops1 = readSocket.validOps();
            readSocket.register(selector, ops1, null);
            //finishConnection(readSocket.keyFor(selector), 1);

            s.register(selector, OP_WRITE);
            readSocket.register(selector, OP_READ);

        } catch (ConnectException e) {
            System.out.println("Кажется, сервер наелся и спит, извините :(");
            System.exit(0);
        }



        Commander commander = new Commander();
        try {
            commander.consoleInput(null, in, 1, port, selector, a, s, readSocket);
        } catch (CancelledKeyException ignore) { }


    }

}
