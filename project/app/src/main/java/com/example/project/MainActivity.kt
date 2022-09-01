package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    private val alChange: String = "Change Activity Level"
    private var alSedentary: String = "Sedentary (1600 kcal/day)"
    private var alMild: String = "Mild (1800 kcal/day)"
    private var alModerate: String = "Moderate (2000 kcal/day)"
    private var alHeavy: String = "Heavy (2200 kcal/day)"
    private var alExtreme: String = "Extreme (2400 kcal/day)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spActivityLevel)
        val activityLevels = arrayOf<String?>(alChange, alSedentary, alMild, alModerate, alHeavy, alExtreme)
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, activityLevels)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter = arrayAdapter
    }
}

//val spinner: Spinner = findViewById(R.id.spActivityLevel)
//// Create an ArrayAdapter using the string array and a default spinner layout
//ArrayAdapter.createFromResource(
//this,
//R.array.activityLevelsArray,
//android.R.layout.simple_spinner_item
//).also { adapter ->
//    // Specify the layout to use when the list of choices appears
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//    // Apply the adapter to the spinner
//    spinner.adapter = adapter
//}