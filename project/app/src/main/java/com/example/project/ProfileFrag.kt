package com.example.project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


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
//        val rgSex : RadioGroup = view.findViewById(R.id.radio_sex)
//        Int selectedSexID = rgSex.checkedRadioButtonId

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
        }

        // Adding functionality to the save button
        val saveButton: Button = view.findViewById(R.id.btnSave)
        saveButton.setOnClickListener{
            with (sharedPref!!.edit()) {
                val etFirstName: EditText = view.findViewById(R.id.etFirstName)
                putString("firstName", etFirstName.text.toString())
                val etLastName: EditText = view.findViewById(R.id.etLastName)
                putString("lastName", etLastName.text.toString())
                apply()
            }
        }

        return view
    }

}