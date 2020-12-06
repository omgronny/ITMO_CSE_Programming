package heroes;

import locations.RichHome;

public class Minoga extends Hero {

    private int money;

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }

    public Minoga(String name, int age, int money) {
        super(name, age);
        this.money = money;
    }

    @Override
    public boolean isFriend(Hero human) { return false; }

    @Override
    public void locationis() {
        RichHome rh = new RichHome();
        System.out.print("i'm live in ");
        rh.thisLocationIs();
    }

    public void getMoneyToDunno(Dunno d, int x) {
        if (money > x) {
            System.out.println(this.getName() + ": Well, okay");
            money -= x;
            d.setMoney(d.getMoney() + x);
        } else {
            System.out.println(this.getName() + ": No! you shouldn't walk with some goat");
        }

    }



}
