package animals;

public class DecentDog extends Dogs{

    private String name;
    private DogTypes type;

    DecentDog(String name) {
        super(DogTypes.DECENT);
        this.name = name;
    }

    public void lunch() {
        System.out.println("i eat in RichHohe");
    }

    public DogTypes getType() {
        return type;
    }

}
