package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    protected Map<Vector2d, Animal> animals = new HashMap<>();

    public AbstractWorldMap() {
        this.animals = new HashMap<>();
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
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
        return animals.get(position);
    }

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2d lowerLeft = getLowerLeft();
        Vector2d upperRight = getUpperRight();
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }
}
