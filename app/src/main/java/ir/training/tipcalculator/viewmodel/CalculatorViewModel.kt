package ir.training.tipcalculator.viewmodel

import android.app.Application
import androidx.databinding.BaseObservable
import ir.training.tipcalculator.R
import ir.training.tipcalculator.model.Calculator
import ir.training.tipcalculator.model.TipCalculation

class CalculatorViewModel(val app: Application,val calculator: Calculator = Calculator()) : BaseObservable() {
    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()
    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotallDollarAmount = ""

    init {
        updateOutPuts(TipCalculation())
    }

    private fun updateOutPuts(tc: TipCalculation) {
        outputCheckAmount = app.getString(R.string.dollar_amount,tc.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount,tc.tipAmount)
        outputTotallDollarAmount = app.getString(R.string.dollar_amount,tc.grandTotal)
    }

    fun calculateTip() {
//        Log.d(TAG, "calculateTip: invoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
//            Log.d(TAG, "checkAmount: $checkAmount & tipPercentage: $tipPct")
            updateOutPuts(calculator.calculateTip(checkAmount, tipPct))
            notifyChange()
//            clearInputs()
        }

    }

    private fun clearInputs() {
        inputCheckAmount = ""
        inputTipPercentage = ""

    }

}