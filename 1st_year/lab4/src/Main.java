import animals.DogTypes;
import animals.Dogs;
import animals.Mimishka;
import animals.Roland;
import exceptions.DogNotFoundException;
import heroes.*;
import locations.Garden;
import locations.Locatable;

public class Main {

    public static void main(String[] args) {

        Dunno dunno = new Dunno("Neznayka", 18, 0);
        Goat goat = new Goat("Kozlik", 18) {
            @Override
            public boolean isFriend(Hero hero) {
                return false;
            }

            @Override
            public boolean isSick() {
                return false;
            }
        };
        Minoga minoga = new Minoga("Minoga", 50, 11);

        Roland roland = new Roland("Roland", 5);

        Mimishka mimishka = new Mimishka("Mimishka", 0){
            @Override
            public void wagTheTail() {
                System.out.println(this.getName() + ":  so sad((");
            }
        };

        Gardener gardener = new Gardener("Gardener", 50);

        System.out.println(dunno.toString());

        if (dunno.isFriend(goat)) {
            System.out.println(dunno.getName() + ": " + goat.getName() + " is my friend");
        }

        System.out.println("---------");
        System.out.println(roland.getName() + ": My health is " + roland.getHealth());
        dunno.lunch();
        roland.exersize(dunno);
        System.out.println(roland.getName() + ": My health is " + roland.getHealth());
        System.out.println("---------");
        dunno.askMoney(minoga, 10);
        System.out.println("---------");
        try {
            minoga.eveningParty(dunno, roland, mimishka);
        } catch (DogNotFoundException e) {
            e.getMessage();
        }
        System.out.println("---------");
        try {
            minoga.dinner(mimishka, dunno, roland);
        } catch (DogNotFoundException e) {
            e.getMessage();
        }

        try {
            dunno.dinner(roland, goat);
        } catch (DogNotFoundException e) {
            e.getMessage();
        }
        dunno.askMoney(minoga, 10);


        System.out.println("---------------");

        System.out.println(dunno.getName() + ": The amount of my food is " + dunno.getFoodCount());
        dunno.lunch();
        System.out.println(dunno.getName() + ": The amount of my food is " + dunno.getFoodCount());
        dunno.getFoodToGoat(goat);
        System.out.println(dunno.getName() + ": The amount of my food is " + dunno.getFoodCount());

        System.out.println("-----------------");


    }

}