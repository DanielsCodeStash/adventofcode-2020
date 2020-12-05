package day5

import util.readInput

fun main() {

    val rows = readInput(5)

    val rowColumnPasses = rows
        .map { Pair(it.substring(0, 7), it.substring(7, 10)) }
        .map{ Pair(toBinaryString('B', 'F', it.first), toBinaryString('R', 'L', it.second))}
        .map { Pair(binaryToInt(it.first), binaryToInt(it.second)) }

    val maxId = rowColumnPasses
        .map { it.first * 8 + it.second }
        .maxByOrNull { it }

    println("Max: $maxId")

}

private fun binaryToInt(binary: String): Int = Integer.parseInt(binary, 2);

private fun toBinaryString(oneChar: Char, zeroChar: Char, data: String) : String {
    var s = data.replace(oneChar, '1')
    s = s.replace(zeroChar, '0')
    return s
}

