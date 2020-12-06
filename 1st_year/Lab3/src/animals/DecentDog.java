package animals;

import locations.Locatable;
import locations.RichHome;

public class DecentDog extends Dogs implements Dinnable, Locatable {


    public DecentDog(String name) {
        super(DogTypes.DECENT,name);
    }

    public DecentDog(){}

    public void lunch() {
        System.out.println("i eat in RichHohe");
    }

    public void locationis() {
        RichHome rh = new RichHome();
        System.out.println(this.getName() + ": i'm live in");
        rh.thisLocationIs();
    }

}
