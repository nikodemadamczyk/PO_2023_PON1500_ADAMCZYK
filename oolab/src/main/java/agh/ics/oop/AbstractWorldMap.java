package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals;
    protected final Map<Vector2d, WorldElement> elements;

    public AbstractWorldMap() {
        this.animals = new HashMap<>();
        this.elements = new HashMap<>();
    }

    @Override
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            elements.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d previousPosition = animal.getPosition();
        if (animal.move(direction, this)) {
            animals.remove(previousPosition, animal);
            Vector2d newPosition = animal.getPosition();
            animals.put(newPosition, animal);
            elements.remove(previousPosition);
            elements.put(newPosition, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return elements.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return elements.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(new Vector2d(getWidth() - 1, getHeight() - 1))
                && position.follows(new Vector2d(0, 0))
                && !isOccupied(position);
    }

    protected int getWidth() {
        return (int) Math.sqrt(elements.size() * 10);
    }

    protected int getHeight() {
        return (int) Math.sqrt(elements.size() * 10);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(getWidth() - 1, getHeight() - 1));
    }
}
