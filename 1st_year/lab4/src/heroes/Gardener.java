package heroes;

import locations.Garden;
import locations.Location;

public class Gardener extends Hero{

    public Gardener(String name, int age) {
        super(name,age,new Garden());
    }

    @Override
    public boolean isFriend(Hero human) {
        return true;
    }

    public void monitor(Garden.Bush bush, Garden.FlowerBed flowerBed) {
        bush.setBeautiful(bush.getBeautiful()+1);
        flowerBed.setBeautiful(flowerBed.getBeautiful()+1);

        bush.setClean(bush.getClean()+1);
        flowerBed.setClean(flowerBed.getClean()+1);
    }


}
