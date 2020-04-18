package solver

import java.lang.NumberFormatException
import kotlin.system.exitProcess

val usage =
    """
        At least 3 numeric arguments expected.
        First argument is used as the target value.
        Rest of arguments are used as the numbers to produce the target value.
        For example: 24.0 1.0 9.0 7.0 4.0
    """.trimIndent()

data class ParsedArgs(val target: Double, val numbersToUse: List<Double>)

fun main(args : Array<String>) {
    fun parseArgumentsOrDie(): ParsedArgs {
        if (args.size < 3) {
            println("Invalid number of arguments")
            print(usage)
            exitProcess(1)
        }
        val doubles: List<Double> = try {
            args.map { it.toDouble() }
        } catch (e: NumberFormatException) {
            println("Invalid input: ${e.message}")
            print(usage)
            exitProcess(1)
        }
        return ParsedArgs(doubles[0], doubles.subList(1, doubles.size))
    }

    val parsedArgs = parseArgumentsOrDie()
    val solution = solve(parsedArgs.numbersToUse, parsedArgs.target)
    if (solution == null) {
        println("No solution found")
    } else {
        println("Solution: $solution")
    }
}
