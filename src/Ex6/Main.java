package Ex6;

import java.sql.*;

import static Ex6.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {

        registerDriver();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            deleteTable(statement);
            createTable(statement);
            insertTable(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            selectFromTable(connection, "East");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
