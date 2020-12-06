package heroes;

import locations.Locatable;

public abstract class  Hero implements Friendable, Locatable {

    private String name;
    private int age;

    public Hero(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Hero() {}

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

    @Override
    public boolean equals(Object o) {
        return ((Hero) o).getClass().equals(this.getClass());
    }
}
