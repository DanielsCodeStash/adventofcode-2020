package day7

import util.readInput

fun main() {

    val allBags = readInput(7).map { toBag(it) }

    val numSubBags = numSubBags("shiny gold", allBags)

    println("Num contained bags: $numSubBags")
}

private fun numSubBags(name: String, allBags: List<Bag>): Int {
    val bag = allBags.first { it.name == name }

    return if(bag.containedBags.isEmpty()) {
        0
    } else {
        bag.containedBags.sumBy { it.num + it.num * numSubBags(it.name, allBags) }
    }
}