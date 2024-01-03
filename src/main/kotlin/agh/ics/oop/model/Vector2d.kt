package agh.ics.oop.model

data class Vector2d(val x: Int, val y: Int) {
    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)
    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)
    fun opposite() = Vector2d(-x, -y)

    fun precedes(other: Vector2d) = x <= other.x && y <= other.y
    fun follows(other: Vector2d) = x >= other.x && y >= other.y
}
