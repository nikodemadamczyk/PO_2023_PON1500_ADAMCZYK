package agh.ics.oop.model;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private final List<Grass> grassList;

    public GrassField(int grassCount) {
        super();
        this.grassList = placeGrass(grassCount);
    }

    private List<Grass> placeGrass(int grassCount) {
        List<Grass> grassList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < grassCount; i++) {
            Vector2d randomPosition;
            do {
                int x = rand.nextInt((int) Math.sqrt(grassCount * 10));
                int y = rand.nextInt((int) Math.sqrt(grassCount * 10));
                randomPosition = new Vector2d(x, y);
            } while (isOccupied(randomPosition));

            Grass grass = new Grass(randomPosition);
            grassList.add(grass);
            elements.put(randomPosition, grass);
        }

        return grassList;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
