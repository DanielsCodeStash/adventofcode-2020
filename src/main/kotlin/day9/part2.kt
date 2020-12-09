package day9

import util.readInput
import java.lang.RuntimeException

fun main() {

    val numbers = readInput(9).map{it.toLong()}
    val preambleSize = 25

    val encryptionNumber = findEncryptionNumber(numbers, preambleSize)

    for(setSize in 2 until numbers.size) {

        for(startIndex in 0 until numbers.size-setSize){
            val consideredNums = numbers.subList(startIndex, startIndex+setSize)

            if(consideredNums.sum() == encryptionNumber) {
                val weakness = consideredNums.minOrNull()!! + consideredNums.maxOrNull()!!
                println("Anwser: $weakness")
                return
            }
        }
    }
}

fun findEncryptionNumber(numbers: List<Long>, preambleSize: Int): Long {

    for(activeNumberIndex in preambleSize until numbers.size) {

        val consideredPreamble = numbers.subList(activeNumberIndex-preambleSize, activeNumberIndex)
        val activeNumber = numbers[activeNumberIndex]

        val sumExists = sumExists(consideredPreamble, activeNumber)

        if(!sumExists) {
            return activeNumber
        }
    }
    throw RuntimeException("Did not find number")
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

