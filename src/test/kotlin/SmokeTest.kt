package hello.tests

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import solver.evalPrefixExpression
import solver.solve
import kotlin.math.abs
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SmokeTest {
    @Test fun solvesSolvableProblem() {
        val solution = solve(listOf(3.0, 3.0, 8.0, 8.0), 24.0)

        assertNotNull(solution, "No solution found")
        assertTrue(abs(evalPrefixExpression(solution) - 24.0) < 0.001, "Wrong solution")
    }

    @Test fun returnsNullForUnsolvableProblem() {
        val solution = solve(listOf(1.0, 2.0, 3.0), 24.0)

        assertNull(solution, "Solution found unlike expected")
    }
}
