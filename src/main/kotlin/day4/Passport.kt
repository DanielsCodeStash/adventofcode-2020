package day4

class Passport(var data: MutableMap<String, String> = mutableMapOf()) {
    override fun toString(): String {
        return data.entries.toString()
    }
}