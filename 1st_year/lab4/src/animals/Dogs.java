package animals;
;
import locations.*;

public abstract class  Dogs implements Locatable {

    private DogTypes type;
    private String name;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Dogs(DogTypes type, String name) {
        this.type = type;
        this.name = name;
    }

    public Dogs(){ }

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

    @Override
    public void locationis() {
        System.out.print(this.getName() + ": i'm in");
        this.getLocation().thisLocationIs();
    }

}

