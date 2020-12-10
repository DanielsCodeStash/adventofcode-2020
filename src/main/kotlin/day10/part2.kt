package day10

import util.readInput

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

inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}