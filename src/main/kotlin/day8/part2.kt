package day8

import util.readInput

fun main() {

    val instructions = readInput(8)
            .map {
                val split = it.split(" ")
                Instruction(InstructionType.valueOf(split[0].toUpperCase()), split[1].toInt())
            }
            .toMutableList()

    for(instruction in instructions) {

        if(instruction.type == InstructionType.ACC) {
            continue
        }

        switchJmpAndNop(instruction)
        val result = runInstructions(instructions)
        if(result.stoppedByReadingAfter) {
            println("Acc: ${result.accumulator}")
            return
        } else {
            switchJmpAndNop(instruction)
        }
    }
}

fun switchJmpAndNop(instruction: Instruction) {
    if(instruction.type == InstructionType.JMP) {
        instruction.type = InstructionType.NOP
    } else if (instruction.type == InstructionType.NOP) {
        instruction.type = InstructionType.JMP
    }
}

data class RunResult(val accumulator: Int, val stoppedByReadingAfter: Boolean)

fun runInstructions(instructions: List<Instruction>): RunResult {

    val instructionIndexToTimesRun = mutableMapOf<Int, Int>()
    var accumulator = 0
    var activeIndex = 0

    while (true) {

        if(activeIndex >= instructions.size) {
            return RunResult(accumulator, true)
        }

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
    return RunResult(accumulator, false)
}

