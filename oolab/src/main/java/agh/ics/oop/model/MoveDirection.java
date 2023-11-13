package agh.ics.oop.model;

public enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;

    public Vector2d toUnitVector() {
        return switch (this) {
            case FORWARD -> new Vector2d(0, 1);
            case BACKWARD -> new Vector2d(0, -1);
            case RIGHT -> new Vector2d(1, 0);
            case LEFT -> new Vector2d(-1, 0);
        };
    }
}
