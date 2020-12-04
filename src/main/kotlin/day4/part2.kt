package day4

import util.readInput

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
    if(!requiredFields.none { !passport.data.containsKey(it) }) {
        return false // some field does not exist
    }

    return requiredFields.none { !fieldIsValid(it, passport.data[it]!!) }
}

private fun fieldIsValid(name: String, data: String): Boolean {

    when(name) {
        "byr" -> return isValidDigits(4, data) && data.toInt() in 1920..2002
        "iyr" -> return isValidDigits(4, data) && data.toInt() in 2010..2020
        "eyr" -> return isValidDigits(4, data) && data.toInt() in 2020..2030
        "hgt" -> {
            if(data.length < 3){
                return false
            }
            val num = data.substring(0, data.length-2).toInt()
            return if(data.endsWith("cm")) {
                num in 150..193
            } else {
                num in 59..76
            }
        }
        "hcl" -> {
            if(data.length != 7 || data[0] != '#') {
                return false
            }
            return "#[a-f0-9]{6}".toRegex().matches(data)
        }
        "ecl" -> {
            val requiredFields = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            return requiredFields.contains(data)
        }
        "pid" -> return isValidDigits(9, data)

    }
    return true
}

private fun isValidDigits(num: Int, data: String): Boolean {
    if(data.length != num){
        return false
    }
    return data.toCharArray().none { !it.isDigit() }
}
