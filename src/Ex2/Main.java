package Ex2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        registerDriver();

        List<String> queries = new ArrayList<>();
        try {
            Files.lines(Paths.get("C:/Study/IDEA/HomeworkJDBC1/src/Ex2/exercise2.txt")).forEach(queries::add);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queries.get(0));
            System.out.println("********* 1 ********");
            while (resultSet.next()) {
                System.out.print("Room number: " + resultSet.getString("roomnumber") + " | ");
                System.out.print("Room name: " + resultSet.getString("roomname") + " | ");
                System.out.println("Room capacity: " + resultSet.getString("roomcapacity") + ";");
            }
            System.out.println("********* 2 ********");
            resultSet = statement.executeQuery(queries.get(1));
            while (resultSet.next()) {
                System.out.print("Room number: " + resultSet.getString("roomnumber") + " | ");
                System.out.print("Room name: " + resultSet.getString("roomname") + " | ");
                System.out.println("Room capacity: " + resultSet.getString("roomcapacity") + ";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
