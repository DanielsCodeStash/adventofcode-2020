package day6

import util.readInput

fun main() {

    val groupAnswers = readInput(6)
            .joinToString(" ")
            .split("  ")
            .map { it.split(" ") }
            .map { findQuestionsWithAllSameAnswers(it) }

    println("Group sum count: ${groupAnswers.map { it.length }.sum()}")
}

private fun findQuestionsWithAllSameAnswers(answers: List<String>): String {
    return answers
            .joinToString("")
            .toCharArray()
            .groupBy { it }
            .filter { it.value.size >= answers.size }
            .map { it.key }
            .joinToString("")
}

