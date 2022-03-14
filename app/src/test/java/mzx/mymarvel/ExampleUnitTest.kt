package mzx.mymarvel

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
object ExampleUnitTest: Spek( {
    Feature("Check addition test") {
        Scenario("Addition") {
            Then("check sum") {
                assertEquals(4, 2 + 2)
            }
        }
    }
})