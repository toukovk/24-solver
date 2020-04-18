package solver

import java.lang.RuntimeException
import kotlin.math.abs

interface ExpressionItem

enum class Operator : ExpressionItem {
    ADD { override fun apply(a1: Double, a2: Double) = a1 + a2 },
    MINUS { override fun apply(a1: Double, a2: Double) = a1 - a2 },
    TIMES { override fun apply(a1: Double, a2: Double) = a1 * a2 },
    DIVIDE { override fun apply(a1: Double, a2: Double) = a1 / a2 };

    abstract fun apply(a1: Double, a2: Double): Double
}

data class Scalar(
    val value: Double
): ExpressionItem

fun evalPrefixExpression(expression: List<ExpressionItem>): Double {
    var index = 0
    fun evalNext(): Double {
        return when (val current = expression[index++]) {
            is Scalar -> current.value
            is Operator -> current.apply(evalNext(), evalNext())
            else -> throw RuntimeException("Unexpected type: $current")
        }
    }
    return evalNext()
}

fun solve(values: List<Double>, target: Double): List<ExpressionItem>? {
    fun recur(expressionSoFar: List<ExpressionItem>, remainingComponents: List<Double>): List<ExpressionItem>? {
        if (remainingComponents.isEmpty()) {
            return if (abs(evalPrefixExpression(expressionSoFar) - target) < 0.001) {
                expressionSoFar
            } else {
                null
            }
        }
        val numbersSoFar = expressionSoFar.filterIsInstance<Scalar>().count()
        val operatorsSoFar = expressionSoFar.filterIsInstance<Operator>().count()
        // Try out all operators if operator can be added
        if (operatorsSoFar < values.size - 1) {
            for (op in Operator.values()) {
                val result = recur(expressionSoFar + op, remainingComponents)
                if (result != null) {
                    return result
                }
            }
        }
        // Try out all operators if number can be added
        if (numbersSoFar < operatorsSoFar || (numbersSoFar == operatorsSoFar && remainingComponents.size == 1)) {
            for (number in remainingComponents) {
                val result = recur(
                    expressionSoFar + Scalar(number),
                    remainingComponents - number
                )
                if (result != null) {
                    return result
                }
            }
        }
        return null
    }
    return recur(listOf(), values)
}


