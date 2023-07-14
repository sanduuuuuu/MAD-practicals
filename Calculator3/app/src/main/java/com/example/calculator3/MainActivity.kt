package com.example.calculator3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    private lateinit var etResult: TextView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etResult = findViewById(R.id.textView)

        val buttons = arrayOf(
            findViewById<Button>(R.id.button41),//0
            findViewById<Button>(R.id.button37),//1
            findViewById<Button>(R.id.button39),//2
            findViewById<Button>(R.id.button36),//3
            findViewById<Button>(R.id.button33),//4
            findViewById<Button>(R.id.button35),//5
            findViewById<Button>(R.id.button32),//6
            findViewById<Button>(R.id.button29),//7
            findViewById<Button>(R.id.button31),//8
            findViewById<Button>(R.id.button28),//9
            findViewById<Button>(R.id.button9),// /
            findViewById<Button>(R.id.button30),// *
            findViewById<Button>(R.id.button34),//-
            findViewById<Button>(R.id.button38),//+
            findViewById<Button>(R.id.button42),//=
            findViewById<Button>(R.id.button40),//.
            findViewById<Button>(R.id.button11)//C
            // Add more buttons for the remaining digits, operators, and clear button
        )

        for (button in buttons) {
            button.setOnClickListener { v -> onButtonClick(v) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when {
            buttonText.isDigitsOnly() -> {
                etResult.append(buttonText)
            }
            buttonText == "." -> {
                if (!etResult.text.contains(".")) {
                    etResult.append(buttonText)
                }
            }
            buttonText == "+" || buttonText == "-" || buttonText == "*" || buttonText == "/" -> {
                operator = buttonText
                operand1 = etResult.text.toString().toDouble()
                etResult.text = ""
            }
            buttonText == "=" -> {
                operand2 = etResult.text.toString().toDouble()
                val result = performOperation(operand1, operand2, operator)
                etResult.setText(result.toString())
            }
            buttonText == "C" -> {
                etResult.text=""
            }
        }
    }

    private fun performOperation(operand1: Double, operand2: Double, operator: String): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}
