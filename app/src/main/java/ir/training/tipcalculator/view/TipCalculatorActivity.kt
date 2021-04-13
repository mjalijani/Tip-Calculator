package ir.training.tipcalculator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ir.training.tipcalculator.R
import ir.training.tipcalculator.databinding.ActivityTipCalculatorBinding
import ir.training.tipcalculator.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityTipCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)
        binding.vm = CalculatorViewModel()
        setSupportActionBar(binding.toolbar)

    }

}