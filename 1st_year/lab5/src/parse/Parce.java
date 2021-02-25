package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * superclass for parsers
 */

public class Parce {

    /**
     * method for parsing String to ArrayList of Strings. Separation by ','
     * @param str String to parsing
     * @return ArrayList of words
     */

    public ArrayList<String> arrayParce(String str) {

        ArrayList<String> strArray = new ArrayList<>();

        String helpStr = "";

        for (int i = 0; i < str.length(); i++) {

            if (String.valueOf(str.charAt(i)).equals("#")) {

                helpStr += String.valueOf(str.charAt(i));
                helpStr += String.valueOf(str.charAt(i+1));

                i++;

            } else if (String.valueOf(str.charAt(i)).equals(",") || String.valueOf(str.charAt(i)).equals(" ")) {

                strArray.add(helpStr);
                helpStr = "";

            } else {
                helpStr += String.valueOf(str.charAt(i));
            }

        }

        strArray.add(helpStr);


        return strArray;
    }

    /**
     * method which parsing line of file to Array
     * @param file file foe parsing
     * @return ArrayList of file-lines
     */

    public ArrayList<String> lineToArray(File file) {

        //File file = new File("Data.csv");
        ArrayList<String> lines = new ArrayList<String>();

        try {

            Scanner sc = new Scanner(file);

            String line = sc.nextLine();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }

        return lines;
    }

    public static String nonSharpString(String str) {

        String helpString = "";

        for (int i = 0; i < str.length(); i++) {
            if (!String.valueOf(str.charAt(i)).equals("#")) {
                helpString += String.valueOf(str.charAt(i));
            }
        }

        return helpString;

    }



}
