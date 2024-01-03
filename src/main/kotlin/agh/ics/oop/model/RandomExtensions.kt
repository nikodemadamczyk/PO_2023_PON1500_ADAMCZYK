package agh.ics.oop.model

fun MapDirection.toUnitVector() = when (this) {
    MapDirection.NORTH -> Vector2d(0, 1)
    MapDirection.SOUTH -> Vector2d(0, -1)
    MapDirection.WEST -> Vector2d(-1, 0)
    MapDirection.EAST -> Vector2d(1, 0)
}

fun Map<Vector2d, Animal>.randomPosition() = keys.randomOrNull()


fun Set<Vector2d>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val freePositions = (0 until mapSize.x).flatMap { x ->
        (0 until mapSize.y).map { y -> Vector2d(x, y) }
    }.filterNot { it in this }

    return freePositions.randomOrNull()
}
