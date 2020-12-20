package heroes;

import animals.DecentDog;
import animals.Dogs;
import animals.Mimishka;
import animals.Roland;
import exceptions.DogNotFoundException;
import food.Foods;
import locations.*;

import java.util.ArrayList;

public class Dunno extends Hero implements Forgettable, Runnable {

    private int iq;
    private int money;
    private int foodCount;
    private ArrayList<String> journal = new ArrayList<>();

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public int getMoney() { return money; }

    public void setMoney(int money) {
        this.money = money;
    }

    public Dunno() { }

    public Dunno(String name, int age, int money) {
        super(name,age);
        this.iq = 0;
        this.money = money;
        this.foodCount = 5;
        this.setLocation(new RichHome());
    }



    @Override
    public String toString() {
        return "Hi, my name is " +  this.getName() + " i'm " + ((Integer) this.getAge()).toString() + "y.o";
    }

    @Override
    public int hashCode() {
        return this.getName().length() + this.getAge() + this.money + this.foodCount;
    }


    @Override
    public boolean isFriend(Hero human) {
        Goat g = new Goat();
        return human.equals(g);
    }

    @Override
    public boolean forgetting(Hero human) {
        return !(isFriend(human));
    }

    public void getFoodToGoat(Goat goat) {

        goat.setFoodCount(goat.getFoodCount() + 1);
        this.foodCount--;

        if (Math.random() <= 0.3) {

            Foods f = Foods.BREAD;
            System.out.println("get " + f + " to " + goat.getName());

        } else if (Math.random() >= 0.7) {

            Foods f = Foods.CUTLET;
            System.out.println("get " + f + " to " + goat.getName());

        } else {

            Foods f = Foods.PIE;
            System.out.println("get " + f + " to " + goat.getName());

        }

    }

    public void walking(DecentDog decentDog) {
        decentDog.walking(this);
        this.journal.add("walking");
        System.out.println(this.getName() + ": i'm walking");
    }

    public void askMoney(Minoga minoga, int x) {
        System.out.println(this.getName() + ": " + minoga.getName() + " give me money please");
        minoga.getMoneyToDunno(this, x);
    }

    public void lunch() {

        if (Math.random() > 0.5) {
            System.out.println(this.getName() + ": i'm eating with dogs");
            DecentDog decentDog = new DecentDog("Decent Dog", 5);
            System.out.print(decentDog.getName() + ": ");
            decentDog.lunch();
            this.foodCount++;
            Doctor doctor = new Doctor();
            Hospital hospital = new Hospital();
            this.setLocation(hospital);
            this.askDoctor(doctor);
        } else {
            System.out.println(this.getName() + ": i'm eating alone");
        }


    }

    public void askDoctor(Doctor doctor) {
        doctor.setIsPlease(true);
    }

    public void dinner(Roland roland, Hero hero) throws DogNotFoundException {
        if (roland == null) {
            throw new DogNotFoundException();
        }

        System.out.println(this.getName() + ": i'm dinnering with " + roland.getName());

        if (Math.random() > 0.3) {
            this.goToSwimmingPool(roland);
            this.journal.add("Go to swimming pool");
        } else if (Math.random() < 0.7) {
            this.goToGym(roland);
            this.journal.add("Go to gym");
        } else {
            this.visit(hero);
        }
    }

    public void visit(Hero human) {

        if (this.isFriend(human)) {
            NighHome nighHome = new NighHome();
            this.setLocation(nighHome);
            System.out.println(this.getName() + ": i'm visiting " + human.getName());
            human.setHappyCount(human.getHappyCount()+1);
        }

    }

    public void goToGym(Roland decentDog) throws DogNotFoundException {
        if (decentDog == null) {
            throw new DogNotFoundException();
        }
        System.out.println(this.getName() + ": i'm in gym with " + decentDog.getName());
        Gym gym = new Gym();
        this.setLocation(gym);
        decentDog.setLocation(gym);
        this.locationis();
    }

    public void goToSwimmingPool(Roland decentDog) throws DogNotFoundException {
        if (decentDog == null) {
            throw new DogNotFoundException();
        }
        System.out.println(this.getName() + ": i'm in swimming pool with " + decentDog.getName());
        SwimmingPool swimmingPool = new SwimmingPool();
        this.setLocation(swimmingPool);
        decentDog.setLocation(swimmingPool);
        this.locationis();
    }

    public void blingToTheRoom(Roland roland, Mimishka mimishka) throws DogNotFoundException {

        if (roland == null || mimishka == null) {
            throw new DogNotFoundException();
        }

        Room room = new Room();
        roland.setLocation(room);
        mimishka.setLocation(room);
        roland.smile();
        mimishka.wagTheTail();
        this.journal.add("Smiling");
    }

    @Override
    public void run() {
        this.setHappyCount(getHappyCount()+1);
        System.out.println(this.getName() + ": i'm running");
    }

}