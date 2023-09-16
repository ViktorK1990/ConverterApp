package com.example.convertorapp;

import com.example.convertorapp.DB.DB_Authentication;
import com.example.convertorapp.DB.DB_Registration;
import com.example.convertorapp.animation.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LkController {

    public static String email;

    @FXML
    private Label auth_label;

    @FXML
    private Button change_btn;

    @FXML
    private Label converterLabel;

    @FXML
    private TextField email_field;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private PasswordField password_field1;

    @FXML
    void initialize() {
        email_field.setText(email);
        change_btn.setOnAction(event -> {
            if (validation(login_field.getText(), password_field.getText())) {
                changeUserData(login_field.getText(), password_field.getText());
            }
        });
    }

    private void changeUserData(String login, String password) {
        String oldLogin = login;
        String oldPassword = password;

        login_field.setText("");
        password_field.setText("");
        email_field.setText("");
        password_field1.setVisible(true);
        email_field.setDisable(false);
        auth_label.setStyle("-fx-text-fill: black");
        converterLabel.setStyle("-fx-text-fill: black");
        auth_label.setText("Введите новые данные");
        change_btn.setText("Изменить");

        change_btn.setOnAction(event -> {
            if(regValidation(login_field.getText(), password_field.getText(), email_field.getText(), password_field1.getText())) {
                new DB_Registration().changeData(oldLogin, oldPassword, email_field.getText(), login_field.getText(), password_field.getText());
                success();
            }
        });
    }

    private boolean validation(String login, String password) {
        boolean result = true;
        if ((!DB_Authentication.authentication(login, password))) {
            converterLabel.setText("Неверный логин или пароль");
            converterLabel.setStyle("-fx-text-fill: red;" +
                    "-fx-font-size: 14");
            auth_label.setText("Неверный ввод");
            auth_label.setStyle("-fx-text-fill: red;");
            new Shake(change_btn).playAnimation();
            result = false;
        }
        return result;
    }

    private void success() {
        converterLabel.setStyle("-fx-text-fill: black");
        converterLabel.setText("Конвертер");
        auth_label.setStyle("-fx-text-fill: black");
        auth_label.setText("Данные изменены успешно");
        change_btn.setText("На главную");
        change_btn.setOnAction(event -> {
            new HelloApplication().nextPage(auth_label.getScene().getWindow(), "auth.fxml");
        });
    }

    private boolean regValidation(String login, String password, String email, String password2) {
        boolean validation = false;
        Shake shake = new Shake(change_btn);
        if (login.contains("@") || login.contains("%")) {
            converterLabel.setStyle("-fx-font-size: 14;" +
                    "-fx-text-fill: red");
            converterLabel.setText("Логин содержит недопустимые символы");
            login_field.setStyle("-fx-border-color: red");
            shake.playAnimation();
        } else if (DB_Authentication.isExist(login)) {
            login_field.setStyle("-fx-border-color: red");
            converterLabel.setStyle("-fx-font-size: 14;" +
                    "-fx-text-fill: red");
            converterLabel.setText("Такой пользователь существует");
            shake.playAnimation();
        } else if (password.length() < 5) {
            password_field.setStyle("-fx-border-color: red");
            converterLabel.setStyle("-fx-font-size: 14;" +
                    "-fx-text-fill: red");
            converterLabel.setText("Пароль менее 5 символов");
            shake.playAnimation();
        } else if (!email.contains("@") || !email.contains(".") || email.isEmpty()) {
            email_field.setStyle("-fx-border-color: red");
            converterLabel.setStyle("-fx-font-size: 14;" +
                    "-fx-text-fill: red");
            converterLabel.setText("Некорректный email");
            shake.playAnimation();
        } else if (!password.equals(password2)) {
            password_field.setStyle("-fx-border-color: red");
            password_field1.setStyle("-fx-border-color: red");
            converterLabel.setStyle("-fx-font-size: 14;" +
                    "-fx-text-fill: red");
            converterLabel.setText("Вы ввели разные пароли");
            shake.playAnimation();
        } else {
            validation = true;
        }
        return validation;
    }
}
