package com.example.project

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    val ages = Array(125, {i -> i})
    val heights = Array(300, {i -> i})
    val weights = Array(450, {i -> i})
    val activityLevels = arrayOf("Sedentary", "Mild", "Moderate", "Heavy", "Extreme")
    val sex = arrayOf("Male", "Female")

    // member variables for UI elements
    private var mFirstNameET: EditText? = null
    private var mLastNameET: EditText? = null
    private var mAgeSpinner: Spinner? = null
    private var mHeightSpinner: Spinner? = null
    private var mWeightSpinner: Spinner? = null
    private var mActivitySpinner: Spinner? = null
    private var mSexSpinner: Spinner? = null
    private var mLocationET: EditText? = null
    private var mLocationButton: Button? = null
    private var mSaveButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // get the UI elements
        mFirstNameET = findViewById(R.id.firstNameET)
        mLastNameET = findViewById(R.id.lastNameET)
        mAgeSpinner = findViewById(R.id.ageSpinner)
        mHeightSpinner = findViewById(R.id.heightSpinner)
        mWeightSpinner = findViewById(R.id.weightSpinner)
        mActivitySpinner = findViewById(R.id.activitySpinner)
        mSexSpinner = findViewById(R.id.sexSpinner)
        mLocationET = findViewById(R.id.locationET)
        mLocationButton = findViewById(R.id.locationButton)
        mSaveButton = findViewById(R.id.saveButton)

        // populate the spinners
        // Creating adapter for spinner
        val ageAdapter= ArrayAdapter(this, android.R.layout.simple_spinner_item, ages)

        // Drop down layout style - list view with radio button
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mAgeSpinner!!.adapter = ageAdapter

    }
}