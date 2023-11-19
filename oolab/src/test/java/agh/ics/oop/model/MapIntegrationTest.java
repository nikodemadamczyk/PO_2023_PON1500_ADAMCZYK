package agh.ics.oop.model;



import agh.ics.oop.*;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapIntegrationTest {

    private RectangularMap rectangularMap;
    private GrassField grassField;
    private Animal animal;

    @BeforeEach
    public void setUp() {
        // Initialize maps with some dimensions and grass count for GrassField
        rectangularMap = new RectangularMap(10, 10);
        grassField = new GrassField(10);

        // Initialize an animal with a known position
        animal = new Animal(new Vector2d(2, 2));
    }

    @Test
    public void testCanPlaceAnimal() {
        assertTrue(rectangularMap.place(animal));
        assertTrue(grassField.place(animal));
    }

    @Test
    public void testIsOccupied() {
        rectangularMap.place(animal);
        assertTrue(rectangularMap.isOccupied(new Vector2d(2, 2)));

        grassField.place(animal);
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void testObjectAt() {
        rectangularMap.place(animal);
        assertEquals(animal, rectangularMap.objectAt(new Vector2d(2, 2)));

        grassField.place(animal);
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));
    }


    @Test
    public void testObjectAtReturnsCorrectType() {
        rectangularMap.place(animal);
        assertTrue(rectangularMap.objectAt(new Vector2d(2, 2)) instanceof Animal);

        grassField.place(animal);
        assertTrue(grassField.objectAt(new Vector2d(2, 2)) instanceof Animal);
    }



}
