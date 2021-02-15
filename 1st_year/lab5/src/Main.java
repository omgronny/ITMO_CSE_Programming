import collection.*;
import commands.*;
import parse.*;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<StudyGroup> vector = new Vector<>();

        ParceCSV parceCSV = new ParceCSV();
        String filename = System.getenv("INPUT_PATH");
        File file = new File(filename);

        System.out.println(parceCSV.fileToArray(file));

        vector = parceCSV.addCSVToVector(file, vector);

        Collection collection = new Collection();

        Scanner in = new Scanner(System.in);

        vector = collection.consoleInput(vector, null, in);


    }

}
