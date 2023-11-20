package agh.ics.oop.model;

public class Animal implements WorldElement{
    private MapDirection mapDirection;
    private Vector2d position;
    private Vector2d leftBound = new Vector2d(0, 0);
    private Vector2d rightBound = new Vector2d(4, 4);

    public void setLeftBound(Vector2d newLeftBound) {
        this.leftBound = newLeftBound;
    }

    public void setRightBound(Vector2d newRightBound) {
        this.rightBound = newRightBound;
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setMapDirection(MapDirection mapDirection) {
        this.mapDirection = mapDirection;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.mapDirection = MapDirection.NORTH;
    }

    public Animal(Vector2d position) {
        this.position = position;
        this.mapDirection = MapDirection.NORTH;
    }

    public String toString() {
        return switch (getMapDirection()) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private void moveForward(MoveValidator moveValidator) {
        MapDirection currentDirection = getMapDirection();
        Vector2d newPosition = getPosition().add(currentDirection.toUnitVector());
        try {
            if (moveValidator.canMoveTo(newPosition)) {
                setPosition(newPosition);
            }
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void moveBackward(MoveValidator moveValidator) {
        MapDirection currentDirection = getMapDirection();
        Vector2d newPosition = getPosition().add(currentDirection.toUnitVector().opposite());
        try {
            if (moveValidator.canMoveTo(newPosition)) {
                setPosition(newPosition);
            }
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void move(MoveDirection direction, MoveValidator moveValidator) {
        switch (direction) {
            case RIGHT -> setMapDirection(getMapDirection().next());
            case LEFT -> setMapDirection(getMapDirection().previous());
            case FORWARD -> moveForward(moveValidator);
            case BACKWARD -> moveBackward(moveValidator);
        }
    }
}