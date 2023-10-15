package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
//        run(args);
        MoveDirection[] directions = OptionsParser.parse(args);
        run(directions);
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
