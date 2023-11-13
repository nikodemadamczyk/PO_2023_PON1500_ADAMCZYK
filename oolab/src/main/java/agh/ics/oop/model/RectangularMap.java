package agh.ics.oop.model;

import agh.ics.oop.model.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap implements WorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();
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
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!isOccupied(position)) {
            animals.put(position, animal);
            animal.setLeftBound(lowerLeft);
            animal.setRightBound(upperRight);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d newPosition = switch (direction) {
            case FORWARD -> animal.getPosition().add(direction.toUnitVector());
            case BACKWARD -> animal.getPosition().add(direction.toUnitVector());
            default -> animal.getPosition();
        };

        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return (WorldElement) animals.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerLeft) && position.precedes(upperRight)) {
            return !isOccupied(position);
        }
        return false;
    }

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }
}