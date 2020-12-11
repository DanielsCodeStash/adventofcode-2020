package day11

import util.readInput

fun main() {

    val rows: List<String> = readInput(11)

    var activeState = rows.toMutableList()
    val newState = rows.toMutableList()

    while (true) {

        for ((rowIndex, row) in rows.withIndex()) {

            for ((columnIndex, column) in row.toCharArray().withIndex()) {

                when (activeState[rowIndex][columnIndex]) {
                    '.' -> continue
                    'L' -> if (getNumOccupiedAdjacentSeats(activeState, rowIndex, columnIndex) == 0) {
                        newState[rowIndex] = newState[rowIndex].substring(0, columnIndex) + '#' + newState[rowIndex].substring(columnIndex + 1)
                    }
                    '#' -> if (getNumOccupiedAdjacentSeats(activeState, rowIndex, columnIndex) >= 4) {
                        newState[rowIndex] = newState[rowIndex].substring(0, columnIndex) + 'L' + newState[rowIndex].substring(columnIndex + 1)
                    }
                }
            }
        }

        if(activeState == newState) {
            break
        }

        activeState = newState.toMutableList()
    }

    for((rowIndex, row) in rows.withIndex()) {

        for ((columnIndex, column) in row.toCharArray().withIndex()) {
            print(newState[rowIndex][columnIndex])
        }
        print("\n")
    }

    println("Answer: " + newState.joinToString(" ").count { it == '#' })

}

private fun getNumOccupiedAdjacentSeats(activeState: List<String>, rowIndex: Int, columnIndex: Int): Int {

    val width = activeState.first().length
    val height = activeState.count()
    val positionsToCheck = mutableListOf<Pair<Int, Int>>()

    if(rowIndex > 0) { // above
        positionsToCheck.add(Pair(rowIndex-1, columnIndex)) // directly above
        if(columnIndex > 0) { // above and to left
            positionsToCheck.add(Pair(rowIndex-1, columnIndex-1))
        }
        if(columnIndex < width-1) { // above and to right
            positionsToCheck.add(Pair(rowIndex-1, columnIndex+1))
        }
    }

    if(columnIndex < width-1) {
        positionsToCheck.add(Pair(rowIndex, columnIndex+1)) // directly right
    }

    if(columnIndex > 0) {
        positionsToCheck.add(Pair(rowIndex, columnIndex-1)) // directly left
    }

    if(rowIndex < height-1) {
        positionsToCheck.add(Pair(rowIndex+1, columnIndex)) // directly below
        if(columnIndex > 0) {
            positionsToCheck.add(Pair(rowIndex+1, columnIndex-1)) // below and left
        }
        if(columnIndex < width-1) {
            positionsToCheck.add(Pair(rowIndex+1, columnIndex+1))
        }
    }

    println(positionsToCheck)

    return positionsToCheck
            .map { activeState[it.first][it.second] }
            .filter { it == '#' }
            .count()
}

