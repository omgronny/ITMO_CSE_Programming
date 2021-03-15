package commands;

import collection.StudyGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 * superclass for all commands
 */
public abstract class Command implements Receiver {

    private String name;

    public abstract String execute();

    public byte[] toBytes() {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
        } catch (IOException e) {
            // ignore
        }

        byte[] b = bos.toByteArray();

        return b;


    }

    public static Command fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        ObjectInput in = new ObjectInputStream(bis);

        Command command = (Command) in.readObject();

        bis.close();
        in.close();

        return command;


    }

    public static String fromBytesToString(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        ObjectInput in = new ObjectInputStream(bis);

        String out = (String) in.readObject();

        bis.close();
        in.close();

        return out;


    }

    public static byte[] toBytesFromString(String str) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(str);
            out.flush();
        } catch (IOException e) {
            // ignore
        }

        return bos.toByteArray();

    }


}
