package com.example.convertorapp;

import com.example.convertorapp.DB.DB_Authentication;
import com.example.convertorapp.DB.DB_Registration;
import com.example.convertorapp.animation.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegController {

    @FXML
    private Label converterLabel;

    @FXML
    private Label reg_text;
    @FXML
    private TextField email_field;

    @FXML
    private Button enter_btn;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private PasswordField password_field1;

    @FXML
    void initialize() {
        converterLabel.setText("Регистрация");
        enter_btn.setOnAction(event -> {
            if (regValidation(login_field.getText(), password_field.getText(), email_field.getText(), password_field1.getText())) {
                new DB_Registration().createUser(login_field.getText(), email_field.getText(), password_field.getText());
                success();
            } else {
                reg_text.setText("Неверный ввод");
            }
        });
    }

    private boolean regValidation(String login, String password, String email, String password2) {
        boolean validation = false;
        Shake shake = new Shake(enter_btn);
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

    private void success() {
        converterLabel.setText("Вы успешно зарегистрировались!");
        converterLabel.setStyle("-fx-font-size: 16;" +
                "-fx-text-fill: black");

        reg_text.setVisible(false);
        login_field.setVisible(false);
        email_field.setVisible(false);
        password_field.setVisible(false);
        password_field1.setVisible(false);

        enter_btn.setText("На главную");
        enter_btn.setOnAction(event -> {
            new HelloApplication().nextPage(enter_btn.getScene().getWindow(), "auth.fxml");
        });
    }

}