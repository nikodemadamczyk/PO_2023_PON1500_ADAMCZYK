package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {
    @Test
    public void testSimulationWithValidDirectionsAndPositions() {
        String[] inputDirections = {"f", "b", "r", "l", "f", "f", "r", "r"};
        List<MoveDirection> directions = OptionsParser.parse(inputDirections);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        assertTrue(simulation.getAnimals().get(0).isAt(new Vector2d(3, 3)));
        assertEquals("Południe", simulation.getAnimals().get(0).getOrientation().toString());

        assertTrue(simulation.getAnimals().get(1).isAt(new Vector2d(2, 3)));
        assertEquals("Północ", simulation.getAnimals().get(1).getOrientation().toString());
    }

    @Test
    public void testSimulationWithOutOfBoundsMovement() {
        String[] inputDirections = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(inputDirections);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        assertTrue(simulation.getAnimals().get(0).getPosition().precedes(new Vector2d(4, 4)));
        assertTrue(simulation.getAnimals().get(0).getPosition().follows(new Vector2d(0, 0)));
    }

    @Test
    public void testSimulationWithInvalidDirection() {
        String[] inputDirections = {"f", "x", "b", "l", "r", "r"};
        List<MoveDirection> directions = OptionsParser.parse(inputDirections);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        assertTrue(simulation.getAnimals().get(0).isAt(new Vector2d(2, 2)));
        assertEquals("Wschód", simulation.getAnimals().get(0).getOrientation().toString());
    }

    @Test
    public void testSimulationWithEmptyDirections() {
        String[] inputDirections = {};
        List<MoveDirection> directions = OptionsParser.parse(inputDirections);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        assertTrue(simulation.getAnimals().get(0).isAt(new Vector2d(2, 2)));
    }
}
