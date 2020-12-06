package day6

import util.readInput

fun main() {

    val groupAnswers = readInput(6)
            .joinToString(" ")
            .split("  ") // double space is group separator
            .map { it.replace(" ", "")} // remove spaces within groups
            .map { it.toCharArray().distinct().joinToString("")} // remove repeating chars

    println("Group sum count: ${groupAnswers.map { it.length }.sum()}")
}

