package collection;

import java.io.Serializable;

/**
 * class for contains the coordinates
 */

public class Coordinates implements Serializable {
    private Double x; //Максимальное значение поля: 866, Поле не может быть null
    private Integer y; //Поле не может быть null

    /**
     * Constructor for initialization x and y coordinates
     * @param x Double parameter for x-coordinate
     * @param y Integer parameter for y-coordinate
     */

    public Coordinates(Double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Double x) {
        this.x = x;
    }

    public Coordinates(Integer y) {
        this.y = y;
    }

    /**
     * Standard empty constructor
     */

    public Coordinates() {
    }


    /**
     * getter for x-coordinate
     * @return Double parameter for x-coordinate
     */
    public Double getX() {
        return x;
    }

    /**
     * setter for x-coordinate
     * @param x Double parameter for x-coordinate
     */

    public void setX(Double x) {
        this.x = x;
    }

    /**
     * getter for y-coordinate
     * @return  Integer parameter for y-coordinate
     */

    public Integer getY() {
        return y;
    }

    /**
     * setter for y-coordinate
     * @param y Integer parameter for y-coordinate
     */

    public void setY(Integer y) {
        this.y = y;
    }
}
