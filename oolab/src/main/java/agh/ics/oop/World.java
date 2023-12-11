package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.Arrays;
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
//            Application.launch(SimulationApp.class, args);

            System.out.println("Simulation " + i + " - Update Counter: " + map.getUpdateCounter());
        }

        SimulationEngine engine = new SimulationEngine(simulations);

//         engine.runSync();

        engine.runAsync();

//        engine.runAsyncInThreadPool();

        try {
            engine.awaitSimulationsEnd();
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        }

        System.out.println("Stop");
    }

//    private static List<Simulation> getSimulations(int n) {
//        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
//        List<Simulation> simulations = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            RectangularMap map = new RectangularMap(5, 5);
//            map.addObserver(consoleMapDisplay);
//            Simulation simulation = new Simulation(
//                    tryToParseOptions("f b r l f f r r f f f f f f f".split(" ")),
//                    List.of(new Vector2d(2, 2), new Vector2d(3, 4)),
//                    map
//            );
//            simulations.add(simulation);
//        }
//
//        return simulations;
//    }
    private static List<MoveDirection> tryToParseOptions(String[] options) {
        try {
            return OptionsParser.parse(options);
        } catch (IllegalArgumentException e) {
            System.out.printf("Could not parse options: %s%n", e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
