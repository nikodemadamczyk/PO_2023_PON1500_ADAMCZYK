package agh.ics.oop.model;



import agh.ics.oop.*;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

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
        try {
            assertTrue(rectangularMap.place(animal));
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(grassField.place(animal));
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsOccupied() {
        try {
            rectangularMap.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(rectangularMap.isOccupied(new Vector2d(2, 2)));

        try {
            grassField.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void testObjectAt() {
        try {
            rectangularMap.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(animal, rectangularMap.objectAt(new Vector2d(2, 2)));

        try {
            grassField.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));
    }


    @Test
    public void testObjectAtReturnsCorrectType() {
        try {
            rectangularMap.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(rectangularMap.objectAt(new Vector2d(2, 2)) instanceof Animal);

        try {
            grassField.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(grassField.objectAt(new Vector2d(2, 2)) instanceof Animal);
    }



}