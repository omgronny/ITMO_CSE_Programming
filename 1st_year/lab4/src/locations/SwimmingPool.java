package locations;

import animals.Roland;
import heroes.Dunno;

public class SwimmingPool extends Location{
    @Override
    public void thisLocationIs() {
        System.out.println("Swimming Pool");
    }

    public class TV {
        boolean isOn;

        public void showTournament(Dunno dunno, Roland roland) {
            dunno.setHappyCount(dunno.getHappyCount()+1);
            roland.smile();
        }
    }

}
