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

public class CurrencyController {

    @FXML
    private Button back_btn;

    @FXML
    private ChoiceBox<String> choiceCurrency;
    ObservableList<String> list = FXCollections.observableArrayList("USD", "EURO", "IDR");

    @FXML
    private Label converterLabel;

    @FXML
    private TextField usd_field;

    @FXML
    private TextField inputField;

    @FXML
    private TextField euro_field;

    @FXML
    private TextField idr_field;

    @FXML
    void initialize() {
        choiceCurrency.setItems(list);
        choiceCurrency.setValue("USD");

        inputField.setOnKeyReleased(keyEvent -> validation());
        back_btn.setOnAction(Event -> goBack("converterMain.fxml"));
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
        switch (choiceCurrency.getValue()) {
            case "USD" -> {
                usd_field.setText(String.valueOf(num));
                euro_field.setText(String.valueOf(num / 1.07));
                idr_field.setText(String.valueOf(num * 15351.99));
            }
            case "EURO" -> {
                usd_field.setText(String.valueOf(num * 1.07));
                euro_field.setText(String.valueOf(num));
                idr_field.setText(String.valueOf(num * 16464.21));
            }
            case "IDR" -> {
                usd_field.setText(String.valueOf(num / 15351.99));
                euro_field.setText(String.valueOf(num / 16464.21));
                idr_field.setText(String.valueOf(num));
            }

        }
    }

    private void goBack(String window) {
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

    }

}