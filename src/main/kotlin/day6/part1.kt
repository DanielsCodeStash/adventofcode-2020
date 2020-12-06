package day6

import util.readGroupsSpaced

fun main() {

    val groupAnswers = readGroupsSpaced(6)
            .map { it.replace(" ", "")} // remove spaces within groups
            .map { it.toCharArray().distinct().joinToString("")} // remove repeating chars

    println("Group sum count: ${groupAnswers.map { it.length }.sum()}")
}

