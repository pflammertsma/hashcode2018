import java.io.File
import java.io.InputStream

class SelfDriven {
}

fun main(args : Array<String>) {
    val inputStream: InputStream = File("a_example.in").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)
}
