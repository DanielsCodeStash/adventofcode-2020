package day8

import util.readInput

fun main() {

    val instructions = readInput(8)
            .map {
                val split = it.split(" ")
                Instruction(InstructionType.valueOf(split[0].toUpperCase()), split[1].toInt())
            }

    val instructionIndexToTimesRun = mutableMapOf<Int, Int>()
    var accumulator = 0
    var activeIndex = 0

    while (true) {
        val instruction = instructions[activeIndex]
        var newActiveIndex = activeIndex
        val timesRun = instructionIndexToTimesRun.getOrDefault(activeIndex, 0)
        if(timesRun == 1) {
            break
        }

        when(instruction.type) {
            InstructionType.NOP -> newActiveIndex++
            InstructionType.ACC -> {
                accumulator += instruction.value
                newActiveIndex++
            }
            InstructionType.JMP -> newActiveIndex += instruction.value
        }

        instructionIndexToTimesRun[activeIndex] = timesRun + 1
        println("Ran instruction $instruction acc: $accumulator index: $newActiveIndex times: ${instructionIndexToTimesRun[activeIndex]}")
        activeIndex = newActiveIndex
    }

    println("Acc: $accumulator")

}

