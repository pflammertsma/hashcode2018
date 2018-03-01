import java.io.File
import java.io.InputStream
import java.util.*
import java.util.regex.Pattern

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

        (1 until rideCount)
                .forEach {
                    addRide(Ride(lines[it]))
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
        return "Scenario(rows=$rows, cols=$cols, bonus=$bonus, stepCount=$stepCount, rides=$rides), vehicles=$vehicles"
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

    val subset = testSubset(intArrayOf(10, 20, 30, 40, 50), 5)
    subset.forEach {
        it.forEach {
            print("$it ")
        }
        println()
    }

}

fun testSubset(input: IntArray, subsetLength: Int): ArrayList<IntArray> {
    val subsets = ArrayList<IntArray>()

    val s = IntArray(subsetLength)                  // here we'll keep indices
    // pointing to elements in input array

    if (subsetLength <= input.size) {
        // first index sequence: 0, 1, 2, ...
        run {
            var i = 0
            while (true) {
                s[i] = i
                if (i >= subsetLength - 1) {
                    break
                }
                i++
            }
        }
        subsets.add(getSubset(input, s))
        while (true) {
            var i: Int
            // find position of item that can be incremented
            i = subsetLength - 1
            while (i >= 0 && s[i] == input.size - subsetLength + i) {
                i--
            }
            if (i < 0) {
                break
            }
            s[i]++                    // increment this item
            ++i
            while (i < subsetLength) {    // fill up remaining items
                s[i] = s[i - 1] + 1
                i++
            }
            subsets.add(getSubset(input, s))
        }
    }

    return subsets
}

// generate actual subset by index sequence
internal fun getSubset(input: IntArray, subset: IntArray): IntArray {
    val result = IntArray(subset.size)
    for (i in subset.indices)
        result[i] = input[subset[i]]
    return result
}
