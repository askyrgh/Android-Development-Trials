package com.royakash.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var lastNumeric: Boolean = false
    private var lastDecimal: Boolean = false

    private var tvInput: TextView? = null

    var btn_zero: Button? = null
    var btn_one: Button? = null
    var btn_two: Button? = null
    var btn_three: Button? = null
    var btn_four: Button? = null
    var btn_five: Button? = null
    var btn_six: Button? = null
    var btn_seven: Button? = null
    var btn_eight: Button? = null
    var btn_nine: Button? = null
    var btn_add: Button? = null
    var btn_subtract: Button? = null
    var btn_multiply: Button? = null
    var btn_divide: Button? = null
    var btn_equals: Button? = null
    var btn_clear: Button? = null
    var btn_decimal: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tv_input)

        specifyButtons()
    }

    fun specifyButtons() {
        btn_zero = findViewById(R.id.btn_zero)
        btn_one = findViewById(R.id.btn_one)
        btn_two = findViewById(R.id.btn_two)
        btn_three = findViewById(R.id.btn_three)
        btn_four = findViewById(R.id.btn_four)
        btn_five = findViewById(R.id.btn_five)
        btn_six = findViewById(R.id.btn_six)
        btn_seven = findViewById(R.id.btn_seven)
        btn_eight = findViewById(R.id.btn_eight)
        btn_nine = findViewById(R.id.btn_nine)
        btn_add = findViewById(R.id.btn_add)
        btn_subtract = findViewById(R.id.btn_subtract)
        btn_multiply = findViewById(R.id.btn_multiply)
        btn_divide = findViewById(R.id.btn_divide)
        btn_equals = findViewById(R.id.btn_equals)
        btn_clear = findViewById(R.id.btn_clear)
        btn_decimal = findViewById(R.id.btn_decimal)

        btn_zero?.setOnClickListener{ onDigitClick(btn_zero) }
        btn_one?.setOnClickListener{ onDigitClick(btn_one) }
        btn_two?.setOnClickListener{ onDigitClick(btn_two) }
        btn_three?.setOnClickListener{ onDigitClick(btn_three) }
        btn_four?.setOnClickListener{ onDigitClick(btn_four) }
        btn_five?.setOnClickListener{ onDigitClick(btn_five) }
        btn_six?.setOnClickListener{ onDigitClick(btn_six) }
        btn_seven?.setOnClickListener{ onDigitClick(btn_seven) }
        btn_eight?.setOnClickListener{ onDigitClick(btn_eight) }
        btn_nine?.setOnClickListener{ onDigitClick(btn_nine) }

        btn_decimal?.setOnClickListener{onDecimalClick()}

        btn_clear?.setOnClickListener{ onClearClick()}

        btn_equals?.setOnClickListener{ onEqualClick()}

        btn_add?.setOnClickListener{ onOperatorClick(btn_add)}
        btn_subtract?.setOnClickListener{ onOperatorClick(btn_subtract)}
        btn_multiply?.setOnClickListener{ onOperatorClick(btn_multiply)}
        btn_divide?.setOnClickListener{ onOperatorClick(btn_divide)}
    }

    fun onDigitClick(view: View?) {
//        Toast.makeText(this, "Button Click", Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)
        lastNumeric = true
//        lastDecimal = false
    }

    fun onDecimalClick() {
        if(lastNumeric && !lastDecimal) {
            tvInput?.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    fun onOperatorClick(view: View?) {
         tvInput?.text?.let {
             if(lastNumeric && !isOperatorEntered(it.toString())) {
                 tvInput?.append((view as Button).text)
                 lastNumeric = false
                 lastDecimal = false
             }
         }
    }

    fun isOperatorEntered(value: String) : Boolean {
        return if(value.startsWith("-")) {
            false
        }
        else {
            value.contains("+")
                    || value.contains("-")
                    || value.contains("*")
                    || value.contains("/")
        }
    }

    fun onEqualClick() {
        if(lastNumeric) {
            var tvVal =  tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvVal.startsWith("-")) {
                    prefix = "-"
                    tvVal = tvVal.substring(1)
                }
/*
                if(tvVal.contains("+")) {
                    val splitVal = tvVal.split("+")

                    var operandOne = if(prefix.isEmpty()) splitVal[0] else (prefix + splitVal[0])
                    var operandTwo = splitVal[1]
                    var result = (operandOne.toDouble() + operandTwo.toDouble())

                    tvInput?.text = result.toString()
                }
                else if(tvVal.contains("-")) {
                    val splitVal = tvVal.split("-")

                    var operandOne = if(prefix.isEmpty()) splitVal[0] else (prefix + splitVal[0])
                    var operandTwo = splitVal[1]
                    var result = (operandOne.toDouble() - operandTwo.toDouble())

                    tvInput?.text = result.toString()
                }
                else if(tvVal.contains("*")) {
                    val splitVal = tvVal.split("*")

                    var operandOne = if(prefix.isEmpty()) splitVal[0] else (prefix + splitVal[0])
                    var operandTwo = splitVal[1]
                    var result = (operandOne.toDouble() * operandTwo.toDouble())

                    tvInput?.text = result.toString()
                }
                else if(tvVal.contains("/")) {
                    val splitVal = tvVal.split("/")

                    var operandOne = if(prefix.isEmpty()) splitVal[0] else (prefix + splitVal[0])
                    var operandTwo = splitVal[1]
                    var result = (operandOne.toDouble() / operandTwo.toDouble())

                    tvInput?.text = result.toString()
                }
 */
                if(tvVal.contains("+")) {
                    arithmeticOperation(tvVal, prefix, "+")
                }
                else if(tvVal.contains("-")) {
                    arithmeticOperation(tvVal, prefix, "-")
                }
                else if(tvVal.contains("*")) {
                    arithmeticOperation(tvVal, prefix, "*")
                }
                else if(tvVal.contains("/")) {
                    arithmeticOperation(tvVal, prefix, "/")
                }

            }
            catch(e: ArithmeticException) {
                // catch arithmetic exceptions like division by 0
                e.printStackTrace()
            }
        }
    }

    fun arithmeticOperation(tvVal: String, prefix: String, operator: String) {
        val splitVal = tvVal.split(operator)

        var operandOne = if(prefix.isEmpty()) splitVal[0] else (prefix + splitVal[0])
        var operandTwo = splitVal[1]
        var result = 0.00
        when (operator) {
            "+" -> {
                result = (operandOne.toDouble() + operandTwo.toDouble())
            }
            "-" -> {
                result = (operandOne.toDouble() - operandTwo.toDouble())
            }
            "*" -> {
                result = (operandOne.toDouble() * operandTwo.toDouble())
            }
            "/" -> {
                result = (operandOne.toDouble() / operandTwo.toDouble())
            }
        }

        tvInput?.text = removeZeroAfterDecimal(result.toString())
    }

    fun onClearClick() {
        tvInput?.text = ""
        lastDecimal = false
        lastNumeric = false
    }

    private fun removeZeroAfterDecimal(result: String) : String {
        var value = result
        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }
}