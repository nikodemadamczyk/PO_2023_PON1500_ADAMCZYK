package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.LinkedList;


public class OptionsParser {
    public static LinkedList<MoveDirection> parse(String[] args) {
        LinkedList<MoveDirection> directions = new LinkedList<>();

        for (String arg : args) {
            switch (arg) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
            }
        }

        return directions;
    }
}
