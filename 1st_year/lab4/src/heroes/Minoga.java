package heroes;

import animals.Mimishka;
import animals.Roland;
import exceptions.DogNotFoundException;
import food.Dinnerable;
import locations.Concert;
import locations.RichHome;
import locations.Theater;

import java.text.CompactNumberFormat;

public class Minoga extends Hero implements Dinnerable {

    private int money;
    private int authority;

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }

    public Minoga(String name, int age, int money) {
        super(name, age);
        this.money = money;
        this.authority = 10;
    }

    @Override
    public boolean isFriend(Hero human) { return false; }

    public void getMoneyToDunno(Dunno d, int x) {
        if (money > x) {
            System.out.println(this.getName() + ": Well, okay");
            money -= x;
            d.setMoney(d.getMoney() + x);
        } else {
            System.out.println(this.getName() + ": No! you shouldn't walk with some goat");
        }

    }

    public void eveningParty(Dunno dunno, Roland roland, Mimishka mimishka) {
        System.out.println(this.getName() + ": i have a Evening party");
        System.out.println(dunno.getName() + ": look! This is our dogs");
        try {
            dunno.blingToTheRoom(roland, mimishka);
        } catch (DogNotFoundException e) {
            e.getMessage();
        }
        this.authority++;
    }

    public void goToTheater(Mimishka mimishka, Dunno dunno, Roland roland) {
        Theater theater = new Theater();
        this.setLocation(theater);
        try {
            dunno.goToGym(roland);
        } catch (DogNotFoundException e) {
            e.getMessage();
        }
        if (Math.random() < 0.9) {
            this.authority = 0;
            System.out.println(this.getName() + ": Oh No! I forgot " + mimishka.getName() + "!! My authority is zero!");
        } else {
            mimishka.setLocation(theater);
            this.authority++;
            System.out.println(this.getName() + ": i'm going to the concert with " + mimishka.getName());
        }
    }

    public void goToConcert(Mimishka mimishka, Dunno dunno, Roland roland) {
        Concert concert = new Concert();
        this.setLocation(concert);
        try {
            dunno.goToSwimmingPool(roland);
        } catch (DogNotFoundException e) {
            e.getMessage();
        }
        if (Math.random() < 0.9) {
            this.authority = 0;
            System.out.println(this.getName() + ": Oh No! I forgot " + mimishka.getName() + "!! My authority is zero!");
        } else {
            mimishka.setLocation(concert);
            this.authority++;
            System.out.println(this.getName() + ": i'm going to the concert with " + mimishka.getName());
        }
    }

    public void dinner(Mimishka mimishka, Dunno dunno, Roland roland) throws DogNotFoundException {

        if (roland == null || mimishka == null) {
            throw new DogNotFoundException();
        }

        System.out.println(this.getName() + ": i'm dinnering");
        if (Math.random() > 0.5) {
            this.goToConcert(mimishka, dunno, roland);
        } else {
            this.goToTheater(mimishka, dunno, roland);
        }
    }






}
