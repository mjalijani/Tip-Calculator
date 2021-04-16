package ir.training.tipcalculator

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import ir.training.tipcalculator.view.TipCalculatorActivity
import org.junit.Rule
import org.junit.Test

class TipCalculatorActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(TipCalculatorActivity::class.java)

    @Test
    fun testTipCalculator() {

        // Calculate Tip
        enter(checkAmount = 15.99, tipPercent = 15)
        calculateTip()
        assertOutput(name = "", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

        // Save Tip
        saveTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

        // Clear Outputs
        clearOutputs()
        assertOutput(name = "", checkAmount = "$0.00", tipAmount = "$0.00", total = "$0.00")

        // Load Tip
        loadTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

    }

    private fun enter(checkAmount: Double, tipPercent: Int) {
        onView(withId(R.id.input_check_amount)).perform(replaceText(checkAmount.toString()))
        onView(withId(R.id.input_tip_percentage)).perform(replaceText(tipPercent.toString()))
    }

    private fun calculateTip() {
        onView(withId(R.id.fab)).perform(click())
    }

    private fun assertOutput(name: String, checkAmount: String, tipAmount: String, total: String) {
        onView(withId(R.id.bill_amount)).check(matches(withText(checkAmount)))
        onView(withId(R.id.tip_dollar_amount)).check(matches(withText(tipAmount)))
        onView(withId(R.id.total_dollar_amount)).check(matches(withText(total)))
        onView(withId(R.id.calculation_name)).check(matches((withText(name))))
    }

    private fun clearOutputs() {
        enter(checkAmount = 0.0, tipPercent = 0)
        calculateTip()
    }

    private fun saveTip(name: String) {

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())

        onView(withText(R.string.save)).perform(click())

        onView(withHint(R.string.enter_location)).perform(replaceText(name))

        onView(withText(R.string.save)).perform(click())

    }

    private fun loadTip(name: String) {

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())

        onView(withText(R.string.load)).perform(click())

        onView(withText(name)).perform(click())
    }
}