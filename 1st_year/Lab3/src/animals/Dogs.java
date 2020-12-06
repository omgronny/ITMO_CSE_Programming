package animals;

import locations.Locatable;

public abstract class  Dogs {

    private DogTypes type;
    private String name;

    Dogs(DogTypes type, String name) {
        this.type = type;
        this.name = name;
    }
    Dogs(){ }

    public abstract void lunch();

    public DogTypes getType() { return type; }

    public String getName() {
        return name;
    }

    public void setType(DogTypes type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
}

