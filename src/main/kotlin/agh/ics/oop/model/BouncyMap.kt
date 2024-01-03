package agh.ics.oop.model

import agh.ics.oop.model.randomFreePosition
class BouncyMap(private val width: Int, private val height: Int) : IWorldMap {
    private val animals = mutableMapOf<Vector2d, Animal>()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0 until width && position.y in 0 until height
    }


    override fun place(animal: Animal): Boolean {
        if (!canMoveTo(animal.position)) {
            return false
        }

        if (objectAt(animal.position) != null) {
            animal.position = animals.keys.randomFreePosition(Vector2d(width, height)) ?: return false
        }

        animals[animal.position] = animal
        return true
    }

    override fun objectAt(position: Vector2d): Animal? = animals[position]

//    fun moveAnimal(oldPosition: Vector2d, newPosition: Vector2d) {
//        animals[newPosition] = animals.remove(oldPosition) ?: return
//    }

    fun updateAnimalPosition(oldPosition: Vector2d, animal: Animal) {
        animals.remove(oldPosition)

        if (objectAt(animal.position) != null || !canMoveTo(animal.position)) {
            animal.position = animals.keys.randomFreePosition(Vector2d(width, height)) ?: oldPosition
        }

        animals[animal.position] = animal
    }

//    private fun findRandomFreePosition(): Vector2d? {
//        return (0 until width).flatMap { x ->
//            (0 until height).map { y -> Vector2d(x, y) }
//        }.filterNot { it in animals.keys }.randomOrNull()
//    }


    override fun toString(): String {
        val horizontalBorder = "" + "-".repeat(2 * width + 1)

        val builder = StringBuilder()

        builder.append("y\\x ")
        for (x in 0 until width) {
            builder.append("$x ")
        }
        builder.append("\n")

        builder.append(" ${height}:").append(horizontalBorder).append("\n")

        for (y in height - 1 downTo 0) {
            builder.append(" $y:|")
            for (x in 0 until width) {
                val animal = objectAt(Vector2d(x, y))
                val symbol = when (animal?.direction) {
                    MapDirection.NORTH -> "^"
                    MapDirection.SOUTH -> "v"
                    MapDirection.EAST -> ">"
                    MapDirection.WEST -> "<"
                    null -> " "
                }
                builder.append("$symbol|")
            }
            builder.append("\n")
        }

        builder.append("-1:").append(horizontalBorder)
        builder.append("\n")
        return builder.toString()
    }
}
