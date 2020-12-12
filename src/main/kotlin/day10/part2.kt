package day10

import util.readInput
import util.sumByLong

var numbers: List<Int> = readInput(10).map{it.toInt()}
val numToSum = mutableMapOf<Int, Long>()
val endNum = numbers.maxOrNull()

fun main() {

    val permutations = getPermutationsForNum(0)

    println("Answer: $permutations")
}

fun getPermutationsForNum(startNum: Int): Long {

    val possibleNumbers = numbers.filter { it > startNum && it <= startNum + 3  }

    if(startNum == endNum || possibleNumbers.isEmpty())
        return 1

    return possibleNumbers.sumByLong { numToSum.getOrPut(it){ getPermutationsForNum(it)} }
}

