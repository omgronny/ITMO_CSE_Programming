import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<StudyGroup> vector = new Vector<>();

        ParceCSV parceCSV = new ParceCSV();

        String filename = System.getenv("INPUT_PATH");

        Scanner inn = new Scanner(System.in);

        try {

            File file = new File(filename);
            File backUpFile = new File("Instr.txt");

            Path path = Paths.get("Instr.txt");

            if (backUpFile.exists() && Files.isReadable(path)) {

                System.out.println("Кажется, последний раз программа была завершена некорректно или без сохранения. " +
                        " Вы хотите восстановить последние изменения коллекции?");

                String isAgree = inn.nextLine();

                if (isAgree.toLowerCase().equals("да") || isAgree.toLowerCase().equals("yes")
                        || isAgree.toLowerCase().equals("lf")) {

                    System.out.println(parceCSV.fileToArray(backUpFile));

                    vector = parceCSV.addCSVToVector(backUpFile, vector);

                } else {

                    System.out.println(parceCSV.fileToArray(file));

                    //vector =
                    parceCSV.addCSVToVector(file, vector);

                }

            } else {

                System.out.println(parceCSV.fileToArray(file));

                //vector =
                parceCSV.addCSVToVector(file, vector);

            }

        } catch (NullPointerException e) {

            System.out.println("Переменная не найдена");
            System.exit(0);

        }

        Commander collection = new Commander();

        Scanner in = new Scanner(System.in);


        //vector =
        collection.consoleInput(vector, null, in);

//        Parce parce = new Parce();
//        String s = in.nextLine();               // 17510##78926,na#,me,2.0,3,25,FULL_TIME_EDUCATION
//        System.out.println(parce.arrayParce(s));


    }

}
