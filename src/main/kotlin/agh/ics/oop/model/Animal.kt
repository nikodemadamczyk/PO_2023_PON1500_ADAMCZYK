package agh.ics.oop.model

class Animal(
    var position: Vector2d = Vector2d(2, 2),
    private var mapDirection: MapDirection = MapDirection.NORTH
) {
    val direction: MapDirection
        get() = mapDirection
    fun move(direction: MoveDirection, map: IWorldMap) {
        when (direction) {
            MoveDirection.RIGHT -> mapDirection = mapDirection.next()
            MoveDirection.LEFT -> mapDirection = mapDirection.previous()
            MoveDirection.FORWARD -> {
                val newPosition = position + mapDirection.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
            MoveDirection.BACKWARD -> {
                val newPosition = position - mapDirection.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
        }
    }
}
