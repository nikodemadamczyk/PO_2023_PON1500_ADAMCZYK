package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void testEquals(){
        Vector2d vector1 = new Vector2d(2, 4);

        assertTrue(vector1.equals(new Vector2d(2, 4)));
        assertFalse(vector1.equals(new Vector2d(9, 4)));
        assertFalse(vector1.equals(new Vector2d(2, 9)));
        assertFalse(vector1.equals(new Vector2d(9, 9)));
        assertFalse(vector1.equals(new Vector2d(-2, -4)));
    }

    @Test
    public void testToString(){
        Vector2d vector1 = new Vector2d(10, 1);
        Vector2d vector2 = new Vector2d(-2, -9);
        Vector2d vector3 = new Vector2d(0, 0);
        assertEquals(vector1.toString(), "(10,1)");
        assertEquals(vector2.toString(), "(-2,-9)");
        assertEquals(vector3.toString(), "(0,0)");
    }

    @Test
    public void testPrecedes(){
        Vector2d vector1 = new Vector2d(5, 9);

        assertTrue(vector1.precedes(vector1));
        assertTrue(vector1.precedes(new Vector2d(5, 9)));
        assertTrue(vector1.precedes(new Vector2d(5, 10)));
        assertTrue(vector1.precedes(new Vector2d(6, 9)));
        assertTrue(vector1.precedes(new Vector2d(11, 11)));
        assertFalse(vector1.precedes(new Vector2d(1, 9)));
        assertFalse(vector1.precedes(new Vector2d(5, 2)));
        assertFalse(vector1.precedes(new Vector2d(4, 8)));
        assertFalse(vector1.precedes(new Vector2d(-2, -2)));
    }

    @Test
    public void testFollows(){
        Vector2d vector1 = new Vector2d(5, 9);

        assertTrue(vector1.follows(vector1));
        assertTrue(vector1.follows(new Vector2d(5, 9)));
        assertFalse(vector1.follows(new Vector2d(5, 10)));
        assertFalse(vector1.follows(new Vector2d(6, 9)));
        assertFalse(vector1.follows(new Vector2d(11, 11)));
        assertTrue(vector1.follows(new Vector2d(1, 9)));
        assertTrue(vector1.follows(new Vector2d(5, 2)));
        assertTrue(vector1.follows(new Vector2d(4, 8)));
        assertTrue(vector1.follows(new Vector2d(-2, -2)));
    }

    @Test
    public void testFollowsUpperRight(){
        Vector2d vector1 = new Vector2d(5, 9);

        assertEquals(vector1.upperRight(vector1), new Vector2d(5, 9));
        assertEquals(vector1.upperRight(new Vector2d(5, 29)), new Vector2d(5, 29));
        assertEquals(vector1.upperRight(new Vector2d(39, 9)), new Vector2d(39, 9));
        assertEquals(vector1.upperRight(new Vector2d(5, 4)), new Vector2d(5, 9));
        assertEquals(vector1.upperRight(new Vector2d(1, 2)), new Vector2d(5, 9));
        assertEquals(vector1.upperRight(new Vector2d(0, 0)), new Vector2d(5, 9));
        assertEquals(vector1.upperRight(new Vector2d(-600, -440)), new Vector2d(5, 9));
        assertEquals(vector1.upperRight(new Vector2d(600, 440)), new Vector2d(600, 440));
        assertEquals(vector1.upperRight(new Vector2d(10, 10)), new Vector2d(10, 10));
    }
    @Test
    public void testFollowsLowerLeft(){
        Vector2d vector1 = new Vector2d(5, 9);

        assertEquals(vector1.lowerLeft(vector1), new Vector2d(5, 9));
        assertEquals(vector1.lowerLeft(new Vector2d(5, 29)), new Vector2d(5, 9));
        assertEquals(vector1.lowerLeft(new Vector2d(39, 9)), new Vector2d(5, 9));
        assertEquals(vector1.lowerLeft(new Vector2d(5, 4)), new Vector2d(5, 4));
        assertEquals(vector1.lowerLeft(new Vector2d(1, 2)), new Vector2d(1, 2));
        assertEquals(vector1.lowerLeft(new Vector2d(0, 0)), new Vector2d(0, 0));
        assertEquals(vector1.lowerLeft(new Vector2d(-600, -440)), new Vector2d(-600, -440));
        assertEquals(vector1.lowerLeft(new Vector2d(600, 440)), new Vector2d(5, 9));
        assertEquals(vector1.lowerLeft(new Vector2d(10, 10)), new Vector2d(5, 9));
    }

    @Test
    public void testAdd(){
        Vector2d vector1 = new Vector2d(9, 8);

        assertEquals(vector1.add(vector1), new Vector2d(18, 16));
        assertEquals(vector1.add(new Vector2d(-9, -8)), new Vector2d(0, 0));
        assertEquals(vector1.add(new Vector2d(7, 7)), new Vector2d(16, 15));
        assertEquals(vector1.add(new Vector2d(30, 10)), new Vector2d(39, 18));
        assertEquals(vector1.add(new Vector2d(0, 0)), new Vector2d(9, 8));
    }

    @Test
    public void testSubtract(){
        Vector2d vector1 = new Vector2d(9, 8);

        assertEquals(vector1.subtract(vector1), new Vector2d(0, 0));
        assertEquals(vector1.subtract(new Vector2d(-9, -8)), new Vector2d(18, 16));
        assertEquals(vector1.subtract(new Vector2d(7, 7)), new Vector2d(2, 1));
        assertEquals(vector1.subtract(new Vector2d(30, 10)), new Vector2d(-21, -2));
        assertEquals(vector1.subtract(new Vector2d(0, 0)), new Vector2d(9, 8));
    }

    @Test
    public void testOpposite() {
        Vector2d vector1 = new Vector2d(5, 9);
        Vector2d vector2 = new Vector2d(-7, -7);
        Vector2d vector3 = new Vector2d(9, -8);
        Vector2d vector4 = new Vector2d(0, 0);
        assertEquals(vector1.opposite(), new Vector2d(-5, -9));
        assertEquals(vector2.opposite(), new Vector2d(7, 7));
        assertEquals(vector3.opposite(), new Vector2d(-9, 8));
        assertEquals(vector4.opposite(), new Vector2d(0, 0));
        assertEquals(vector4.opposite(), new Vector2d(-0, -0));
    }
}