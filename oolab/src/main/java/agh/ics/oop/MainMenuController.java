package agh.ics.oop;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class MainMenuController {

    @FXML
    private TextField mapHeightField, maxWidthField, jungleWidthField, jungleHeightField;
    @FXML
    private TextField grassEnergyProfitField, minEnergyCopulationField, animalStartEnergyField, dailyEnergyCostField;
    @FXML
    private TextField animalsSpawningStartField, grassSpawnedEachDayField, realRefreshTimeField;

    @FXML
    private void handleSubmitButtonAction() {
        if (validateInput()) {
            // Tutaj możesz dodać logikę przetwarzania danych
            System.out.println("Wszystkie dane są poprawne!");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd walidacji");
            alert.setHeaderText("Niepoprawne dane wejściowe");
            alert.setContentText("Proszę wprowadzić poprawne liczby w każdym polu.");
            alert.showAndWait();
        }
    }

    private boolean validateInput() {
        return isNumeric(mapHeightField.getText()) && isNumeric(maxWidthField.getText()) &&
                isNumeric(jungleWidthField.getText()) && isNumeric(jungleHeightField.getText()) &&
                isNumeric(grassEnergyProfitField.getText()) && isNumeric(minEnergyCopulationField.getText()) &&
                isNumeric(animalStartEnergyField.getText()) && isNumeric(dailyEnergyCostField.getText()) &&
                isNumeric(animalsSpawningStartField.getText()) && isNumeric(grassSpawnedEachDayField.getText()) &&
                isNumeric(realRefreshTimeField.getText());
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
