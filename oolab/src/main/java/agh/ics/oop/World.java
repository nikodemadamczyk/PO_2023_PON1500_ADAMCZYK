package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
//        run(args);
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(MapDirection.NORTH.previous());
        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.NORTH.toUnitVector());
//        -lab1
//        MoveDirection[] directions = OptionsParser.parse(args);
//        run(directions);
        System.out.println("Stop");
    }

    public static void run(MoveDirection[] directions){
        String message;
        for (MoveDirection arg : directions) {
            message = switch (arg) {
                case FORWARD -> "idzie do przodu";
                case BACKWARD -> "idzie do tyłu";
                case LEFT -> "skręca w lewo";
                case RIGHT -> "skręca w prawo";
                default -> "Error";
            };
            if (message.equals("Error")){
                System.out.print("");
            }
            else{
                System.out.println("Zwierzak " + message);
            }
        }
    }

//    public static void run(String[] args){
//        String message;
//        for (int i = 0; i < args.length; i++){
//            message = switch (args[i]) {
//                case "f" -> "idzie do przodu";
//                case "b" -> "idzie do tyłu";
//                case "r" -> "skręca w prawo";
//                case "l" -> "skręca w lewo";
//                default -> "Error";
//            };
//            if (message.equals("Error")){
//                System.out.print("");
//            }
//            else{
//                System.out.println("Zwierzak " + message);
//            }
//        }
//    }

//    public static void run(String[] args){
//        System.out.println("Idę do przodu!");
//        for (int i = 0; i < args.length; i++) {
//            System.out.print(args[i]);
//            if (i < args.length - 1) {
//                System.out.print(", ");
//            }
//        }
//        System.out.println();
//    }
}
