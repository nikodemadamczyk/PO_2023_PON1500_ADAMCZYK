package agh.ics.oop.model

class Simulation(
    private val initialPositions: List<Vector2d>,
    private val moves: List<MoveDirection>,
    private val map: IWorldMap
) {
    private val animals = mutableListOf<Animal>()

    init {
        for (initialPosition in initialPositions) {
            val animal = Animal(initialPosition)
            animals.add(animal)
            map.place(animal)
        }
    }

    fun run() {
        for (i in moves.indices) {
            val animalIndex = i % animals.size
            val animal = animals[animalIndex]
            val oldPosition = animal.position

            try {
                animal.move(moves[i], map) // Pass map here
                (map as? BouncyMap)?.updateAnimalPosition(oldPosition, animal)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
            println(map.toString())
            // Thread.sleep(500)
        }
    }
}
