import collection.Collection;
import collection.Person;
import collection.StudyGroup;
import parse.ParceCSV;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {


        Vector<StudyGroup>  vector = new Vector<>();

        //collection.StudyGroup studyGroup = new collection.StudyGroup(1,"name",new collection.Coordinates(1.7,2), LocalDateTime.now(),12,)

        String str = "name,x,y,,form,semester,adminName,adminHeight,adminColor,adminCountry,x,y,z,name";

        ParceCSV parceCSV = new ParceCSV();
        File file = new File("Data.csv");

        System.out.println(parceCSV.fileToArray(file));
        vector = parceCSV.addCSVToVector(file, vector);

        Collection collection = new Collection();

        Scanner in = new Scanner(System.in);

        vector = collection.consoleInput(vector, null, in);




    }
}
