package util

import java.io.File

fun readInput(dayNum: Int) = File("src\\main\\kotlin\\day$dayNum\\input.txt").readLines()
