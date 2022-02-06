package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip()
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent_option -> 0.20
            R.id.eighteen_percent_option -> 0.18
            R.id.fifteen_percent_option -> 0.15
            else -> 0.10
        }

        var tip = tipPercentage * cost

        if(binding.roundUpSwitch.isChecked){ tip = kotlin.math.ceil(tip) }

        displayTip(tip)
    }

    private fun displayTip(tip: Double = 0.0) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_calculated_amount, formattedTip)
    }
}