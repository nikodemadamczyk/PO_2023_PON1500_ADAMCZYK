package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldIntegrationTest {

    @Test
    void canMoveTo_place_with_animal() {
        GrassField field = new GrassField(10);
        try {
            field.place(new Animal(new Vector2d(1,1)));
            assertFalse(field.canMoveTo(new Vector2d(1,1)));
        } catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
    }


    @Test
    void place()  {
        GrassField field = new GrassField(10);
        try {
            assertTrue(field.place(new Animal(new Vector2d(1000,-123))));
            assertFalse(field.place(new Animal(new Vector2d(1000,-123))));
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }

    }

    @Test
    void move() {
        GrassField field = new GrassField(10);
        Animal animal=new Animal(new Vector2d(-100,1323));
        try {
            field.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        field.move(animal,MoveDirection.FORWARD);
        Vector2d expected=new Vector2d(-100,1324);
        assertEquals(expected,animal.getPosition());
    }

    @Test
    void isOccupied() {
        GrassField field = new GrassField(10);
        Animal animal=new Animal(new Vector2d(-10320,-69123));
        try {
            field.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        assertTrue(field.isOccupied(new Vector2d(-10320,-69123)));
    }

    @Test
    void objectAt() {
        GrassField field = new GrassField(10);
        Animal animal = new Animal(new Vector2d(-2030,469123));
        try {
            field.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals(field.objectAt(new Vector2d(-2030,469123)),animal);
    }

    @Test
    void moveAnimalOutsideMapBounds() {
        GrassField field = new GrassField(10);
        Animal animal = new Animal(new Vector2d(0, 0));
        try {
            field.place(animal);
            animal.move(MoveDirection.FORWARD, field); // Próba przemieszczenia się poza mapę
            assertEquals(new Vector2d(0, 1), animal.getPosition()); // Zwierzę powinno się przemieścić
        } catch (PositionAlreadyOccupiedException ex) {
            fail("Unexpected exception: " + ex.getMessage());
        }
    }

    @Test
    void moveAnimalToOccupiedPosition() {
        GrassField field = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            field.place(animal1);
            field.place(animal2);
            animal1.move(MoveDirection.FORWARD, field); // Próba przemieszczenia się na zajętą pozycję
            assertEquals(new Vector2d(2, 2), animal1.getPosition()); // Zwierzę powinno pozostać na miejscu
        } catch (PositionAlreadyOccupiedException ex) {
            fail("Unexpected exception: " + ex.getMessage());
        }
    }

    @Test
    void moveAnimalToGrassPosition() {
        GrassField field = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        try {
            field.place(animal);
            animal.move(MoveDirection.FORWARD, field); // Próba przemieszczenia się na pozycję z trawą
            assertEquals(new Vector2d(2, 3), animal.getPosition()); // Zwierzę powinno się przemieścić
        } catch (PositionAlreadyOccupiedException ex) {
            fail("Unexpected exception: " + ex.getMessage());
        }
    }
}