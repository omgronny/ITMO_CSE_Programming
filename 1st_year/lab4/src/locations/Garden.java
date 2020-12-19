package locations;

public class Garden extends Location{

    @Override
    public void thisLocationIs() {
        System.out.println("Garden");
    }

    public class Bush {
        private int clean;
        private int beautiful;

        public Bush(int clean, int beautiful) {
            this.clean = clean;
            this.beautiful = beautiful;
        }

        public Bush() { }

        public int getClean() {
            return clean;
        }

        public void setClean(int clean) {
            this.clean = clean;
        }

        public int getBeautiful() {
            return beautiful;
        }

        public void setBeautiful(int beautiful) {
            this.beautiful = beautiful;
        }
    }

    public class FlowerBed {
        private int clean;
        private int beautiful;

        public FlowerBed(int clean, int beautiful) {
            this.clean = clean;
            this.beautiful = beautiful;
        }

        public FlowerBed() { }

        public int getClean() {
            return clean;
        }

        public void setClean(int clean) {
            this.clean = clean;
        }

        public int getBeautiful() {
            return beautiful;
        }

        public void setBeautiful(int beautiful) {
            this.beautiful = beautiful;
        }
    }
}
