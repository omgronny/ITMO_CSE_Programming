package collection;

/**
 * class for contains the location
 */

public class Location {
    private Double x; //Поле не может быть null
    private Integer y; //Поле не может быть null
    private Integer z; //Поле не может быть null
    private String name; //Поле может быть null

    /**
     * Constructor for initialization fields of location
     * @param x Double parameter for x-coordinate
     * @param y Integer parameter for y-coordinate
     * @param z Integer parameter for z-coordinate
     * @param name String name of location
     */

    public Location(Double x, Integer y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
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

    /**
     * getter for z-coordinate
     * @return  Integer parameter for z-coordinate
     */

    public Integer getZ() {
        return z;
    }

    /**
     * setter for z-coordinate
     * @param z Integer parameter for z-coordinate
     */

    public void setZ(Integer z) {
        this.z = z;
    }

    /**
     * getter for name
     * @return  String name
     */

    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name String name
     */

    public void setName(String name) {
        this.name = name;
    }
}