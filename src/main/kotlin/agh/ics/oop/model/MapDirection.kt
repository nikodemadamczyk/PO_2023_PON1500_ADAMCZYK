package agh.ics.oop.model

enum class MapDirection {
    NORTH, EAST, SOUTH, WEST;

    override fun toString() = when (this) {
        NORTH -> "N"
        SOUTH -> "S"
        WEST -> "W"
        EAST -> "E"
    }

    fun next() = values()[(ordinal + 1) % values().size]
    fun previous() = values()[(ordinal + values().size - 1) % values().size]
}
