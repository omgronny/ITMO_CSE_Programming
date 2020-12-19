package animals;

public class Roland extends DecentDog {

    public Roland(String name, int mood) {
        super(name, mood);
    }

    public void smile() {
        this.setMood(getMood()+1);
        System.out.println(this.getName() + ": *smiling*");
    }

}
