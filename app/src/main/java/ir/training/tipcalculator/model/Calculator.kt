package ir.training.tipcalculator.model

import androidx.lifecycle.LiveData
import java.math.RoundingMode

class Calculator(private val repository: TipCalculationRepository = TipCalculationRepository()) {
    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation {

        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.0))
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
            locationName = "",
            checkAmount,
            tipPct,
            tipAmount,
            grandTotal
        )
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTips(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTips()
    }
}
