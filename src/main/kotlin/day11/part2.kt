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
                    '#' -> if (getNumOccupiedAdjacentSeats(activeState, rowIndex, columnIndex) >= 5) {
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

    val deltasToCheck = listOf(
            Pair(-1, -1), // up-left
            Pair(-1, 0),  // up
            Pair(-1, 1), // up-right
            Pair(0, -1), // left
            Pair(0, 1), // right
            Pair(1, -1), // down-left
            Pair(1, 0), // down
            Pair(1, 1) // down-right
    )

    return deltasToCheck
            .filter { occupiedSeatExistsInDirection(activeState, rowIndex, columnIndex, it.first, it.second) }
            .count()
}

 fun occupiedSeatExistsInDirection(activeState: List<String>, startRow: Int, startCol: Int, deltaRow: Int, deltaCol: Int): Boolean {

    var activeRow = startRow + deltaRow
    var activeCol = startCol + deltaCol

    while (!positionOutsideOfPlay(activeState, activeRow, activeCol))
    {

        if(activeState[activeRow][activeCol] == 'L') {
            return false
        }
        if(activeState[activeRow][activeCol] == '#') {
            return true
        }

        activeRow += deltaRow
        activeCol += deltaCol
    }

    return false
}

private fun positionOutsideOfPlay(activeState: List<String>, rowIndex: Int, columnIndex: Int): Boolean {

    if(rowIndex < 0 || rowIndex >= activeState.size) {
        return true
    }

    if(columnIndex < 0 || columnIndex >= activeState.first().length ) {
        return true
    }

    return false
}