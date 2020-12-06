package animals;

public class StandartDog extends Dogs implements Dinnable{

    StandartDog(String name) {
        super(DogTypes.STANDART, name);
    }

    public void lunch() {
        System.out.println("i eat in the street");
    }


}
