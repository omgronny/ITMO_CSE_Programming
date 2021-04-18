package commands;

import collection.Collection;
import parse.ParceCSV;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;

public class Register extends Command implements Serializable {

    private String login;
    private String password;

    public Register(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String execute() {

        System.out.println(this.login + " " + this.password);

        if (Collection.getUsersMap().containsKey(this.login)) {

            if (this.password.equals(Collection.getUsersMap().get(this.login))) {
                return "Вы вошли в аккаунт";
            } else {
                return "Неверный пароль";
            }

        } else {

            HashMap<String, String> newMap = Collection.getUsersMap();
            newMap.put(this.login, this.password);
            Collection.setUsersMap(newMap);
            return "Вы зарегистрированы";
            
        }


    }

}
