package commands;

import java.io.InputStreamReader;
import java.io.Serializable;

public class LongCommand extends Command implements Serializable {

    @Override
    public String execute() {
        int a = 1;
        while (a != 0) {
            a++;
        }
        while (a != 0) {
            a++;
        }
        while (a != 0) {
            a++;
        }

        return "Long Command accepted";

    }

}
