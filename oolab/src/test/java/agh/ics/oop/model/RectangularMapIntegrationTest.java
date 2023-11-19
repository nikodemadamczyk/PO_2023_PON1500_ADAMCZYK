package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapIntegrationTest {
    private RectangularMap map;

    @BeforeEach
    void setUp() {
        map = new RectangularMap(5, 5);
    }

    @Test
    void testCanMoveTo() {
        assertTrue(map.canMoveTo(new Vector2d(3, 3)));
        assertFalse(map.canMoveTo(new Vector2d(6, 6))); // Poza granicami mapy
    }

    @Test
    void testPlaceAndIsOccupied() {
        Animal animal = new Animal(new Vector2d(2, 2));
        assertTrue(map.place(animal));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void testObjectAt() {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
        assertNull(map.objectAt(new Vector2d(3, 3))); // Brak obiektu na tej pozycji
    }
    @Test
    void testGetElements() {
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(2, 2));
        map.place(animal1);
        map.place(animal2);
        assertEquals(2, map.getElements().size());
    }

    @Test
    void testMove() {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertNotNull(map.objectAt(new Vector2d(2, 3)));
    }
}
