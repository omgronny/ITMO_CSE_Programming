package animals;

import food.Dinnable;
import heroes.Dunno;
import heroes.Gardener;
import heroes.Jumpable;
import locations.Garden;
import locations.Locatable;
import locations.RichHome;

public  class DecentDog extends Dogs implements Dinnable, Locatable, Runnable, Jumpable {

    private int health = 5;
    private int mood;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public DecentDog(String name, int mood) {
        super(DogTypes.DECENT,name);
        this.mood = mood;
    }

    public DecentDog(){}

    @Override
    public void lunch() {
        System.out.println("i eat in RichHohe");
    }

    @Override
    public void locationis() {
        RichHome rh = new RichHome();
        System.out.println(this.getName() + ": i'm live in");
        rh.thisLocationIs();
    }

    @Override
    public void run() {
        this.health++;
        System.out.println("i'm running");
    }

    public void walking(Dunno dunno) {
        this.exersize(dunno);
    }

    public void exersize(Dunno dunno) {
        Garden garden = new Garden();
        this.health++;
        dunno.setHappyCount(dunno.getHappyCount()+1);
        this.setLocation(garden);
        dunno.setLocation(garden);
        this.run();
        dunno.run();
    }

    public void jump(Gardener gardener, Garden.Bush bush) {
        this.health++;
        System.out.println("i'm jumping");
        gardener.setHappyCount(gardener.getHappyCount()-1);
        bush.setClean(bush.getClean()-1);
    }


}
