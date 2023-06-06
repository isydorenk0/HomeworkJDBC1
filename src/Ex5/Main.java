package Ex5;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    private static final String GETALLEMPL = "SELECT name, phone, home " +
            "FROM contacts " +
            "INNER JOIN family " +
            "ON contacts.id = contact_id";

    private static final String GETALLEMPLSINGL = "SELECT name, phone, dob " +
            "FROM contacts " +
            "INNER JOIN family " +
            "ON contacts.id = contact_id " +
            "WHERE NOT married";

    private static final String GETALLEMPLMANAGER = "SELECT name, phone, home, dob FROM contacts " +
            "INNER JOIN family " +
            "ON contacts.id = family.contact_id " +
            "INNER JOIN work " +
            "ON contacts.id = work.contact_id " +
            "WHERE position = 'manager'";

    public static void main(String[] args) {

        registerDriver();

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            Statement statement = connection.createStatement()) {
            exercise5_1(statement);
            exercise5_2(statement);
            exercise5_3(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void exercise5_1(Statement statement) throws SQLException {
        System.out.println("*******Exercise 1**********");
        ResultSet resultSet = statement.executeQuery(GETALLEMPL);
        while (resultSet.next()) {
            System.out.print("Name: " + resultSet.getString("name") + " | ");
            System.out.print("Phone: " + resultSet.getString("phone") + " | ");
            System.out.println("Address: " + resultSet.getString("home") + ";");
        }
    }

    private static void exercise5_2(Statement statement) throws SQLException {
        System.out.println("*******Exercise 2**********");
        ResultSet resultSet = statement.executeQuery(GETALLEMPLSINGL);
        while (resultSet.next()) {
            System.out.print("Name: " + resultSet.getString("name") + " | ");
            System.out.print("Phone: " + resultSet.getString("phone") + " | ");
            System.out.println("DOB: " + resultSet.getString("dob") + ";");
        }
    }
    private static void exercise5_3(Statement statement) throws SQLException {
        System.out.println("*******Exercise 3**********");
        ResultSet resultSet = statement.executeQuery(GETALLEMPLMANAGER);
        while (resultSet.next()) {
            System.out.print("Name: " + resultSet.getString("name") + " | ");
            System.out.print("Phone: " + resultSet.getString("phone") + " | ");
            System.out.print("Address: " + resultSet.getString("home") + "|");
            System.out.println("DOB: " + resultSet.getString("dob") + ";");
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}