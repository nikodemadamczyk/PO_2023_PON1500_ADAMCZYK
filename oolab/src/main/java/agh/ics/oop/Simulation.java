package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.RectangularMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final WorldMap map;

    public Simulation(WorldMap map, List<MoveDirection> directions, List<Vector2d> positions) {
        this.map = map;
        initializeAnimals(directions, positions);
    }

    private void initializeAnimals(List<MoveDirection> directions, List<Vector2d> positions) {
        int numAnimals = positions.size();

        for (int i = 0; i < numAnimals; i++) {
            Vector2d initialPosition = positions.get(i);
            Animal animal = new Animal(initialPosition);
            List<MoveDirection> animalDirections = new ArrayList<>();
            for (int j = i; j < directions.size(); j += numAnimals) {
                animalDirections.add(directions.get(j));
            }
            animal.setDirections(animalDirections);
            map.place(animal);
        }
    }



    public void run() {
        int maxMoves = getMaxMoves() + 1;
        for (int i = 0; i < maxMoves; i++) {
            for (Animal animal : map.getElements()) {
                List<MoveDirection> directions = animal.getDirections();
                if (!directions.isEmpty()) {
                    MoveDirection nextMove = directions.remove(0);
                    map.move(animal, nextMove);
                }
            }
        }
        System.out.println(map);
    }

    private int getMaxMoves() {
        int maxMoves = 0;
        for (Animal animal : map.getElements()) {
            maxMoves = Math.max(maxMoves, animal.getDirections().size());
        }
        return maxMoves;
    }
}
