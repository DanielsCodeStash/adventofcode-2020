package day7

data class Bag(val name: String, val num: Int, val containedBags: List<Bag> = emptyList()) {}

fun toBag(row: String): Bag {
    val parts = row.replace(".", "").split(" bags contain ")
    val bagName = parts[0]

    val containedBags = if(parts[1] == "no other bags") {
        emptyList()
    } else {
        parts[1].split(",")
                .map { it.trim() }
                .map { mapContainedBag(it)}
    }

    return Bag(bagName, 1, containedBags)
}

private fun mapContainedBag(bagString: String): Bag {

    val num = bagString.substring(0, bagString.indexOf(' ')).toInt()
    val name = bagString.substring(bagString.indexOf(' '), bagString.length)
            .replace(" bags", " ")
            .replace(" bag", " ")
            .trim()
    return Bag(name, num)

}