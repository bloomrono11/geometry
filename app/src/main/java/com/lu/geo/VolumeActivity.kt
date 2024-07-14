package com.lu.geo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lu.geo.MainActivity.Companion.CONE
import com.lu.geo.MainActivity.Companion.CUBE
import com.lu.geo.MainActivity.Companion.PYRAMID
import com.lu.geo.MainActivity.Companion.SPHERE
import org.w3c.dom.Text
import kotlin.math.pow

class VolumeActivity : AppCompatActivity() {
    companion object {
        const val TAG = "VolumeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume)

        val volumeSubHead: TextView = findViewById(R.id.volumeSubheading)
        volumeSubHead.text = intent.getStringExtra("subHeading")

        Log.i(TAG, "data received for sub heading" + volumeSubHead.text)

        val volumeBtn: Button = findViewById(R.id.volumeCalBtn)
        val volumeResultTV: TextView = findViewById(R.id.volumeResultTV)

        val edTxt1: EditText = findViewById(R.id.edTxtVolume1)
        val edTxt2: EditText = findViewById(R.id.edTxtVolume2)
        val edTxt3: EditText = findViewById(R.id.edTxtVolume3)

        val formulaTV: TextView = findViewById(R.id.volumeFormulaTV)

        when (volumeSubHead.text) {
            CUBE -> {
                formulaTV.text = "Volume = a^3 where a is side"
                edTxt2.visibility = View.INVISIBLE
                edTxt3.visibility = View.INVISIBLE
            }
            SPHERE -> {
                formulaTV.text = "Volume = (4/3)*π*r^3 where r is radius"
                edTxt2.visibility = View.INVISIBLE
                edTxt3.visibility = View.INVISIBLE
            }

            CONE -> {
                formulaTV.text = "Volume = (1/3)*π*(r^2)*h where r is radius and h is height"
                edTxt3.visibility = View.VISIBLE
                edTxt2.visibility = View.INVISIBLE
            }

            PYRAMID -> {
                formulaTV.text = "Volume = l*b*h/3 where l is length, b is breadth and h is height"
                edTxt2.visibility = View.VISIBLE
                edTxt3.visibility = View.VISIBLE
            }

        }

        volumeBtn.setOnClickListener {
            val side = edTxt1.text.toString().toDouble()

            when (volumeSubHead.text) {
                CONE -> {
                    val height = edTxt3.text.toString().toFloat()
                    val volume = (Math.PI * side.pow(2) * height) / 3
                    volumeResultTV.text = "Cone volume is " + String.format("%.2f", volume) + " cubic units"
                }

                CUBE -> {
                    val volume = side.pow(3.0)
                    volumeResultTV.text = "Cube volume is " + String.format("%.2f", volume) + " cubic units"
                }

                SPHERE -> {
                    val volume = 4 / 3 * Math.PI * side.pow(3)
                    volumeResultTV.text = "Sphere volume is " + String.format("%.2f", volume) + " cubic units"
                }

                PYRAMID -> {
                    val width = edTxt2.text.toString().toFloat()
                    val height = edTxt3.text.toString().toFloat()
                    val volume = (side * height * width) / 3
                    volumeResultTV.text = "Pyramid volume is " + String.format("%.2f", volume) + " cubic units"
                }

            }
        }
    }
}
