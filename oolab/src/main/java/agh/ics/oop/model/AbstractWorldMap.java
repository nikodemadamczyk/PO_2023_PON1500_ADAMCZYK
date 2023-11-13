package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractWorldMap implements WorldMap {
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();
    protected Map<Vector2d, Animal> animals;
    public AbstractWorldMap() {
        this.animals = new HashMap<>();
    }
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition(), animal);
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2d lowerLeft = getLowerLeft();
        Vector2d upperRight = getUpperRight();
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}