package util

import java.io.File

fun readInput(dayNum: Int) = File("src\\main\\kotlin\\day$dayNum\\input.txt").readLines()

fun readGroupsSpaced(dayNum: Int) = readInput(dayNum)
        .joinToString(" ")
        .split("  ")

fun readGroupsSplit(dayNum: Int) = readInput(dayNum)
        .joinToString(" ")
        .split("  ")
        .map { it.split(" ") }

inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}