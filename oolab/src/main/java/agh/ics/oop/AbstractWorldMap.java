package agh.ics.oop;

import agh.ics.oop.model.*;

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
    public List<Animal> getElements() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition)) {
            // Check if there is grass at the animal's position
            if (elements.containsKey(animalPosition) && elements.get(animalPosition) instanceof Grass) {
                // If there is grass at the animal's position, remove the grass
                elements.remove(animalPosition);
            }

            animals.put(animalPosition, animal);
            elements.put(animalPosition, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d previousPosition = animal.getPosition();
        if (animal.move(direction, this)) {
            Vector2d newPosition = animal.getPosition();
            animals.remove(previousPosition);
            elements.remove(previousPosition);

            // Check if there is grass at the new position
            if (elements.containsKey(newPosition) && elements.get(newPosition) instanceof Grass) {
                // If there is grass at the new position, remove the grass
                elements.remove(newPosition);
            }

            animals.put(newPosition, animal);
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
