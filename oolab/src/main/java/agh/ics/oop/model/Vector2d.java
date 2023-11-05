package agh.ics.oop.model;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        String str;
        str = "(" + String.valueOf(this.x) + "," + String.valueOf(this.y) + ")";
        return str;
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other){
        int maxX = Math.max(this.x, other.x);
        int maxY = Math.max(this.y, other.y);
        return new Vector2d(maxX, maxY);
    }

    public Vector2d lowerLeft(Vector2d other){
        int minX = Math.min(this.x, other.x);
        int minY = Math.min(this.y, other.y);
        return new Vector2d(minX, minY);
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Vector2d)) {
            return false;
        }

        Vector2d that = (Vector2d) other;

        // Faktyczne porównanie pól x i y obiektów
        return this.x == that.x && this.y == that.y;
    }
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

}
