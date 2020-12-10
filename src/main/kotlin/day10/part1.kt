package day10

import util.readInput

fun main() {

    val numbers = readInput(10).map{it.toInt()}.sortedBy { it }

    var jolt1 = 0
    var jolt2 = 0
    var jolt3 = 0
    var lastJolt = 0

    for (num in numbers) {

        when(num-lastJolt) {
            1 -> jolt1++
            2 -> jolt2++
            3 -> jolt3++
        }
        lastJolt = num
    }

    jolt3++

    println("jolt1: $jolt1 jolt3: $jolt3")
    println("Answer: ${jolt1*jolt3}")

}

