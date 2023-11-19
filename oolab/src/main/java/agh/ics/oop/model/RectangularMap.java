package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;
    private Vector2d lowerLeft;
    private Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(this.width, this.height);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);
    }

    @Override
    protected Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    protected Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) throws PositionAlreadyOccupiedException {
        if (position.follows(lowerLeft) && position.precedes(upperRight)) {
            if (!isOccupied(position)) {
                return true;
            } else {
                throw new PositionAlreadyOccupiedException(position);
            }
        }
        return false;
    }



}
