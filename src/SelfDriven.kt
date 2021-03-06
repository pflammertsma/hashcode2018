import java.io.File
import java.io.InputStream
import java.util.*

class Scenario(lines: List<String>) {

    val rows: Int
    val cols: Int
    val rideCount: Int
    val bonus: Int
    val stepCount: Int

    val vehicles = LinkedList<Vehicle>()
    val rides = LinkedList<Ride>()

    var step = 0

    init {
        val values = lines[0].split(" ")
        var i = 0
        rows = values[i++].toInt()
        cols = values[i++].toInt()
        (0 until values[i++].toInt()).forEach {
            vehicles.add(Vehicle())
        }
        rideCount = values[i++].toInt()
        bonus = values[i++].toInt()
        stepCount = values[i++].toInt()

        for (i in 1..rideCount) {
            addRide(Ride(i, lines[i]))
        }

        vehicles.forEach {
            val vehicle = it
//            Route(vehicle, )
            rides.forEach {

            }
        }
    }

    fun addRide(ride: Ride) {
        rides.add(ride)
    }

    override fun toString(): String {
        return "Scenario:\n" +
                "\trows=$rows, cols=$cols, bonus=$bonus, stepCount=$stepCount,\n" +
                "\trides=$rides),\n" +
                "\tvehicles=$vehicles"
    }

}

class Ride(val index: Int, line: String) {

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
        return "Ride(startX=$startX, startY=$startY, endX=$endX, endY=$endY, startT=$startT, endT=$endT, distance=${calcDistance(startX, startY)}})"
    }

    fun score(currentStep: Int): Int =
            if (currentStep + calcDistance(startX, startY) >= endT) {
                // If we cannot get there in time, return infinite score
                Int.MAX_VALUE
            } else {
                // Otherwise
                startT - currentStep
            }

    fun calcDistance(curX: Int, curY: Int) =
            Math.abs(endX - curX) + Math.abs(endY - curY)

}

class Vehicle {

    var x = 0
    var y = 0

    override fun toString(): String {
        return "Vehicle($x, $y)"
    }

}

fun main(args: Array<String>) {
    val inputStream: InputStream = File("a_example.in").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)

    val lines = inputString.split(System.lineSeparator())
    val scenario = Scenario(lines)

    println(scenario)

    scenario.let {
        var rides = it.rides
        for (i in 2..rides.size) {
            val routes = Recursion.getAllLists(rides, i)
            routes.forEach {
                val route = it
                route.forEach {
                    val ride = it
                    print("${ride.index},")
                }
                println(" (${rides.size - route.size} remain)")
            }
        }
    }

}
