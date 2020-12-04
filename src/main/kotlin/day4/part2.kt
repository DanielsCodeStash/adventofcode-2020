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

        row.split(" ").forEach { dataEntry ->
            val data = dataEntry.split(":")
            activePassport.data[data[0]] = data[1]
        }
    }

    if(activePassport.data.isNotEmpty()) {
        passports.add(activePassport)
    }

    val validPassports = passports.filter { passportIsValid(it) }

    println("Total parsed passports: ${passports.size}")
    println("Number of valid passports: ${validPassports.count()}")
}

private fun passportIsValid(passport: Passport) : Boolean {

    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    if(!requiredFields.none { !passport.data.containsKey(it) }) {
        return false
    }

    return requiredFields.none { !fieldIsValid(it, passport.data[it]!!) }
}

private fun fieldIsValid(name: String, data: String): Boolean {

    when(name) {
        "byr" -> return validDigits(data, 4) && data.toInt() in 1920..2002
        "iyr" -> return validDigits(data, 4) && data.toInt() in 2010..2020
        "eyr" -> return validDigits(data, 4) && data.toInt() in 2020..2030
        "hgt" -> {
            return when {
                data.endsWith("cm") -> data.removeSuffix("cm").toInt() in 150..193
                data.endsWith("in") -> data.removeSuffix("in").toInt() in 59..76
                else -> false
            }
        }
        "hcl" -> return Regex("#[a-f0-9]{6}").matches(data)
        "ecl" -> return listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(data)
        "pid" -> return validDigits(data,9)
    }

    return false
}

private fun validDigits(data: String, num: Int): Boolean {
    if(data.length != num) {
        return false
    }
    return data.toCharArray().none { !it.isDigit() }
}
