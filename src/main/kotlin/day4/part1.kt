package day4

import util.readInput

fun main() {

    val input = readInput(4)

    val passports = mutableListOf<Passport>()
    var activePassport = Passport()

    for(row in input) {

        if(row.isBlank()) {
            passports.add(activePassport)
            activePassport = Passport()
            continue
        }

        if(activePassport.data.isNotEmpty()) {
            passports.add(activePassport)
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

