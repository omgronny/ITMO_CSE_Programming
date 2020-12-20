package heroes;

import exceptions.AlreadyCuredException;

public class Doctor extends Hero {

    private boolean isPlease = false;

    public boolean getIsPlease() {
        return isPlease;
    }

    public void setIsPlease(boolean please) {
        isPlease = please;
    }

    @Override
    public boolean isFriend(Hero human) {
        return true;
    }

    public void heal(Goat goat) throws AlreadyCuredException {

        if (goat.isSick()) {
            throw new AlreadyCuredException();
        }
        if(isPlease) {
            goat.setSick(false);
        }
    }
}
