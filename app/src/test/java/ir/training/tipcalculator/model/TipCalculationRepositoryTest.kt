package ir.training.tipcalculator.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {
    lateinit var repository: TipCalculationRepository

    @get:Rule
    var rule : TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation(
            "kfc",
            100.0,
            25,
            25.0,
            125.0
        )
        repository.saveTipCalculation(tip)
        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testLoadSavedTipCalculations(){
        val tip1 = TipCalculation(
            "ifc",
            100.0,
            25,
            25.0,
            125.0
        )
        val tip2 = TipCalculation(
            "kfc",
            100.0,
            25,
            25.0,
            125.0
        )
        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)
        repository.loadSavedTips().observeForever{tips->
            assertEquals(2, tips?.size)
        }

    }
}