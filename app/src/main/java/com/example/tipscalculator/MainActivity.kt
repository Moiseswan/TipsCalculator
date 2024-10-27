package com.example.tipscalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        if (savedInstanceState != null){
            counter = savedInstanceState.getInt("myCount")
            binding.tiePeople.text = counter.toString()
        }

        binding.btnDecrease.setOnClickListener {
            counter -= 1
            binding.tiePeople.text = counter.toString()
        }
        binding.btnIncrease.setOnClickListener {
            counter +=1
            binding.tiePeople.text = counter.toString()
        }
        binding.btnCalculate.setOnClickListener {

            val totalBillTemp = binding.tieBill.text
            val percentageTemp = binding.tiePercentage.text
            val numPeopleTemp = binding.tiePeople.text

            if (totalBillTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true
                ){
                Snackbar
                    .make(binding.tieBill, "Fill in the empty fields", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val totalBill: Float = totalBillTemp.toString().toFloat()
                val percentage: Int = percentageTemp.toString().toInt()
                val nPeople: Int = numPeopleTemp.toString().toInt()

                val billTemp = totalBill / nPeople
                val tips = billTemp * percentage / 100
                val totalWithTips = billTemp + tips

                binding.tvResult.text = NumberFormat.getCurrencyInstance(Locale("en", "GB")).format(totalWithTips)
                binding.tvBill.text = NumberFormat.getCurrencyInstance(Locale("en", "GB")).format(billTemp)
                binding.tvTip.text = NumberFormat.getCurrencyInstance(Locale("en", "GB")).format(tips)



            }
        }

        binding.btnClean.setOnClickListener {
            binding.tieBill.setText("")
            binding.tiePercentage.setText("")
            binding.tiePeople.text = "0"
            binding.tvResult.text = ""
            binding.tvBill.text = ""
            binding.tvTip.text = ""

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("myCount", counter)
        super.onSaveInstanceState(outState)
    }
}