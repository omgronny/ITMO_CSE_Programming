package heroes;

import locations.NighHome;

import java.util.Objects;

public class Goat extends Hero {

    private boolean isSick;
    private int foodCount;

    public boolean isSick() { return isSick; }

    public void setSick(boolean sick) { isSick = sick; }

    public int getFoodCount() { return foodCount; }

    public void setFoodCount(int foodCount) { this.foodCount = foodCount; }

    public Goat(String name, int age) {
        super(name, age);
        this.isSick = Math.random() > 0.5;
        this.foodCount = 0;
    }

    public Goat() { }

    @Override
    public boolean isFriend(Hero human) {
        Dunno dun = new Dunno();
        return human.getClass() == dun.getClass();
    }


}
