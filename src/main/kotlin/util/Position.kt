package util

import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

enum class Way {RIGHT, LEFT}

data class Position(var x: Int, var y:Int) {

    fun rotated(way: Way, angle: Int): Position {
        val c = cos(Math.toRadians(angle.toDouble()))
        val s = sin(Math.toRadians(angle.toDouble()))

        return when(way) {
            Way.RIGHT -> {
                val newX = (x * c + y * s).roundToInt()
                val newY = (-x * s + y * c).roundToInt()
                Position(newX, newY)
            }
            Way.LEFT -> {
                val newX = (x * c - y * s).roundToInt()
                val newY = (x * s + y * c).roundToInt()
                Position(newX, newY)
            }
        }
    }

    fun moved(direction: Direction, steps: Int): Position {
        return when(direction) {
            Direction.NORTH -> Position(x, y + steps)
            Direction.EAST -> Position(x + steps, y)
            Direction.SOUTH -> Position(x, y - steps)
            Direction.WEST -> Position(x - steps, y)
        }
    }

}