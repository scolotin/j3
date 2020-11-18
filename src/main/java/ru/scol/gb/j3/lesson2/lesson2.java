package ru.scol.gb.j3.lesson2;

import java.sql.*;

public class lesson2 {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();

            String name = "Students";
            String[] fields = { "StudId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" ,
                                "Name TEXT NOT NULL",
                                "GroupName TEXT NOT NULL",
                                "Score INTEGER NOT NULL" };
            createTable(name, fields);

            String[] appendFields = {"Name", "GroupName", "Score"};
            String[] values = {"Bob", "Tbz11", "80"};
            append(name, appendFields, values);

            ResultSet resultSet = getRecord(name, "*");
            while (resultSet.next()) {
                System.out.printf("%s %s %s %s\n", resultSet.getString("StudId"),
                                                                resultSet.getString("Name"),
                                                                resultSet.getString("GroupName"),
                                                                resultSet.getString("Score"));
            }

            removeRecord(name, "StudId", "13");

            deleteTable(name);

            disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        statement = connection.createStatement();
    }

    private static void disconnect() throws SQLException {
        connection.close();
    }

    private static int createTable(String nameTable, String[] fieldsTable) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("CREATE TABLE IF NOT EXISTS %s (", nameTable));
        for (int i = 0; i < fieldsTable.length; i++) {
            sql.append(fieldsTable[i]);
            if (i != fieldsTable.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");

        return statement.executeUpdate(sql.toString());
    }

    private static int append(String nameTable, String[] fieldsTable, String[] values) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("INSERT INTO %s (", nameTable));
        for (int i = 0; i < fieldsTable.length; i++) {
            sql.append(fieldsTable[i]);
            if (i != fieldsTable.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(") VALUES (");
        for (int i = 0; i < values.length; i++) {
            sql.append(String.format("\"%s\"", values[i]));
            if (i != values.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");

        return statement.executeUpdate(sql.toString());
    }

    private static ResultSet getRecord(String nameTable, String field) throws SQLException {
        return statement.executeQuery(String.format("SELECT %s FROM %s;", field, nameTable));
    }

    private static int removeRecord(String nameTable, String field, String value) throws SQLException {
        return statement.executeUpdate(String.format("DELETE FROM %s WHERE %s=%s", nameTable, field, value));
    }

    private static int deleteTable(String nameTable) throws SQLException {
        return statement.executeUpdate("DROP TABLE " + nameTable);
    }
}
