package agh.ics.oop.model;

import java.util.List;


public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private List<MoveDirection> directions;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
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

    public void move(MoveDirection direction, MoveValidator moveValidator) {
        switch (direction) {
            case RIGHT:
                orientation = orientation.next();
                break;
            case LEFT:
                orientation = orientation.previous();
                break;
            case FORWARD:
                Vector2d forwardPosition = position.add(orientation.toUnitVector());
                if (moveValidator.canMoveTo(forwardPosition)) {
                    position = forwardPosition;
                }
                break;
            case BACKWARD:
                Vector2d backwardPosition = position.subtract(orientation.toUnitVector());
                if (moveValidator.canMoveTo(backwardPosition)) {
                    position = backwardPosition;
                }
                break;
        }
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
}
