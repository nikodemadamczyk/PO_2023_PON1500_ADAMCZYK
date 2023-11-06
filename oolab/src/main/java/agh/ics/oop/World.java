package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        int width = 5;
        int height = 5;
        WorldMap map = new RectangularMap(width, height);
        Simulation simulation = new Simulation(map, directions, positions);
        simulation.run();

        System.out.println("Stop");
    }
}
