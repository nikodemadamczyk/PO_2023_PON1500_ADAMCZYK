package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions) {
         animals = initializeAnimals(directions, positions);
    }

    private List<Animal> initializeAnimals(List<MoveDirection> directions, List<Vector2d> positions) {
        List<Animal> animals = new ArrayList<>();
        int numAnimals = Math.min(directions.size(), positions.size());

        for (int i = 0; i < numAnimals; i++) {
            Animal animal = new Animal();
            animal.setPosition(positions.get(i));
            List<MoveDirection> animalDirections = new ArrayList<>();
            for (int j = i; j < directions.size(); j += numAnimals) {
                animalDirections.add(directions.get(j));
            }
            animal.setDirections(animalDirections);
            animals.add(animal);
        }

        return animals;
    }


    public void run() {
        int maxMoves = getMaxMoves();
        for (int i = 0; i < maxMoves; i++) {
            for (int j = 0; j < animals.size(); j++) {
                Animal animal = animals.get(j);
                System.out.println("Zwierzę " + j + " " + animal);
                animal.move();
            }
        }
    }

    private int getMaxMoves() {
        int maxMoves = 0;
        for (Animal animal : animals) {
            maxMoves = Math.max(maxMoves, animal.getDirections().size());
        }
        return maxMoves;
    }
}
