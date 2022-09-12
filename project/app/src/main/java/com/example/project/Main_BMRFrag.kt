package com.example.project

import android.os.Bundle
import android.util.Log
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

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        Log.d("Main_BMRFrag", "onCreateView")

        val view = inflater.inflate(R.layout.fragment_main_b_m_r, container, false)
        Log.d("Main_BMRFrag", "onCreateView: view inflated successfully")

        val spinner: Spinner = view.findViewById(R.id.spActivityLevel)
        Log.d("Main_BMRFrag", "onCreateView: spinner found successfully")

        val activityLevels = arrayOf<String?>(alChange, alSedentary, alMild, alModerate, alHeavy, alExtreme)
        Log.d("Main_BMRFrag", "onCreateView: activityLevels array created successfully")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(view.context, R.layout.spinner_list_main, activityLevels)
        Log.d("Main_BMRFrag", "onCreateView: arrayAdapter created successfully")
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list_main)
        spinner.adapter = arrayAdapter

        return view
    }
}