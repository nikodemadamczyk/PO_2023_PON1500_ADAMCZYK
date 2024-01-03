package agh.ics.oop.model.agh.ics.oop.model

import agh.ics.oop.model.*
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : BehaviorSpec({
    given("a BouncyMap of size 5x5") {
        val map = BouncyMap(5, 5)

        `when`("an animal is placed at a valid position") {
            val animal = Animal(Vector2d(2, 2))
            val result = map.place(animal)

            then("it should be placed successfully") {
                result shouldBe true
                map.objectAt(Vector2d(2, 2)) shouldBe animal
            }
        }

        `when`("an animal is placed outside the map") {
            val animal = Animal(Vector2d(6, 6))
            val result = map.place(animal)

            then("it should not be placed") {
                result shouldBe false
            }
        }

        `when`("two animals are placed at the same position") {
            val animal1 = Animal(Vector2d(3, 3))
            val animal2 = Animal(Vector2d(3, 3))
            map.place(animal1)
            val result = map.place(animal2)

            then("the second animal should bounce to a new position") {
                result shouldBe true
                map.objectAt(Vector2d(3, 3)) shouldNotBe animal2
            }
        }
        `when`("an animal tries to move to an occupied position") {
            val animal1 = Animal(Vector2d(1, 1))
            val animal2 = Animal(Vector2d(2, 1))
            map.place(animal1)
            map.place(animal2)
            val oldPosition = animal2.position
            animal2.move(MoveDirection.FORWARD, map)
            map.updateAnimalPosition(oldPosition, animal2)

            then("it should bounce to a new position") {
                animal2.position shouldNotBe Vector2d(1, 1)
                animal2.position shouldNotBe oldPosition
            }
        }

        `when`("an animal tries to move outside the map") {
            val animal = Animal(Vector2d(0, 0), MapDirection.WEST)
            map.place(animal)
            val oldPosition = animal.position
            animal.move(MoveDirection.FORWARD, map)
            map.updateAnimalPosition(oldPosition, animal)

            then("it should bounce to a new position inside the map") {
                animal.position.x shouldBeInRange 0..4
                animal.position.y shouldBeInRange 0..4
            }
        }

        `when`("the map is full and a new animal is placed") {
            // Fill the map
            for (x in 0 until 5) {
                for (y in 0 until 5) {
                    map.place(Animal(Vector2d(x, y)))
                }
            }
            val newAnimal = Animal(Vector2d(2, 2))

            then("placing a new animal should fail") {
                map.place(newAnimal) shouldBe false
            }
        }
    }
})