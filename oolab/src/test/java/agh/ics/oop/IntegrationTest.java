//package agh.ics.oop;
//
//import agh.ics.oop.Simulation;
//import agh.ics.oop.model.*;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class IntegrationTest{
//
//    @Test
//    public void testSimulationSingleAnimal() {
//        List<MoveDirection> directions = List.of(
//                MoveDirection.FORWARD, MoveDirection.RIGHT,
//                MoveDirection.FORWARD, MoveDirection.FORWARD,
//                MoveDirection.LEFT, MoveDirection.FORWARD
//        );
//
//        List<Vector2d> positions = List.of(new Vector2d(2, 2));
//        WorldMap map = new RectangularMap(5, 5);
//        Simulation simulation = new Simulation(map, directions, positions);
//        simulation.run();
//
//        assertEquals(new Vector2d(4, 4), map.objectAt(new Vector2d(4, 4)).getPosition());
//        assertEquals(MapDirection.NORTH, map.objectAt(new Vector2d(4, 4)).getOrientation());
//    }
//
//    @Test
//    public void testSimulationMultipleAnimals() {
//        List<MoveDirection> directions = List.of(
//                MoveDirection.FORWARD, MoveDirection.RIGHT,
//                MoveDirection.FORWARD, MoveDirection.FORWARD
//        );
//
//        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
//        WorldMap map = new RectangularMap(5, 5);
//        Simulation simulation = new Simulation(map, directions, positions);
//        simulation.run();
//
//        assertEquals(new Vector2d(2, 4), map.objectAt(new Vector2d(2, 4)).getPosition());
//        assertEquals(MapDirection.NORTH, map.objectAt(new Vector2d(2, 4)).getOrientation());
//
//        assertEquals(new Vector2d(4, 3), map.objectAt(new Vector2d(4, 3)).getPosition());
//        assertEquals(MapDirection.EAST, map.objectAt(new Vector2d(4, 3)).getOrientation());
//    }
//
//    @Test
//    public void testSimulationWithCollision() {
//
//        List<MoveDirection> directions = List.of(
//                MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT,
//                MoveDirection.RIGHT, MoveDirection.BACKWARD
//        );
//        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
//        WorldMap map = new RectangularMap(5, 5);
//        Simulation simulation = new Simulation(map, directions, positions);
//        simulation.run();
//
//        assertEquals(new Vector2d(2, 3), map.objectAt(new Vector2d(2, 3)).getPosition());
//        assertEquals(MapDirection.WEST, map.objectAt(new Vector2d(2, 3)).getOrientation());
//
//        assertEquals(new Vector2d(3, 3), map.objectAt(new Vector2d(3, 3)).getPosition());
//        assertEquals(MapDirection.SOUTH, map.objectAt(new Vector2d(3, 3)).getOrientation());
//    }
//
//    @Test
//    public void testSimulationBlockedMovement() {
//        List<MoveDirection> directions = List.of(
//                MoveDirection.FORWARD, MoveDirection.RIGHT,
//                MoveDirection.FORWARD, MoveDirection.FORWARD
//        );
//
//        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
//        WorldMap map = new RectangularMap(4, 4);
//        Simulation simulation = new Simulation(map, directions, positions);
//        simulation.run();
//        assertEquals(new Vector2d(2, 3), map.objectAt(new Vector2d(2, 3)).getPosition());
//        assertEquals(MapDirection.NORTH, map.objectAt(new Vector2d(2, 3)).getOrientation());
//
//        assertEquals(new Vector2d(3, 3), map.objectAt(new Vector2d(3, 3)).getPosition());
//        assertEquals(MapDirection.EAST, map.objectAt(new Vector2d(3, 3)).getOrientation());
//    }
//}
