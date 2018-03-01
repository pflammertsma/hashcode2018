import java.io.File
import java.io.InputStream
import java.util.*

class SelfDriven {
}

class Scenario(header: String) {

    val rows: Int
    val cols: Int
    val vehicles: Int
    val rides: Int
    val bonus: Int
    val steps: Int

    val rideList = LinkedList<Ride>()

    init {
        val values = header.split(" ")
        var i = 0
        rows = values[i++].toInt()
        cols = values[i++].toInt()
        vehicles = values[i++].toInt()
        rides = values[i++].toInt()
        bonus = values[i++].toInt()
        steps = values[i++].toInt()
    }

    fun addRide(ride: Ride) {
        rideList.add(ride)
    }

    override fun toString(): String {
        return "Scenario(rows=$rows, cols=$cols, vehicles=$vehicles, rides=$rides, bonus=$bonus, steps=$steps, rideList=$rideList)"
    }


}

class Ride(line: String) {

    val startX: Int
    val startY: Int
    val endX: Int
    val endY: Int
    val startT: Int
    val endT: Int

    init {
        val values = line.split(" ")
        var i = 0
        startX = values[i++].toInt()
        startY = values[i++].toInt()
        endX = values[i++].toInt()
        endY = values[i++].toInt()
        startT = values[i++].toInt()
        endT = values[i++].toInt()
    }

    override fun toString(): String {
        return "Ride(startX=$startX, startY=$startY, endX=$endX, endY=$endY, startT=$startT, endT=$endT)"
    }


}

fun main(args: Array<String>) {
    val inputStream: InputStream = File("a_example.in").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)

    val lines = inputString.split("\n")
    val scenario = Scenario(lines[0])
    for (i in 1 until lines.size) {
        // Skip empty lines
        if (lines[i].isEmpty()) continue
        scenario.addRide(Ride(lines[i]))
    }

    println(scenario)
}
