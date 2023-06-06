package Ex6;

import java.sql.*;

public class sql {
    static void selectFromTable(Connection conenction, String roomname) throws SQLException {
        System.out.println("Select all records.");
        String query = "SELECT * FROM office "+
                "WHERE roomname = ?";
        try (PreparedStatement statement = conenction.prepareStatement(query)) {
            statement.setString(1, roomname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.print("Room number: " + resultSet.getString("roomnumber") + " | ");
                System.out.print("Room name: " + resultSet.getString("roomname") + " | ");
                System.out.println("Room capacity: " + resultSet.getString("roomcapacity") + ";");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    static void insertTable(Statement statement) throws SQLException {
        System.out.println("Insert records.");
        statement.addBatch("INSERT INTO office (roomnumber, roomname, roomcapacity) " +
                "VALUES (1, 'east', 20)");
        statement.addBatch("INSERT INTO office (roomnumber, roomname, roomcapacity) " +
                "VALUES (2, 'west', 20)");
        statement.addBatch("INSERT INTO office (roomnumber, roomname, roomcapacity) " +
                "VALUES (3, 'north', 30)");
        statement.addBatch("INSERT INTO office (roomnumber, roomname, roomcapacity) " +
                "VALUES (4, 'south', 40)");
        statement.executeBatch();
    }

    static void createTable(Statement statement) throws SQLException {
        System.out.println("Create table 'office'.");
        String query = "CREATE TABLE office " +
                "(id INT AUTO_INCREMENT NOT NULL, " +
                "roomnumber INT, " +
                "roomname VARCHAR(40), " +
                "roomcapacity INT, " +
                "PRIMARY KEY (id))";
        statement.execute(query);
    }

    static void deleteTable(Statement statement) throws SQLException {
        System.out.println("Delete table 'office' if exists.");
        String query = "DROP TABLE IF EXISTS office";
        statement.execute(query);
    }

    static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
