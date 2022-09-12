package com.example.project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment


class ProfileFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val npAge : NumberPicker = view.findViewById(R.id.np_age)
        npAge.minValue = 12
        npAge.maxValue = 99
        npAge.value = 25
        npAge.wrapSelectorWheel = false

        val npHeight : NumberPicker = view.findViewById(R.id.np_height)
        npHeight.minValue = 140
        npHeight.maxValue = 220
        npHeight.value = 160
        npHeight.wrapSelectorWheel = false

        val npWeight : NumberPicker = view.findViewById(R.id.np_weight)
        npWeight.minValue = 45
        npWeight.maxValue = 150
        npWeight.value = 75
        npWeight.wrapSelectorWheel = false

        val spActivityLvl: Spinner = view.findViewById(R.id.spActivityLevel)
        val activityLevels = arrayOf<String?>("Sedentary", "Mild", "Moderate", "Heavy", "Extreme")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(view.context, R.layout.spinner_list_profile, activityLevels)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list_profile)
        spActivityLvl.adapter = arrayAdapter


        // TODO -- use this in listener to get radio button value (it is returned as an Int)
        val rgSex: RadioGroup = view.findViewById(R.id.radio_sex)

        // TODO -- use this to change value of numberpicker stored in member variable
//                np.setOnValueChangedListener { picker, oldVal, newVal ->
//            val text = "Changed from $oldVal to $newVal"
//        }

        // Get the SharedPreferences to read from/store in
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Check to see if there is user info in the SharedPreferences
        if (sharedPref != null) {
            // Get the name
            val firstName = sharedPref.getString("firstName", "")
            val lastName = sharedPref.getString("lastName", "")
            // Set the name
            val etFirstName: EditText = view.findViewById(R.id.etFirstName)
            val etLastName: EditText = view.findViewById(R.id.etLastName)
            etFirstName.setText(firstName)
            etLastName.setText(lastName)
            // Get the age
            val age = sharedPref.getInt("age", 0)
            //set the age
            npAge.value = age

            // Get the height
            val height = sharedPref.getInt("height", 0)
            //set the height
            npHeight.value = height

            // Get the weight
            val weight = sharedPref.getInt("weight", 0)
            //set the weight
            npWeight.value = weight

            // Get the activity level
            val activityLevel = sharedPref.getInt("activityLevel", 2)
            //set the activity level
            spActivityLvl.setSelection(activityLevel)

            val radioMale: RadioButton = view.findViewById(R.id.radio_male)
            radioMale.isChecked = sharedPref.getBoolean("isMale", true)
            val radioFemale: RadioButton = view.findViewById(R.id.radio_female)
            radioFemale.isChecked = !sharedPref.getBoolean("isMale", false)
        }

        // Adding functionality to the save button
        val saveButton: Button = view.findViewById(R.id.btnSave)
        saveButton.setOnClickListener{
            with (sharedPref!!.edit()) {
                val etFirstName: EditText = view.findViewById(R.id.etFirstName)
                putString("firstName", etFirstName.text.toString())
                val etLastName: EditText = view.findViewById(R.id.etLastName)
                putString("lastName", etLastName.text.toString())
                putInt("age", npAge.value)
                putInt("height", npHeight.value)
                putInt("weight", npWeight.value)
                putInt("activityLevel", spActivityLvl.selectedItemPosition)
                // get selected radio button from radioGroup
                val radioButton: RadioButton = view.findViewById(rgSex.checkedRadioButtonId)
                // store a boolean (for less space + ease) representing whether they are male or not
                putBoolean("isMale", radioButton.text.toString() == "Male")
                apply()
            }
        }

        return view
    }

}