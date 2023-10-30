package agh.ics.oop.model;

import java.util.List;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private List<MoveDirection> directions;
    public Animal() {
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }


    public List<MoveDirection> getDirections() {
        return directions;
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                orientation = orientation.next();
                break;
            case LEFT:
                orientation = orientation.previous();
                break;
            case FORWARD:
                Vector2d forwardPosition = position.add(orientation.toUnitVector());
                if (isValidPosition(forwardPosition)) {
                    position = forwardPosition;
                }
                break;
            case BACKWARD:
                Vector2d backwardPosition = position.subtract(orientation.toUnitVector());
                if (isValidPosition(backwardPosition)) {
                    position = backwardPosition;
                }
                break;
        }
    }

    public String toString() {
        return "Pozycja: " + position.toString() + ", Orientacja: " + orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private boolean isValidPosition(Vector2d newPosition) {
        return newPosition.precedes(new Vector2d(4, 4)) && newPosition.follows(new Vector2d(0, 0));
    }
}
