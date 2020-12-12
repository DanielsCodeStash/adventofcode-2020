package day12

import util.Direction
import util.Position
import util.Way
import util.readInput
import java.lang.RuntimeException
import kotlin.math.abs

fun main() {

    val waypoint = Waypoint(10, 1)
    val shipPosition = Position(0, 0)

    readInput(12)
            .forEach { applyInstruction(it[0], it.substring(1).toInt(), shipPosition, waypoint) }

    println("Answer: ${abs(shipPosition.x) + abs(shipPosition.y)}")

}

private fun applyInstruction(action: Char, value: Int, shipPosition: Position, waypoint: Waypoint) {

    when(action) {
        'L', 'R' -> waypoint.rotate(action, value)
        'N', 'S', 'E', 'W' -> waypoint.move(action, value)
        'F' -> {
            shipPosition.x += (waypoint.pos.x * value)
            shipPosition.y += (waypoint.pos.y * value)
        }
    }
}

data class Waypoint(var pos: Position){
    constructor(x: Int, y: Int) : this(Position(x, y))

    fun rotate(direction: Char, angle: Int) {
        val way = if(direction == 'R') Way.RIGHT else Way.LEFT
        pos = pos.rotated(way, angle)
    }

    fun move(directionChar: Char, amount: Int) {
        val direction = when(directionChar) {
            'N' -> Direction.NORTH
            'S' -> Direction.SOUTH
            'E' -> Direction.EAST
            'W' -> Direction.WEST
            else -> throw RuntimeException("Unsupported direction $directionChar")
        }
        pos = pos.moved(direction, amount)
    }
}