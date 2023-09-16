package com.example.convertorapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Label converterLabel;

    @FXML
    private CheckBox currency_switch;

    @FXML
    private Button start_btn;
    @FXML
    private ImageView lk_btn;

    @FXML
    private CheckBox weight_switch;


    @FXML
    void initialize() {
        currency_switch.setSelected(true);

        currency_switch.setOnAction(event -> {
            currency_switch.setSelected(true);
            weight_switch.setSelected(false);
        });

        weight_switch.setOnAction(event -> {
            weight_switch.setSelected(true);
            currency_switch.setSelected(false);
        });

        start_btn.setOnAction(event -> {
            if (weight_switch.isSelected()) {
                goConvert("converter.fxml");
            } else {
                goConvert("convertCurrency.fxml");
            }
        });

        lk_btn.setOnMouseClicked(mouseEvent -> {
            new HelloApplication().nextPage(lk_btn.getScene().getWindow(), "lk.fxml");
        });
    }

    private void goConvert(String window) {
        start_btn.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(window));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
