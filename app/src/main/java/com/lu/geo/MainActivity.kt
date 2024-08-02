package com.lu.geo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"

        const val NO_SELECT = "No Selection"

        //Primary
        const val AREA = "Area"
        const val VOLUME = "Volume"

        //Area
        const val RECT = "Rectangle"
        const val CIRCLE = "Circle"
        const val SQUARE = "Square"
        const val TRIANGLE = "Triangle"

        //Volume
        const val CONE = "Cone"
        const val CUBE = "Cube"
        const val SPHERE = "Sphere"
        const val PYRAMID = "Pyramid"

        const val CHOOSE_OPTION = "Please select an option"
    }

    private val primaryOptions = arrayOf(NO_SELECT, AREA, VOLUME)
    val areaOptions = arrayOf(NO_SELECT, RECT, CIRCLE, SQUARE, TRIANGLE)
    val volumeOptions = arrayOf(NO_SELECT, CUBE,CONE, SPHERE, PYRAMID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        val spinnerPrimary: Spinner = findViewById(R.id.spPrimaryOption)
        val spinnerSecondary: Spinner = findViewById(R.id.spSecondaryOption)

        spinnerPrimary.adapter =
            ArrayAdapter(context, android.R.layout.simple_list_item_1, primaryOptions)

        spinnerPrimary.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                handlePrimarySpinnerSelection(position, context, spinnerSecondary)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinnerSecondary.visibility = View.INVISIBLE
            }
        }
    }

    fun handlePrimarySpinnerSelection(position: Int,
                                      context: Context,
                                      spSecondary: Spinner) {
        val value = primaryOptions[position]
        Log.i(TAG, value)
        //Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
        spSecondary.visibility = View.VISIBLE
        when (value) {
            NO_SELECT -> {
                spSecondary.visibility = View.INVISIBLE
                Toast.makeText(context, CHOOSE_OPTION, Toast.LENGTH_SHORT).show()
            }

            AREA -> {
                spSecondary.adapter =
                    ArrayAdapter(context, android.R.layout.simple_list_item_1, areaOptions)

                spSecondary.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            handleAreaSelection(areaOptions[position], context)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
            }

            VOLUME -> {
                spSecondary.adapter =
                    ArrayAdapter(context, android.R.layout.simple_list_item_1, volumeOptions)

                spSecondary.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            handleVolumeSelection(volumeOptions[position], context)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
            }
        }
    }

    fun handleAreaSelection(textSelected: String, context: Context) {
        if (textSelected == NO_SELECT) {
            Toast.makeText(context, CHOOSE_OPTION, Toast.LENGTH_SHORT).show()
            return
        }
        val intent: Intent = Intent(context, AreaActivity::class.java)
        intent.putExtra("subHeading", textSelected)
        startActivity(intent)
    }

    fun handleVolumeSelection(textSelected: String, context: Context) {
        if (textSelected == NO_SELECT) {
            Toast.makeText(context, CHOOSE_OPTION, Toast.LENGTH_SHORT).show()
            return
        }
        val intent: Intent = Intent(context, VolumeActivity::class.java)
        intent.putExtra("subHeading", textSelected)
        startActivity(intent)
    }

}
