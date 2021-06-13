package commands;

import java.io.Serializable;

public class ManCommand extends Command implements Serializable {

    public void man(Command command) {

    }







    @Override
    public String execute() {
        return "1";
    }

    //@Override
    public String man() {
        return ("Команда предназначена для вывода инрмации о других команд. Передается один аргумент - название " +
                "соответсвующей команды");
    }


}
