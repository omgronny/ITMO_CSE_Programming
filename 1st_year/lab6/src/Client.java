import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

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

        Commander commander = new Commander();
        commander.consoleInput(null, in, 1, port);



    }

}
