package agh.ics.oop.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import agh.ics.oop.*;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    public TextField textField;
    @FXML
    public Label moveInfoLabel;
    @FXML
    public Button startButton;
    @FXML
    public GridPane mapGrid;

    private WorldMap worldMap;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            GridMapDrawer gridMapDrawer = new GridMapDrawer(mapGrid, worldMap);
            gridMapDrawer.draw();
            moveInfoLabel.setText(message);
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        try {
            createNewSimulationWindow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createNewSimulationWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane newSimulationRoot = loader.load();
        SimulationPresenter newPresenter = loader.getController();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(newSimulationRoot));
        newStage.setTitle("New Simulation");
        newStage.show();

        // Inicjalizacja nowej symulacji
        GrassField newGrassField = new GrassField(10);
        newGrassField.addObserver(newPresenter);
        newPresenter.setWorldMap(newGrassField);

        // Uruchomienie symulacji
        String[] options = textField.getText().split(" ");
        List<Vector2d> initialPositions = List.of(new Vector2d(-3, 5), new Vector2d(3, 4));
        Simulation newSimulation = new Simulation(initialPositions, OptionsParser.parse(options), newGrassField);
        SimulationEngine newSimulationEngine = new SimulationEngine(List.of(newSimulation));
        newSimulationEngine.runAsync();
    }

    private void startSimulation() {

        GrassField map = new GrassField(10);
        map.addObserver(this);
        map.addObserver((worldMap, message) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    System.out.println(LocalDateTime.now().format(formatter) + " " + message);
                }
        );
        String[] options = textField.getText().split(" ");
        List<Vector2d> initialPositions = List.of(new Vector2d(-3, 5), new Vector2d(3, 4));
        Simulation simulation = new Simulation(initialPositions, OptionsParser.parse(options), worldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runAsync();
        Platform.runLater(() -> startButton.setDisable(true));
    }

    private static List<MoveDirection> tryToParseOptions(String[] options) {
        try {
            return OptionsParser.parse(options);
        } catch (IllegalArgumentException e) {
                Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid moves");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            });
            throw e;
        }
    }

}
