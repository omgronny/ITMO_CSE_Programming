package animals;

public class StandartDog extends Dogs{

    private String name;
    private DogTypes type;

    StandartDog(String name) {
        super(DogTypes.STANDART);
        this.name = name;
    }

    public void lunch() {
        System.out.println("i eat in the street");
    }

    public DogTypes getType() {
        return type;
    }

}
