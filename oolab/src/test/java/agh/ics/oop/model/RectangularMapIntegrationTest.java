package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapIntegrationTest {

    @Test
    void canMoveTo() throws PositionAlreadyOccupiedException {
        RectangularMap map = new RectangularMap(4,4);
        assertFalse(map.canMoveTo(new Vector2d(0,-1)));
    }

    @Test
    void canMoveTo2()  {
        RectangularMap map = new RectangularMap(4,4);
        try {
            map.place(new Animal(new Vector2d(1, 1)));
            assertFalse(map.canMoveTo(new Vector2d(1,1)));
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Test
    void place() {
        RectangularMap map = new RectangularMap(3,3);
        try {
            assertTrue(map.place(new Animal(new Vector2d(1,1))));
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }

    }

    @Test
    void place2() {
        RectangularMap map = new RectangularMap(3,3);
        try {
            assertFalse(map.place(new Animal(new Vector2d(-1, 1))));
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }

    }

    @Test
    void move() {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal=new Animal(new Vector2d(1,1));
        try {
            map.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        map.move(animal,MoveDirection.FORWARD);
        Vector2d expected=new Vector2d(1,2);
        assertEquals(expected,animal.getPosition());
    }
    @Test
    void move2() {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal=new Animal(new Vector2d(1,0));
        try {
            map.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        map.move(animal,MoveDirection.BACKWARD);
        Vector2d expected=new Vector2d(1,0);
        assertEquals(expected,animal.getPosition());
    }

    @Test
    void isOccupied() {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal=new Animal(new Vector2d(1,0));
        try {
            map.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        assertTrue(map.isOccupied(new Vector2d(1,0)));
    }

    @Test
    void isOccupied2() {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal=new Animal(new Vector2d(1,0));
        try {
            map.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        assertFalse(map.isOccupied(new Vector2d(1,1)));
    }
    @Test
    void objectAt() {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal=new Animal(new Vector2d(1,0));
        try {
            map.place(animal);
        }catch (PositionAlreadyOccupiedException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals(map.objectAt(new Vector2d(1,0)),animal);
    }

    @Test
    void moveAnimalOutsideMapBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 0));
        try {
            map.place(animal);
            animal.move(MoveDirection.BACKWARD, map); // Próba przemieszczenia się poza mapę
            assertEquals(new Vector2d(0, 0), animal.getPosition()); // Zwierzę powinno pozostać na miejscu
        } catch (PositionAlreadyOccupiedException ex) {
            fail("Unexpected exception: " + ex.getMessage());
        }
    }

    @Test
    void moveAnimalToOccupiedPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            map.place(animal1);
            map.place(animal2);
            animal1.move(MoveDirection.FORWARD, map); // Próba przemieszczenia się na zajętą pozycję
            assertEquals(new Vector2d(2, 2), animal1.getPosition()); // Zwierzę powinno pozostać na miejscu
        } catch (PositionAlreadyOccupiedException ex) {
            fail("Unexpected exception: " + ex.getMessage());
        }
    }
}