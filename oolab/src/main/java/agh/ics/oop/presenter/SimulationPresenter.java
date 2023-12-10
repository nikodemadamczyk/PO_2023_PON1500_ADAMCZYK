package agh.ics.oop.presenter;

import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter {
    private WorldMap worldMap;

    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
        drawMap();
    }

    public void drawMap() {
        // Tutaj implementacja tłumaczenia mapy na postać siatki kontrolek
        // Na razie ustawiamy zawartość mapy jako tekst
        if (worldMap != null) {
            infoLabel.setText(worldMap.toString());
        } else {
            infoLabel.setText("Mapa nie została jeszcze zainicjowana.");
        }
    }

    // Metoda wywoływana, gdy mapa się zmienia
    public void mapChanged() {
        drawMap();
    }
}
