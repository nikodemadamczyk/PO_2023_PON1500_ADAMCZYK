package agh.ics.oop.model;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void testPlaceAndObjectAt() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));

        assertTrue(map.place(animal));
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    public void testMove() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));

        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testIsOccupied() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));

        map.place(animal);

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(3, 3)));
    }

    // Add more tests for other methods as needed
}
