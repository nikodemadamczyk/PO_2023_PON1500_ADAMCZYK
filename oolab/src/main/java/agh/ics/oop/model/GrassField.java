package agh.ics.oop.model;

import agh.ics.oop.Grass;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final UUID id = UUID.randomUUID();
    private final int grassCount;
    private final Map<Vector2d, Grass> grassMap;

    private List<MapChangeListener> listeners = new ArrayList<>();

    public GrassField(int grassCount) {
        super();
        this.grassCount = grassCount;
        this.grassMap = new HashMap<>();
        placeGrass();
    }




    @Override
    public Boundary getCurrentBounds() {
        Vector2d lowerLeft = getLowerLeft();
        Vector2d upperRight = getUpperRight();
        return new Boundary(lowerLeft, upperRight);
    }


    private synchronized void placeGrass() {
        Random random = new Random();
        for (int i = 0; i < grassCount; i++) {
            Vector2d grassPos;
            do {
                int x = random.nextInt((int) Math.sqrt(grassCount * 10));
                int y = random.nextInt((int) Math.sqrt(grassCount * 10));
                grassPos = new Vector2d(x, y);
            } while (isOccupied(grassPos));
            Grass grass = new Grass(grassPos);
            grassMap.put(grassPos, grass);
        }
    }


    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        return grassMap.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(grassMap.values());
    }

    @Override
    protected Vector2d getLowerLeft() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Animal animal : animals.values()) {
            minX = Math.min(minX, animal.getPosition().getX());
            minY = Math.min(minY, animal.getPosition().getY());
        }

        for (Vector2d position : grassMap.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
        }

        return new Vector2d(minX, minY);
    }

    @Override
    protected Vector2d getUpperRight() {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Animal animal : animals.values()) {
            maxX = Math.max(maxX, animal.getPosition().getX());
            maxY = Math.max(maxY, animal.getPosition().getY());
        }

        for (Vector2d position : grassMap.keySet()) {
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        return new Vector2d(maxX, maxY);
    }

    @Override
    public synchronized boolean canMoveTo(Vector2d position) throws PositionAlreadyOccupiedException {
        WorldElement object = objectAt(position);
        if (object == null || object instanceof Grass) {
            return true;
        } else {
            throw new PositionAlreadyOccupiedException(position);
        }
    }


    @Override
    public UUID getId() {
        return id;
    }
}