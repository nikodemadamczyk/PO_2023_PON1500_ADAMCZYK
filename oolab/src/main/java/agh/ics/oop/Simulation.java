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
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            try {
                if (!map.place(animal)) {
                    animals.remove(i--);
                }
            } catch (PositionAlreadyOccupiedException e) {
                System.out.println(e.getMessage());
                animals.remove(i--);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(map.toString());
        for (int i = 0; i < moves.size(); i++) {
            int animalsInd = i % n;
            try {
                map.move(animals.get(animalsInd), moves.get(i));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
//            System.out.println(map.toString());
            try {
                Thread.sleep(500); // Opóźnienie 500 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}