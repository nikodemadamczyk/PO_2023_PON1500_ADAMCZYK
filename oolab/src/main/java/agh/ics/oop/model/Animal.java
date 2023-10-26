package agh.ics.oop.model;

import java.util.List;

public class Animal
{
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private List<MoveDirection> directions;

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d newPosition) {
        this.position = newPosition;
    }

    public List<MoveDirection> getDirections() {
        return directions;
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

    public String toString()
    {
        return "Pozycja: " + position.toString() + ", Orientacja: " + orientation.toString();
    }

    public void move() {
        if (directions != null && !directions.isEmpty()) {
            MoveDirection nextMove = directions.remove(0);
            switch (nextMove) {
                case FORWARD -> {
                    Vector2d newPosition = this.position.add(orientation.toUnitVector());
                    if (isValidPosition(newPosition)) {
                        this.position = newPosition;
                    }
                }
                case BACKWARD -> {
                    Vector2d newPosition = this.position.subtract(orientation.toUnitVector());
                    if (isValidPosition(newPosition)) {
                        this.position = newPosition;
                    }
                }
                case RIGHT -> this.orientation = this.orientation.next();
                case LEFT -> this.orientation = this.orientation.previous();
            }
        }
    }
    private boolean isValidPosition(Vector2d newPosition) {
        int topBorder = 4;
        int bottomBorder = 0;
        return newPosition.precedes(new Vector2d(topBorder, topBorder)) && newPosition.follows(new Vector2d(bottomBorder, bottomBorder));
    }
}
