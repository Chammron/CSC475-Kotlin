package com.cameron.poundstostonesconverter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PoundsToStonesConverter)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //UI references.
        val etPounds: EditText = findViewById(R.id.etPounds)
        val btnConvert: Button = findViewById(R.id.btnConvert)
        val tvResult: TextView = findViewById(R.id.tvResult)
        val tvError: TextView = findViewById(R.id.tvError)

        btnConvert.setOnClickListener {
            val inputText = etPounds.text.toString()

            if (inputText.isEmpty() || inputText.toDoubleOrNull() == null) {
                tvError.text = "Please enter a valid number."
                tvError.visibility = TextView.VISIBLE
                tvResult.text = ""
            } else {
                try {
                    //Makes input a decimal
                    val pounds = inputText.toDouble()
                    //Pounds to stones conversion formula.
                    val stones = pounds / 14
                    tvResult.text = String.format("%.2f stones", stones)
                    tvError.visibility = TextView.GONE
                } catch (e: NumberFormatException) {
                    tvError.text = "Please enter a valid number."
                    tvError.visibility = TextView.VISIBLE
                    tvResult.text = ""
                }
            }
        }
    }
}