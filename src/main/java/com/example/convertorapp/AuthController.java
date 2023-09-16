package com.example.convertorapp;

import com.example.convertorapp.DB.DB_Authentication;
import com.example.convertorapp.animation.Shake;
import com.example.convertorapp.password.Password;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController {

    @FXML
    private Label converterLabel;
    @FXML
    private Label auth_label;

    @FXML
    private Button enter_btn;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Hyperlink reg_link;

    @FXML
    void initialize() {
        login_field.setText("Admin");
        password_field.setText("12345");
        enter_btn.setText("Войти");
        enter_btn.setOnAction(event -> goNextPage(login_field.getText(), password_field.getText()));
        reg_link.setOnAction(event -> {
            new HelloApplication().nextPage(enter_btn.getScene().getWindow(), "reg.fxml");
        });
    }

    private void goNextPage(String login, String password) {
        if (validation(login, password)) {
            LkController.email = DB_Authentication.getEmail(login, password);
            new HelloApplication().nextPage(enter_btn.getScene().getWindow(), "converterMain.fxml");
        }
    }

    private boolean validation(String login, String password) {
        boolean validation = false;
        Shake shake = new Shake(enter_btn);
        if (!DB_Authentication.isExist(login)) {
            converterLabel.setStyle("-fx-text-fill: red;" +
                    "-fx-font-size: 12;");
            converterLabel.setText("Такой пользователь не существует");
            login_field.setStyle("-fx-border-color: red");
            shake.playAnimation();
        } else if (!DB_Authentication.authentication(login, password)) {
            converterLabel.setText("Неверный логин или пароль");
            converterLabel.setStyle("-fx-text-fill: red;" +
                    "-fx-font-size: 14");
            auth_label.setText("Неверный ввод");
            auth_label.setStyle("-fx-text-fill: red;");
            new Shake(enter_btn).playAnimation();
        } else {
            validation = true;
        }
        return validation;
    }

}
