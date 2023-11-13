package agh.ics.oop.model;

import agh.ics.oop.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GrassField extends AbstractWorldMap {
    private final int grassCount;
    private final List<Grass> grassList;

    public GrassField(int grassCount) {
        super();
        this.grassCount = grassCount;
        this.grassList = new ArrayList<>();
        placeGrass();
    }

    private void placeGrass() {
        for (int i = 0; i < grassCount; i++) {
            Vector2d grassPos;

            do {
                grassPos =
                        new Vector2d((int) (Math.random() * Math.sqrt(grassCount * 10))
                                , (int) (Math.random() * Math.sqrt(grassCount * 10)));
            }   while (isOccupied(grassPos));
            Grass grass = new Grass(grassPos);
            grassList.add(grass);
        }
    }

    public WorldElement objectAt(Vector2d position) {
        for (WorldElement animal : animals.values()) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }

        for (Grass grass : grassList) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }

        return null;
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(grassList);
    }

    protected Vector2d getLowerLeft() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Animal animal : animals.values()) {
            minX = Math.min(minX, animal.getPosition().getX());
            minY = Math.min(minY, animal.getPosition().getY());
        }

        for (Grass grass : grassList) {
            minX = Math.min(minX, grass.getPosition().getX());
            minY = Math.min(minY, grass.getPosition().getY());
        }

        return new Vector2d(minX, minY);
    }

    protected Vector2d getUpperRight() {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Animal animal : animals.values()) {
            maxX = Math.max(maxX, animal.getPosition().getX());
            maxY = Math.max(maxY, animal.getPosition().getY());
        }
        for (Grass grass : grassList) {
            maxX = Math.max(maxX, grass.getPosition().getX());
            maxY = Math.max(maxY, grass.getPosition().getY());
        }
        return new Vector2d(maxX, maxY);
    }

    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position)) {
            return true;
        }
        else return objectAt(position).getClass() != Animal.class;
    }
}