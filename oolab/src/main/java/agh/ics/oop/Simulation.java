package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> moves;
    private WorldMap map;

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<MoveDirection> getMoves() {
        return moves;
    }

    public WorldMap getMap() {
        return map;
    }

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves,
                      WorldMap map) {
        this.map = map;
        this.moves = moves;
        for (Vector2d initialPosition : initialPositions) {
            Animal animal = new Animal(initialPosition);
            this.animals.add(animal);
        }
    }

    public void run() {
        int n = getAnimals().size();
        for (Animal animal : animals) {
            if (!map.place(animal)) {
                animals.remove(animal);
            }
        }

        System.out.println(map.toString());
        for (int i = 0; i < moves.size(); i++) {
            int animalsInd = i % n;
            map.move(animals.get(animalsInd), moves.get(i));
            System.out.println(map.toString());
        }
    }

    public void run(List<MapDirection> actualDirections,
                    List<Vector2d> actualPositions) {
        int n = getAnimals().size();
        for (Animal animal : animals) {
            if (!map.place(animal)) {
                animals.remove(animal);
            }
        }

        System.out.println(map.toString());
        for (int i = 0; i < moves.size(); i++) {
            int animalsInd = i % n;
            Animal currentAnimal = animals.get(animalsInd);
            map.move(currentAnimal, moves.get(i));
            actualDirections.add(currentAnimal.getMapDirection());
            actualPositions.add(currentAnimal.getPosition());
            System.out.println(map.toString());
        }
    }
}