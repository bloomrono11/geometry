package com.lu.geo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lu.geo.MainActivity.Companion.CIRCLE
import com.lu.geo.MainActivity.Companion.RECT
import com.lu.geo.MainActivity.Companion.SQUARE
import com.lu.geo.MainActivity.Companion.TRIANGLE

class AreaActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AreaActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)

        val areaSubHead: TextView = findViewById(R.id.areaSubheading)
        areaSubHead.text = intent.getStringExtra("subHeading")

        Log.i(TAG, "data received for sub heading" + areaSubHead.text)

        val areaBtn: Button = findViewById(R.id.areaCalBtn)
        val areaResultTV: TextView = findViewById(R.id.areaResultTV)

        val edTxt1: EditText = findViewById(R.id.edTxtArea1)
        val edTxt2: EditText = findViewById(R.id.edTxtArea2)

        val formulaTV: TextView = findViewById(R.id.areaFormulaTV)

        when (areaSubHead.text) {
            RECT->{
                formulaTV.text = "Area = l * b where l is length and b is breadth"
                edTxt2.visibility = View.VISIBLE
            }
            TRIANGLE -> {
                formulaTV.text = "Area = 0.5*b*h where b is base and h is height"
                edTxt2.visibility = View.VISIBLE
            }
            SQUARE -> {
                formulaTV.text = "Area = a^2 where a is side"
                edTxt2.visibility = View.INVISIBLE
            }
            CIRCLE -> {
                formulaTV.text = "Area = π*r^2 where r is radiuus"
                edTxt2.visibility = View.INVISIBLE
            }
        }

        areaBtn.setOnClickListener {
            val side = edTxt1.text.toString().toFloat()

            when (areaSubHead.text) {
                RECT -> {
                    val breadth = edTxt2.text.toString().toFloat()
                    val area = side * breadth
                    areaResultTV.text = "Reactable area is " + String.format("%.2f", area) + " sq units"
                }

                SQUARE -> {
                    val area = side * side
                    areaResultTV.text = "Square area is " + String.format("%.2f", area) + " sq units"
                }

                TRIANGLE -> {
                    val height = edTxt2.text.toString().toFloat()
                    val area = 0.5 * side * height
                    areaResultTV.text = "Triangle area is " + String.format("%.2f", area) + " sq units"
                }

                CIRCLE -> {
                    val area = Math.PI * side * side
                    areaResultTV.text = "Circle area is " + String.format("%.2f", area) + " sq units"
                }
            }
        }
    }

}
