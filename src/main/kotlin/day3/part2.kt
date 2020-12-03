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

    val treesHit = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2) )
            .map { getTreesHit(it.first, it.second, rows, treePositions) }

    println("Trees hit: $treesHit")
    println("Answer: ${treesHit.reduce { acc, i ->  acc * i }}")
}

private fun getTreesHit(deltaX: Int, deltaY: Int, rows: List<String>, treePositions: Set<String>): Int {

    var activeY = 0
    var activeX = 0
    var treesHit = 0

    while (activeY < rows.size) {
        activeX += deltaX
        activeY += deltaY

        val virtualX = activeX % rows.first().length

        if(treePositions.contains(hashPosition(virtualX, activeY))) {
            treesHit++
        }
    }

    return treesHit
}

private fun hashPosition(x: Int, y: Int): String {
    return "$x&$y"
}

