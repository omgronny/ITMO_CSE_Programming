package databasehelpers;

import collection.Collection;
import collection.Condition;
import collection.StudyGroup;
import parse.ParceCSV;

import java.sql.*;

import static collection.Collection.getUsersMap;

public class DataBaseWorker {

    private final String url = "***";
    private final String user = "***";
    private final String password = "***";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection connect() throws SQLException {

        System.out.println(Collection.getVector());

        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("sos");
        }

        ResultSet rs = statement.executeQuery( "SELECT * FROM studygroup;" );

        ParceCSV parceCSV = new ParceCSV();
        Collection.setVector(parceCSV.addCollectionToVector(rs, Collection.getVector()));

        System.out.println(Collection.getVector());

        ResultSet usersResultSet = statement.executeQuery( "SELECT * FROM users;" );
        Collection.setUsersMap(parceCSV.addUsersToVector(usersResultSet, getUsersMap()));

        System.out.println(getUsersMap());
        System.out.println(Collection.getServerAdmin());

        return conn;

    }

    public void saveToDataBase() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement statement = conn.createStatement();

        statement.executeUpdate("DELETE FROM users;");
        //statement.executeUpdate("DELETE FROM StudyGroup;");

        for (String key : getUsersMap().keySet()) {

            statement.executeUpdate("INSERT INTO users (login, password) VALUES ('" + key + "', '"
                    + getUsersMap().get(key) +"');");

        }

        for (StudyGroup studyGroup : Collection.getVector()) {

            System.out.println(studyGroup.toDataBaseFormat());
            System.out.println(studyGroup.toUpdateFormat());

            if (studyGroup.getCondition().equals(Condition.INSERT)) {

                statement.executeUpdate("INSERT INTO StudyGroup (id,name,xcord,ycord,createdate,count,typeofeducation," +
                        "semester,adminname,height,color,country,adminxcoord,adminycoord,adminzcoord,locationname,userlogin)" +
                        " VALUES (" + studyGroup.toDataBaseFormat() + ");");

            } else if (studyGroup.getCondition().equals(Condition.UPDATE)) {

                statement.executeUpdate("UPDATE StudyGroup SET " + studyGroup.toUpdateFormat()
                        + " WHERE id = " + studyGroup.getId() + ";");

            } else if (studyGroup.getCondition().equals(Condition.DELETE)) {

                statement.executeUpdate("DELETE FROM StudyGroup WHERE id = " + studyGroup.getId() +";");

            }

        }

        statement.close();
        conn.close();


    }


}
