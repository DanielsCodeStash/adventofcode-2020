package day12

import util.Direction
import util.readInput
import java.lang.Math.floorMod
import java.lang.RuntimeException
import kotlin.math.abs

fun main() {

    val ship = Ship()

    readInput(12)
            .forEach { ship.applyInstruction(it[0], it.substring(1).toInt()) }

    println("Answer: ${abs(ship.posX) + abs(ship.posY)}")

}

class Ship(var heading: Direction = Direction.EAST, var posX: Int = 0, var posY: Int = 0) {

    fun applyInstruction(action: Char, value: Int) {

        when(action) {
            'R', 'L'->  heading = newHeading(heading, action, value)
            'F' -> applyDirectionTrip(heading, value)
            'N' -> applyDirectionTrip(Direction.NORTH, value)
            'E' -> applyDirectionTrip(Direction.EAST, value)
            'W' -> applyDirectionTrip(Direction.WEST, value)
            'S' -> applyDirectionTrip(Direction.SOUTH, value)
        }
    }

    private fun applyDirectionTrip(direction: Direction, amount: Int) {
        when (direction) {
            Direction.NORTH -> posY += amount
            Direction.EAST -> posX += amount
            Direction.SOUTH -> posY -= amount
            Direction.WEST -> posX -= amount
        }
    }

    private fun newHeading(oldHeading: Direction, changeDirection: Char, changeAmount: Int): Direction {

        val startDegree = when (oldHeading) {
            Direction.NORTH -> 0
            Direction.EAST -> 90
            Direction.SOUTH -> 180
            Direction.WEST -> 270
        }

        val newDegree = if(changeDirection == 'R') startDegree + changeAmount else  startDegree - changeAmount

        return when(floorMod(newDegree, 360)) {
            0 -> Direction.NORTH
            90 -> Direction.EAST
            180 -> Direction.SOUTH
            270 -> Direction.WEST
            else -> throw RuntimeException("Unexpected degree $changeAmount ${newDegree % 360}")
        }
    }
}