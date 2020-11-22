package heroes;

import Food.Foods;
import locations.*;

public class Dunno implements Human {

    private String name;
    private int age;
    private int iq;
    private int money;
    private int foodCount;

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        this.name = name;
        this.age = age;
        this.iq = 0;
        this.money = money;
        this.foodCount = 5;
    }

    @Override
    public String toString() {
        return "Hi, my name is " +  this.getName() + " i'm " + ((Integer) this.getAge()).toString() + "y.o";
    }

    @Override
    public int hashCode() {
        return this.name.length() + this.age + this.money + this.foodCount;
    }

    @Override
    public boolean equals(Object o) {
        return ((Human) o).getName().equals(this.name);
    }


    @Override
    public boolean isFriend(Human human) {
        Goat g = new Goat();
        return human.getClass() == g.getClass();
    }

    public boolean forgetting(Human human) {
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

    public void walking() {
        System.out.println("i'm walking");
    }

    public void askMoney(Minoga minoga, int x) {
        System.out.println(this.getName() + ": " + minoga.getName() + " give me money please");
        minoga.getMoneyToDunno(this, x);
    }

    public void lunch() {

        if (Math.random() > 0.5) {
            System.out.println("i'm eating with dogs");
            this.foodCount++;
        } else {
            System.out.println("i'm eating alone");
        }


    }

    @Override
    public void locationis() {

        RichHome rh = new RichHome();
        System.out.println("i'm live in");
        rh.thisLocationIs();

    }

    public void visit(Human human) {
        if (this.isFriend(human)) {
            System.out.println("i'm visiting " + human.getName());
        }
    }


}