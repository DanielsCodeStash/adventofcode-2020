package day9

import util.readInput

fun main() {

    val numbers = readInput(9).map{it.toLong()}
    val preambleSize = 25

    for(activeNumberIndex in preambleSize until numbers.size) {

        val consideredPreamble = numbers.subList(activeNumberIndex-preambleSize, activeNumberIndex)
        val activeNumber = numbers[activeNumberIndex]

        val sumExists = sumExists(consideredPreamble, activeNumber)

        if(!sumExists) {
            println("$activeNumber finns inte!")
        }
    }
}

private fun sumExists(consideredPreamble: List<Long>, activeNumber: Long): Boolean {
    for (i in consideredPreamble) {
        for(y in consideredPreamble) {
            if(i+y==activeNumber) {
                return true
            }
        }
    }
    return false
}

