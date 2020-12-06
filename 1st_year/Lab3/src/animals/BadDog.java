package animals;

import locations.Locatable;

public class BadDog extends Dogs implements Dinnable{

    public BadDog(String name) {
        super(DogTypes.BAD, name);
    }

    public BadDog() { }

    public void lunch() {
        System.out.println("i eat in the dirty street");
    }


}
