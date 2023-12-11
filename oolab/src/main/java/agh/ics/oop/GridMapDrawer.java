package agh.ics.oop;

import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldElement;
import agh.ics.oop.model.WorldMap;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridMapDrawer {
    private static final int CELL_SIZE = 40;

    private final GridPane mapGrid;
    private final WorldMap worldMap;

    public GridMapDrawer(GridPane mapGrid, WorldMap worldMap) {
        this.mapGrid = mapGrid;
        this.worldMap = worldMap;
    }

    public void draw() {
        clearGrid();
        setGridSize();
        drawElements();
    }

    private void clearGrid() {
        mapGrid.getChildren().clear();
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void setGridSize() {
        // Ustawianie rozmiarów komórek siatki
        for (int i = 0; i < worldMap.getWidth(); i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        for (int i = 0; i < worldMap.getHeight(); i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
    }

    private void drawElements() {
        // Rysowanie elementów na mapie
        for (int x = 0; x < worldMap.getWidth(); x++) {
            for (int y = 0; y < worldMap.getHeight(); y++) {
                Vector2d position = new Vector2d(x, y);
                WorldElement element = worldMap.objectAt(position);
                String labelContent = element != null ? element.toString() : "";
                addLabelToGrid(labelContent, x, y);
            }
        }
    }

    private void addLabelToGrid(String text, int colIndex, int rowIndex) {
        Label label = new Label(text);
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, colIndex, rowIndex);
    }
}
