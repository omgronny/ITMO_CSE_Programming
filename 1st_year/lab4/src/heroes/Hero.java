package heroes;

import food.Dinnerable;
import locations.Locatable;
import locations.Location;

public abstract class  Hero implements Friendable, Locatable, Dinnerable {

    private String name;
    private int age;
    private int happyCount;
    private Location location;


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Hero(String name, int age) {
        this.name = name;
        this.age = age;
        this.happyCount = 5;
    }

    public Hero(String name, int age, Location location) {
        this.name = name;
        this.age = age;
        this.happyCount = 5;
        this.location = location;
    }

    public Hero() { }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHappyCount() {
        return happyCount;
    }

    public void setHappyCount(int happyCount) {
        this.happyCount = happyCount;
    }

    @Override
    public boolean equals(Object o) {
        return ((Hero) o).getClass().equals(this.getClass());
    }

    @Override
    public void locationis() {
        System.out.print(this.getName() + ": i'm in");
        this.getLocation().thisLocationIs();
    }

    @Override
    public void dinner() {
        this.happyCount++;
    }
}
