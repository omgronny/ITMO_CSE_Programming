import heroes.*;

public class Main {

    public static void main(String[] args) {

        Dunno dunno = new Dunno("Neznayka", 18, 0);
        Goat goat = new Goat("Kozlik", 18);
        Minoga minoga = new Minoga("Minoga", 50, 11);

        System.out.println(dunno.toString());

        if (dunno.isFriend(goat)) {
            System.out.println(dunno.getName() + ": " + goat.getName() + " is my friend");
        }

        System.out.println("---------");
        dunno.walking();
        dunno.lunch();
        System.out.println("---------");
        dunno.askMoney(minoga, 10);
        System.out.println("---------");
        dunno.visit(goat);
        dunno.walking();
        dunno.lunch();
        dunno.getFoodToGoat(goat);

        System.out.println("---------");

        dunno.askMoney(minoga, 10);


        System.out.println("---------------");

        System.out.println(dunno.getName() + ": The amount of my food is " + dunno.getFoodCount());
        dunno.lunch();
        System.out.println(dunno.getName() + ": The amount of my food is " + dunno.getFoodCount());
        dunno.getFoodToGoat(goat);
        System.out.println(dunno.getName() + ": The amount of my food is " + dunno.getFoodCount());

        System.out.println(minoga.hashCode());
        System.out.println(goat.hashCode());
        System.out.println();

    }

}