package heroes;

import locations.RichHome;

public class Minoga implements Human {

    private String name;
    private int age;
    private int money;

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }


    public Minoga(String name, int age, int money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    @Override
    public boolean isFriend(Human human) { return false; }

    @Override
    public void locationis() {
        RichHome rh = new RichHome();
        System.out.print("i'm live in ");
        rh.thisLocationIs();
    }

    public void getMoneyToDunno(Dunno d, int x) {
        if (money > x) {
            money -= x;
            d.setMoney(d.getMoney() + x);
        } else {
            System.out.println("i have no money");
        }

    }



}
