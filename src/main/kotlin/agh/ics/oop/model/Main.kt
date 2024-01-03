package agh.ics.oop.model

import agh.ics.oop.model.Animal
import agh.ics.oop.model.BouncyMap
import agh.ics.oop.model.Vector2d

fun main(args: Array<String>) {
    // Define the size of your map
    val mapWidth = 5
    val mapHeight = 5
    val bouncyMap = BouncyMap(mapWidth, mapHeight)

    // Define initial positions for animals, for example:
    val initialPositions1 = listOf(Vector2d(2, 2), Vector2d(3, 3)) // You can add more

    // Convert arguments to MoveDirection
    val moves = args.map {
        when (it.lowercase()) {
            "f" -> MoveDirection.FORWARD
            "b" -> MoveDirection.BACKWARD
            "r" -> MoveDirection.RIGHT
            "l" -> MoveDirection.LEFT
            else -> throw IllegalArgumentException("Invalid move direction: $it")
        }
    }

    // Create and run the simulation
    val simulation = Simulation(initialPositions1, moves, bouncyMap)
    simulation.run()

}
