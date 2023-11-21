package agh.ics.oop;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        AbstractWorldMap map1 = new GrassField(10);
        AbstractWorldMap map2 = new RectangularMap(10, 10);

        ConsoleMapDisplay display1 = new ConsoleMapDisplay();
        ConsoleMapDisplay display2 = new ConsoleMapDisplay();

        map1.addObserver(display1);
        map2.addObserver(display2);

        Simulation simulation1 = new Simulation(positions, directions, map1);
        Simulation simulation2 = new Simulation(positions, directions, map2);

        List<Simulation> simulations = new ArrayList<>();
        simulations.add(simulation1);
        simulations.add(simulation2);

        SimulationEngine engine = new SimulationEngine(simulations);

        // Uruchomienie symulacji synchronicznie
        // engine.runSync();

        // Uruchomienie symulacji asynchronicznie
        engine.runAsync();

        // Oczekiwanie na zakończenie symulacji
        try {
            engine.awaitSimulationsEnd();
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        }

        System.out.println("Stop");
    }
}
