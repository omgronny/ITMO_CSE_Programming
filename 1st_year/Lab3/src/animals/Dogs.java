package animals;

public abstract class  Dogs {

    private DogTypes type;

    Dogs(DogTypes type) {
        this.type = type;
    }

    public abstract void lunch();

    public DogTypes getType() {
        return type;
    }
}

