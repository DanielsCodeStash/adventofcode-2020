package day6

import util.readGroupsSplit

fun main() {

    val groupAnswers = readGroupsSplit(6)
            .map { findQuestionsWithAllSameAnswers(it) }

    println("Group sum count: ${groupAnswers.map { it.length }.sum()}")
}

private fun findQuestionsWithAllSameAnswers(answers: List<String>) =
        answers
            .joinToString("")
            .toCharArray() // join all answers within a group to one big char array
            .groupBy { it } // group by char to find how many of each char we have
            .filter { it.value.size == answers.size } // keep only those that is duplicated as many times as there are group members
            .map { it.key }
            .joinToString("")

