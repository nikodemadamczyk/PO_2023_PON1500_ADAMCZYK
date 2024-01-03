package agh.ics.oop.model

interface IWorldMap {
    fun canMoveTo(position: Vector2d): Boolean
    fun place(animal: Animal): Boolean
    fun objectAt(position: Vector2d): Animal?
//    fun move(animal: Animal, moveDirection: MoveDirection): Boolean // Add this line
}
