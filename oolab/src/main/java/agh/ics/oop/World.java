package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        List<Simulation> simulations = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            AbstractWorldMap map = new GrassField(10);
            ConsoleMapDisplay display = new ConsoleMapDisplay();
            map.addObserver(display);
            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
            System.out.println("Simulation " + i + " - Update Counter: " + map.getUpdateCounter());
        }

        SimulationEngine engine = new SimulationEngine(simulations);

//         engine.runSync();

//        engine.runAsync();

        engine.runAsyncInThreadPool();

        try {
            engine.awaitSimulationsEnd();
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        }

        System.out.println("Stop");
    }
}
