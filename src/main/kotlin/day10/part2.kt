package day10

import util.readInput

data class Result(var answersFound: Long = 0)

var numbers: List<Int> = readInput(10).map{it.toInt()}.sortedBy { it }
var result = Result()
val endNum = numbers.maxOrNull()

fun main() {

    addPossibleFirst(0)

    println("Answer: ${result.answersFound}")
}

fun addPossibleFirst(startNum: Int) {

    val possibleNumbers = numbers.filter { it > startNum && it <= startNum + 3  }

    possibleNumbers
            .forEach {
                if(it == endNum) {
                    println("$startNum ${result.answersFound}")
                    result.answersFound++
                }

                addPossibleFirst(it)
            }
}
