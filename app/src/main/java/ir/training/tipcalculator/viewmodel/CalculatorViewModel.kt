package ir.training.tipcalculator.viewmodel

import android.app.Application
import ir.training.tipcalculator.R
import ir.training.tipcalculator.model.Calculator
import ir.training.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
    private val app: Application,
    private val calculator: Calculator = Calculator()
) : ObservableViewModel(app) {

    private var lastTipCalculation = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    val outputCheckAmount
        get() = getApplication<Application>().getString(
            R.string.dollar_amount,
            lastTipCalculation.checkAmount
        )
    val outputTipAmount
        get() = getApplication<Application>().getString(
            R.string.dollar_amount,
            lastTipCalculation.tipAmount
        )
    val outputTotallDollarAmount
        get() = getApplication<Application>().getString(
            R.string.dollar_amount,
            lastTipCalculation.grandTotal
        )

    val locationName get() = lastTipCalculation.locationName

    init {
        updateOutPuts(TipCalculation())
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculation.copy(locationName = name)
            calculator.saveTipCalculation(tipToSave)

        updateOutPuts(tipToSave)

    }

    private fun updateOutPuts(tc: TipCalculation) {
        lastTipCalculation = tc
        notifyChange()
    }

    fun calculateTip() {
//        Log.d(TAG, "calculateTip: invoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
//            Log.d(TAG, "checkAmount: $checkAmount & tipPercentage: $tipPct")
            updateOutPuts(calculator.calculateTip(checkAmount, tipPct))
//            clearInputs()
        }

    }

    private fun clearInputs() {
        inputCheckAmount = ""
        inputTipPercentage = ""

    }

}