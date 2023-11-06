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
    private final List<Animal> animals;

    public Simulation(WorldMap map, List<MoveDirection> directions, List<Vector2d> positions) {
        this.map = map;
        animals = initializeAnimals(directions, positions);
    }

    private List<Animal> initializeAnimals(List<MoveDirection> directions, List<Vector2d> positions) {
        List<Animal> animals = new ArrayList<>();
        int numAnimals = positions.size();

        for (int i = 0; i < numAnimals; i++) {
            Animal animal = new Animal(positions.get(i));
            List<MoveDirection> animalDirections = new ArrayList<>();
            for (int j = i; j < directions.size(); j += numAnimals) {
                animalDirections.add(directions.get(j));
            }
            animal.setDirections(animalDirections);
            if (map.place(animal)) {
                animals.add(animal);
            }
        }

        return animals;
    }

    public void run() {
//        System.out.println(map);
        int maxMoves = getMaxMoves() + 1;
        for (int i = 0; i < maxMoves; i++) {
            for (Animal animal : map.getAnimals()) {
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
        for (Animal animal : animals) {
            maxMoves = Math.max(maxMoves, animal.getDirections().size());
        }
        return maxMoves;
    }

}
