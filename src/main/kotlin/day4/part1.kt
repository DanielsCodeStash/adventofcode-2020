package day4

import util.readInput

class Passport(var data: MutableMap<String, String> = mutableMapOf()) {
    override fun toString(): String {
        return data.entries.toString()
    }
}

fun main() {

    val input = readInput(4)

    val passports = mutableListOf<Passport>()
    var activePassport = Passport()

    for((i, row) in input.withIndex()) {

        if(i == input.size-1 || row.isBlank()) {
            passports.add(activePassport)
            activePassport = Passport()
            continue
        }

        row.split(" ").forEach { dataEntry ->
            val data = dataEntry.split(":")
            activePassport.data[data[0]] = data[1]
        }
    }

    val validPassports = passports.filter { passportIsValid(it) }

    println(validPassports)

    println("Total parsed passports: ${passports.size}")
    println("Number of valid passports: ${validPassports.count()}")
}

private fun passportIsValid(passport: Passport) : Boolean {
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    return requiredFields.none { !passport.data.containsKey(it) }
}

