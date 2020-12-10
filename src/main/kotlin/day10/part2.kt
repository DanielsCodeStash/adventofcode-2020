package day10

import util.readInput

data class Result(var answersFound: Long = 0)

var numbers: List<Int> = readInput(10).map{it.toInt()}.sortedBy { it }
val numToSum = mutableMapOf<Int, Long>()
val endNum = numbers.maxOrNull()

fun main() {

    val possibilities = getPermutationsForNum(0, emptyList())

    println("Answer: ${possibilities}")
}

fun getPermutationsForNum(startNum: Int, path: List<Int>): Long {

    if(startNum == endNum)
        return 1

    val possibleNumbers = numbers.filter { it > startNum && it <= startNum + 3  }

    if(possibleNumbers.isEmpty()) return 1



    var totalSumOfLower = 0L
    for(num in possibleNumbers) {



        if(numToSum.containsKey(num)) {
            totalSumOfLower += numToSum.get(num)!!
        } else {
            val permutationsForNum = getPermutationsForNum(num, path + 1)
            totalSumOfLower += permutationsForNum
            numToSum.put(num, totalSumOfLower)
        }
    }
    return totalSumOfLower
}