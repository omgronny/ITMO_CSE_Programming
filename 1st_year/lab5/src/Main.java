import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<StudyGroup> vector = new Vector<>();

        ParceCSV parceCSV = new ParceCSV();

        String filename = System.getenv("INPUT_PATH");

        try {

            File file = new File(filename);
            File backUpFile = new File("Instr.txt");

            if (backUpFile.exists()) {

                System.out.println(parceCSV.fileToArray(backUpFile));

                vector = parceCSV.addCSVToVector(backUpFile, vector);

            } else {

                System.out.println(parceCSV.fileToArray(file));

                vector = parceCSV.addCSVToVector(file, vector);

            }

        } catch (NullPointerException e) {
            System.out.println("Переменная не найдена");
            System.exit(0);
        }

        Commander collection = new Commander();

        Scanner in = new Scanner(System.in);


        vector = collection.consoleInput(vector, null, in);

//        Parce parce = new Parce();
//        String s = in.nextLine();               // 17510##78926,na#,me,2.0,3,25,FULL_TIME_EDUCATION
//        System.out.println(parce.arrayParce(s));


    }

}
