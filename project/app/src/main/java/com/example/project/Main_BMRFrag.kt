package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

class Main_BMRFrag : Fragment() {

    private val alChange: String = "Change Activity Level"
    private var alSedentary: String = "Sedentary (1600 kcal/day)"
    private var alMild: String = "Mild (1800 kcal/day)"
    private var alModerate: String = "Moderate (2000 kcal/day)"
    private var alHeavy: String = "Heavy (2200 kcal/day)"
    private var alExtreme: String = "Extreme (2400 kcal/day)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val spinner: Spinner = findViewById(R.id.spActivityLevel)
        val activityLevels = arrayOf<String?>(alChange, alSedentary, alMild, alModerate, alHeavy, alExtreme)
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, activityLevels)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter = arrayAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_b_m_r, container, false)
    }
}