package animals;

public class BadDog extends Dogs{

    private String name;
    private DogTypes type;

    BadDog(String name) {
        super(DogTypes.BAD);
        this.name = name;
    }

    public void lunch() {
        System.out.println("i eat in the dirty street");
    }

    public DogTypes getType() {
        return type;
    }

}
