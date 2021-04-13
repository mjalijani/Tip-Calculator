package ir.training.tipcalculator.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import ir.training.tipcalculator.model.Calculator
import ir.training.tipcalculator.model.TipCalculation

class CalculatorViewModel(val calculator: Calculator = Calculator()) {
    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()

    fun calculateTip() {
        Log.d(TAG, "calculateTip: invoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
        }

    }

}