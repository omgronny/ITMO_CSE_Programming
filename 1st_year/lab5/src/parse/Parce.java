package parse;

import java.util.ArrayList;

public class Parce {

    public ArrayList<String> arrayParce(String str) {

        ArrayList<String> strArray = new ArrayList<>();

        String helpStr = "";
        for (int i = 0; i < str.length(); i++) {

            if (String.valueOf(str.charAt(i)).equals(",")) {
                strArray.add(helpStr);
                helpStr = "";
            } else {
                helpStr += String.valueOf(str.charAt(i));
            }

        }
        strArray.add(helpStr);


        return strArray;
    }



}
