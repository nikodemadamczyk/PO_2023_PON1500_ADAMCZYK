package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import static java.util.Arrays.copyOf;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] directions = new MoveDirection[args.length];
        int index = 0;

        for (String arg : args) {
            switch (arg) {
                case "f":
                    directions[index] = MoveDirection.FORWARD;
                    index++;
                    break;
                case "b":
                    directions[index] = MoveDirection.BACKWARD;
                    index++;
                    break;
                case "l":
                    directions[index] = MoveDirection.LEFT;
                    index++;
                    break;
                case "r":
                    directions[index] = MoveDirection.RIGHT;
                    index++;
                    break;
                default:
                    break;
            }
        }

        return copyOf(directions, index);
    }
}
