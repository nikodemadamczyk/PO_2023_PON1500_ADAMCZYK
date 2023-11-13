package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.GrassField;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        WorldMap map = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        System.out.println("Stop");
    }
}






