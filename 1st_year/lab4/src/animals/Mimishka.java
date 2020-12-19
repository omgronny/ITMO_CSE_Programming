package animals;

public class Mimishka extends DecentDog {

    public Mimishka(String name, int mood) {
        super(name, mood);
    }

    public void wagTheTail() {
        this.setMood(this.getMood()+1);
        System.out.println(this.getName() + ": *wagging the tail*");
    }



}
