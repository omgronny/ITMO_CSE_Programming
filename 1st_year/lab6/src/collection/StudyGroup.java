package collection;

import collection.FormOfEducation;
import collection.Person;
import parse.Parce;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The StudyGroup class - that exact class that is meant to fill the collection with its objects
 */

public class StudyGroup implements Comparable<StudyGroup>, Serializable {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
                    // Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null,
                                                    // Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null

    /**
     * The empty constructor
     */

    public StudyGroup() { }

    /**
     * full initialization constructor
     * @param id int id of study group
     * @param name String name of study group
     * @param coordinates coordinates of study group
     * @param creationDate creation date of this study group object
     * @param studentsCount int count of students of study group
     * @param formOfEducation  form Of Education - enum
     * @param semesterEnum semester - Enum
     * @param groupAdmin admin of this study group object
     */


    public StudyGroup(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Long studentsCount,
                      FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;

    }

    /**
     * constructor w/o formOfEducation, semesterEnum and groupAdmin
     * @param id int id of study group
     * @param name String name of study group
     * @param coordinates coordinates of study group
     * @param creationDate  creation date of this study group object
     * @param studentsCount int count of students of study group
     */

    public StudyGroup(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Long studentsCount) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Overrided method for compare the objects
     * @param o  comparable parameter
     * @return int result
     */

    @Override
    public int compareTo(StudyGroup o) {
        return (int) (this.getStudentsCount() - o.getStudentsCount());
    }

    @Override
    public String toString() {

        if (formOfEducation != null && semesterEnum != null) {
            return (id + " " + Parce.nonSharpString(name) + " " + coordinates.getX() + " " + coordinates.getY() + " "
                    + creationDate.toString() + " " + studentsCount + " " + formOfEducation + " "
                    + semesterEnum + " " + Parce.nonSharpString(groupAdmin.getName()) + groupAdmin.getEyeColor() + " "
                    + groupAdmin.getHeight() + " " + groupAdmin.getEyeColor() + " " + groupAdmin.getNationality() + " "
                    + groupAdmin.getLocation().getX() + " " + groupAdmin.getLocation().getY() + " "
                    + groupAdmin.getLocation().getZ() + " " + Parce.nonSharpString(groupAdmin.getLocation().getName()));
        } else if (formOfEducation != null) {
            return (id + " " + Parce.nonSharpString(name) + " " + coordinates.getX() + " " + coordinates.getY() + " "
                    + creationDate.toString() + " " + studentsCount + " " + formOfEducation + " "
                    + "  " + " " + Parce.nonSharpString(groupAdmin.getName()) + groupAdmin.getEyeColor() + " "
                    + groupAdmin.getHeight() + " " + groupAdmin.getEyeColor() + " " + groupAdmin.getNationality() + " "
                    + groupAdmin.getLocation().getX() + " " + groupAdmin.getLocation().getY() + " "
                    + groupAdmin.getLocation().getZ() + " " + Parce.nonSharpString(groupAdmin.getLocation().getName()));
        } else {
            return (id + " " + Parce.nonSharpString(name) + " " + coordinates.getX() + " " + coordinates.getY() + " "
                    + creationDate.toString() + " " + studentsCount + " " + "  " + " "
                    + "  " + " " + Parce.nonSharpString(groupAdmin.getName()) + groupAdmin.getEyeColor() + " "
                    + groupAdmin.getHeight() + " " + groupAdmin.getEyeColor() + " " + groupAdmin.getNationality() + " "
                    + groupAdmin.getLocation().getX() + " " + groupAdmin.getLocation().getY() + " "
                    + groupAdmin.getLocation().getZ() + " " + Parce.nonSharpString(groupAdmin.getLocation().getName()));
        }
    }

    public ArrayList<String> getObject() throws NullPointerException {

        ArrayList<String> stringArrayList = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field f : fields) {

            f.setAccessible(true);

            if (f.toString().equals("private collection.Coordinates collection.StudyGroup.coordinates")) {

                Field[] coordinatesFields = this.getCoordinates().getClass().getDeclaredFields();

                for (Field cf : coordinatesFields) {

                    cf.setAccessible(true);

                    try {
                        stringArrayList.add(cf.get(this.getCoordinates()).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            } else if (f.toString().equals("private collection.Person collection.StudyGroup.groupAdmin")) {

                Field[] coordinatesFields = this.getGroupAdmin().getClass().getDeclaredFields();

                for (Field gf : coordinatesFields) {
                    gf.setAccessible(true);

                    if (gf.toString().equals("private collection.Location collection.Person.location")) {

                        Field[] locationFields = this.getGroupAdmin().getLocation().getClass().getDeclaredFields();

                        for (Field lf : locationFields) {
                            lf.setAccessible(true);
                            try {
                                stringArrayList.add(lf.get(this.getGroupAdmin().getLocation()).toString());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {

                        try {
                            stringArrayList.add(gf.get(this.getGroupAdmin()).toString());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }

            } else {

                try {
                    stringArrayList.add(f.get(this).toString());
                } catch (IllegalAccessException | NullPointerException e) {
                    stringArrayList.add("");
                }

            }

        }

        return stringArrayList;
    }

}