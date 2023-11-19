package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldIntegrationTest {
    private GrassField map;

    @BeforeEach
    void setUp() {
        map = new GrassField(10); // 10 kęp trawy
    }

    @Test
    void testCanMoveTo() {
        // Testowanie, czy zwierzęta mogą poruszać się na wolne pozycje
        assertTrue(map.canMoveTo(new Vector2d(3, 3)));
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
    }

    @Test
    void testMove() {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertNotNull(map.objectAt(new Vector2d(2, 3)));
    }
}
