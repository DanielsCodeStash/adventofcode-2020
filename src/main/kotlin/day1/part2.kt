package day1

import util.readInput

fun main() {

    val input = readInput(1).map { it.toInt() }

    var term1: Int? = null
    var term2: Int? = null
    var term3: Int? = null

    for (num1 in input) {
        for (num2 in input) {
            for(num3 in input) {

                if (num1 + num2 + num3 == 2020) {
                    term1 = num1
                    term2 = num2
                    term3 = num3
                }
            }
        }
    }

    if(term1 != null && term2 != null && term3 != null) {
        println("$term1 + $term2 + $term3 = ${term1 + term2 + term3}")
        println("Answer:  ${term1 * term2 * term3}")
    } else {
        println("Failed to find answer")
    }

}