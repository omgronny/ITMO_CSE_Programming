package heroes;

import locations.NighHome;

public class Goat implements Human {

    private String name;
    private int age;
    private boolean isSick;
    private int foodCount;

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public boolean isSick() { return isSick; }

    public void setSick(boolean sick) { isSick = sick; }

    public int getFoodCount() { return foodCount; }

    public void setFoodCount(int foodCount) { this.foodCount = foodCount; }

    public Goat(String name, int age) {
        this.name = name;
        this.age = age;
        this.isSick = Math.random() > 0.5;
        this.foodCount = 0;
    }

    public Goat() { }


    @Override
    public boolean isFriend(Human human) {
        Dunno dun = new Dunno();
        return human.getClass() == dun.getClass();
    }

    @Override
    public void locationis() {
        NighHome nh = new NighHome();
        System.out.print("i'm live in ");
        nh.thisLocationIs();
    }


}
