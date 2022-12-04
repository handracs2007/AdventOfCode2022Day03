import java.io.File

const val a = 1
const val A = 27

fun part1() {
    var priority = 0
    File("input.txt").forEachLine { line ->
        // Get the items for the first and second compartments.
        val compartment1 = line.substring(0 until line.length / 2).toSet()
        val compartment2 = line.substring(line.length / 2).toSet()

        // Find the same element in both compartments.
        val sameElements = compartment1.intersect(other = compartment2)
        if (sameElements.isNotEmpty()) {
            val element = sameElements.elementAt(0)
            priority += if (Character.isUpperCase(element)) element - 'A' + A else element - 'a' + a
        }
    }

    println(priority)
}

fun part2() {
    // Read all the lines.
    val lines = File("input.txt").readLines()

    // Group the elemnts every 3 items.
    var priority = 0

    // We create a window for each 3 elements in the list. We then map each item in the group to a set of characters
    // so that we can easily use it further down.
    val groups = lines.windowed(size = 3, step = 3).map { group -> group.map { it.toSet() } }
    groups.forEach { rucksacks ->
        // Find the intersection for all the three elements.
        val sameElements =
            rucksacks[0].intersect(other = rucksacks[1]).intersect(other = rucksacks[2])
        if (sameElements.isNotEmpty()) {
            val element = sameElements.elementAt(0)
            priority += if (Character.isUpperCase(element)) element - 'A' + A else element - 'a' + a
        }
    }

    println(priority)
}

fun main() {
    part1()
    part2()
}