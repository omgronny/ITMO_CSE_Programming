package collection;

import parse.Parce;

public class InterfaceGroup {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null

    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null

    private String adminName;
    private Long height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Country nationality; //Поле не может быть null

    private String creator;

    private Condition condition;

    public InterfaceGroup() { }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public InterfaceGroup(int id, String name, Coordinates coordinates, Long studentsCount,
                          FormOfEducation formOfEducation, Semester semesterEnum, String adminName,
                          Long height, Color eyeColor, Country nationality, String creator, Condition condition) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.adminName = adminName;
        this.height = height;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
        this.creator = creator;
        this.condition = condition;

    }


    @Override
    public String toString() {
        return (id + " " + Parce.nonSharpString(name) + " " + coordinates.getX() + " " + coordinates.getY() + " "
                + studentsCount + " " + formOfEducation + " "
                + semesterEnum + " " + adminName + " " + height + " " + eyeColor + " " + nationality + " " +
                creator + " " + condition);
    }

    public String shower() {

        return ("id: " + id + "\n \n name: " + Parce.nonSharpString(name) + "\n \n coordinates: " + coordinates.getX() +
                " " + coordinates.getY()
                + "\n \n students count: " + studentsCount + "\n \n  formOfEducation: " + formOfEducation + "\n \n semester: "
                + semesterEnum + "\n \n name of creator " +
                creator);


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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


}
