import heroes.*;

public class Main {

    public static void main(String[] args) {

        Dunno dunno = new Dunno("Neznayka", 18, 0);
        Goat goat = new Goat("Kozlik", 18);
        Minoga minoga = new Minoga("Minoga", 50, 30);


        System.out.println("Hi, i'm " + dunno.getName());

        System.out.println("----------");

        System.out.println(dunno.toString());


        if (dunno.isFriend(goat)) {
            System.out.println(dunno.getName() + ": " + goat.getName() + " is my friend");
        }

        dunno.askMoney(minoga, 10);


        dunno.visit(goat);
        dunno.walking();
        dunno.lunch();
        dunno.getFoodToGoat(goat);

        dunno.askMoney(minoga, 10);


        System.out.println("---------------");

        System.out.println(dunno.getFoodCount());
        dunno.lunch();
        dunno.getFoodToGoat(goat);
        System.out.println(dunno.getFoodCount());




    }

}