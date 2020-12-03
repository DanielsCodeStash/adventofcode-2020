package day3

import util.readInput

fun main() {

    val treePositions = mutableSetOf<String>()

    val rows = readInput(3)
    rows.forEachIndexed { rowIndex, s ->
        s.forEachIndexed { charIndex, c ->
            if(c == '#') {
                treePositions.add(hashPosition(charIndex, rowIndex ))
            }
        }
    }
    var activeY = 0
    var activeX = 0
    var treesHit = 0

    while (activeY < rows.size) {
        activeX += 3
        activeY += 1

        val virtualX = activeX % rows.first().length

        if(treePositions.contains(hashPosition(virtualX, activeY))) {
            treesHit++
        }
    }

    println("Trees hit: $treesHit")
}

private fun hashPosition(x: Int, y: Int): String {
    return "$x&$y"
}

