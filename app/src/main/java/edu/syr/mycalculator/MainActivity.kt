package edu.syr.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    var firstZero : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        firstZero = false
        if (tvInput.text == "0"){
            tvInput.text = ((view as Button).text)
        }
        else {
            tvInput.append((view as Button).text)
        }
        lastNumeric = true
    }

    fun onClear(view: View){
        tvInput.text = "0"
        firstZero = true
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)

                }

                if (tvValue.contains("-")){
                    //split string
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0] // 77
                    var two = splitValue[1] // 2
                    // 77 - 2

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                else if (tvValue.contains("+")){
                    //split string
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0] // 77
                    var two = splitValue[1] // 2
                    // 77 + 2

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }

                else if (tvValue.contains("*")){
                    //split string
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0] // 77
                    var two = splitValue[1] // 2
                    // 77 * 2

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

                else if (tvValue.contains("/")){
                    //split string
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0] // 77
                    var two = splitValue[1] // 2
                    // 77 / 2

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
            // 99.0 -> 99
        }
        return value
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        } else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}