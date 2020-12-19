package locations;

import heroes.Hero;

public class Hospital extends Location{
    @Override
    public void thisLocationIs() {
        System.out.println("Hospital");
    }

    public static class ServiceStaff extends Hero {
        private int ambulanceCount;
        Ambulance[] ambulances = new Ambulance[ambulanceCount];

        @Override
        public boolean isFriend(Hero human) {
            return false;
        }

        public ServiceStaff(int ambulanceCount) {
            this.ambulanceCount = ambulanceCount;
            for (Ambulance ambulance : ambulances) {
                ambulance = new Ambulance(10);
            }
        }

        Hero doctor = new Hero() {
            @Override
            public boolean isFriend(Hero human) {
                return false;
            }
        };


    }

    public static class Ambulance {
        private int speed;
        Ambulance(int speed) {
            this.speed = speed;
        }
    }
}
