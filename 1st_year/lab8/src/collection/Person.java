package collection;

import collection.*;
import commands.*;
import parse.*;

import java.io.Serializable;

public class Person extends StudyGroup implements Serializable {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    /**
     * Constructor for initialization a Person
     * @param name Name of Person
     * @param height height of Person
     * @param eyeColor enum. Eye's Color of Person
     * @param nationality nationality of Person
     * @param location location of Person
     */

    public Person(String name, Long height, Color eyeColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
        this.location = location;
    }

    /**
     * The empty constructor
     */

    public Person() { }

    /**
     * method that get the Name of Person
     * @return Name of Person
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * method that get the height of Person
     * @return height of Person
     */

    public Long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    /**
     * method that get the eye color of Person
     * @return eye color of Person
     */

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * method that get the nationality of Person
     * @return  nationality of Person
     */

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * method that get the Location of Person
     * @return Location of Person
     */

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}