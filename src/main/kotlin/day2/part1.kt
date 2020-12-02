package day2

import util.readInput

fun main() {

    var numCorrect = 0

    for(row in readInput(2)) {

        val baseParts = row.split(" ")

        val numRequiredRange = baseParts[0].split("-")

        val rangeStart = numRequiredRange[0].toInt()
        val rangeEnd = numRequiredRange[1].toInt()
        val letter = baseParts[1].substring(0, 1)
        val password = baseParts[2]

        if(isValidPassword(password, letter, rangeStart, rangeEnd)) {
            numCorrect++
        }
    }

    println("Total entries: ${readInput(2).size} " )
    println("Answer: $numCorrect")
}

private fun isValidPassword(password: String, letter: String, from: Int, to: Int): Boolean
{
    val numOfLetter = password.count{it == letter.single()}

    return numOfLetter in from..to
}