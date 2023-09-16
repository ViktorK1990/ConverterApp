package com.example.convertorapp.DB;

import com.example.convertorapp.password.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB_Registration {
    public void createUser (String login, String email, String password) {
        String query = "INSERT INTO users (login, email, password) VALUES (?,?,?)";
        MySQL_Connection mySQLConnection = new MySQL_Connection();
        try {
            PreparedStatement preparedStatement = mySQLConnection.getDBConnection().prepareStatement(query);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3, Password.md5String(password));
            preparedStatement.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeData (String login, String password, String newEmail, String newLogin, String newPassword) {
        String query = "UPDATE users SET login = ? , password = ? , email = ? WHERE login = ? AND password = ?;";
        MySQL_Connection mySQLConnection = new MySQL_Connection();
        try {
            PreparedStatement preparedStatement = mySQLConnection.getDBConnection().prepareStatement(query);
            preparedStatement.setString(1,newLogin);
            preparedStatement.setString(2,Password.md5String(newPassword));
            preparedStatement.setString(3, newEmail);
            preparedStatement.setString(4, login);
            preparedStatement.setString(5, Password.md5String(password));
            preparedStatement.executeUpdate();
            System.out.println("User changed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
