package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    protected Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();

    protected MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        this.mapVisualizer = new MapVisualizer(this);
    }

    private int updateCounter = 0;

    public int getUpdateCounter() {
        return updateCounter;
    }
    public void addObserver(MapChangeListener observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    protected void mapChanged(String message) {
        updateCounter++;
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    @Override
    public synchronized boolean place(Animal animal) throws PositionAlreadyOccupiedException {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal placed at " + animal.getPosition());
            return true;
        }
        throw new PositionAlreadyOccupiedException(animal.getPosition());
    }

    @Override
    public synchronized void move(Animal animal, MoveDirection direction) {
        if(animals.containsKey(animal.getPosition())) {
            Vector2d lastPosition = animal.getPosition();
            animals.remove(lastPosition);
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal moved from " + lastPosition + " to " + animal.getPosition());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

//    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary boundary = this.getCurrentBounds();
        return mapVisualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }
}
