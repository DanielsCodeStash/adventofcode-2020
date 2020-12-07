package day7

import util.readInput

fun main() {

    val allBags = readInput(7).map { toBag(it) }

    val alreadyExplored = mutableSetOf<String>()

    var numMatches = 0
    var activeLevelNames = listOf("shiny gold")
    do {
        val bagsToExplore = getBagsContaining(activeLevelNames, allBags)
                .filter { !alreadyExplored.contains(it.name) }

        bagsToExplore.forEach { alreadyExplored.add(it.name) }

        numMatches += bagsToExplore.size
        activeLevelNames = bagsToExplore.map { it.name }

    } while (activeLevelNames.isNotEmpty())

    println("Shiny bag containers: $numMatches")

}

private fun getBagsContaining(names: List<String>, bags: List<Bag>) =
        bags.filter {it.containedBags.any { subBag -> names.contains(subBag.name) } }
