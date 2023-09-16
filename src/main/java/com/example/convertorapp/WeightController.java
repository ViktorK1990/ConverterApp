package com.example.convertorapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class WeightController {

    @FXML
    private ChoiceBox<String> choiceWeight;
    ObservableList<String> list = FXCollections.observableArrayList("Тонны", "Килограммы", "Граммы");

    @FXML
    private Label converterLabel;

    @FXML
    private TextField gramField;

    @FXML
    private TextField inputField;

    @FXML
    private TextField kiloField;

    @FXML
    private TextField tonField;

    @FXML
    private Button back_btn;


    @FXML
    void initialize() {
        choiceWeight.setItems(list);
        choiceWeight.setValue("Тонны");

        inputField.setOnKeyReleased(keyEvent -> validation());
        back_btn.setOnAction(Event -> {
            new HelloApplication().nextPage(back_btn.getScene().getWindow(), "converterMain.fxml");
        });
    }


    public void validation() {
        char[] numArray = inputField.getText().toCharArray();
        boolean valid = false;
        for (int i = 0; i < numArray.length; i++) {
            if ((numArray[i] > '0' || numArray[i] < '9') || numArray[i] == '.') {
                valid = true;
            }
        }
        if (valid) {
            convert();
        }
    }

    public void convert() {
        float num = Float.parseFloat(inputField.getText());
        switch (choiceWeight.getValue()) {
            case "Тонны" -> {
                tonField.setText(String.valueOf(num));
                kiloField.setText(String.valueOf(num * 1000));
                gramField.setText(String.valueOf(num * 1000000));
            }
            case "Килограммы" -> {
                kiloField.setText(inputField.getText());
                tonField.setText(String.valueOf(num / 1000));
                gramField.setText(String.valueOf(num * 1000));
            }
            case "Граммы" -> {
                gramField.setText(inputField.getText());
                kiloField.setText(String.valueOf(num / 1000));
                tonField.setText(String.valueOf(num / 1000000));
            }

        }
    }

   /* private void goBack(String window) {
        back_btn.getScene().getWindow().hide();
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

    } */
}
