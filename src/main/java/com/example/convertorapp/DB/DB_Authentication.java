package com.example.convertorapp.DB;

import com.example.convertorapp.password.Password;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Authentication {
    public static boolean authentication(String login, String password) {
        boolean result = false;
        String query = "SELECT id FROM users WHERE login = '" + login + "' AND password = '" + Password.md5String(password) + "';";
        MySQL_Connection mySQLConnection = new MySQL_Connection();
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            ResultSet res = statement.executeQuery(query);
            result = res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isExist(String login) {
        boolean result = false;
        String query = "SELECT id FROM users WHERE login = '" + login + "';";
        MySQL_Connection mySQLConnection = new MySQL_Connection();
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            ResultSet res = statement.executeQuery(query);
            result = res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getEmail(String login, String password) {
        String query = "SELECT email FROM users WHERE login = '" + login + "' AND password = '" + Password.md5String(password) + "';";
        MySQL_Connection mySQLConnection = new MySQL_Connection();
        String email = "";
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                email = res.getString("email");
            }
            System.out.println(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static void main(String[] args) {
        getEmail("Admin", "12345");
    }
}
