package com.example.project

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import kotlin.math.roundToInt

class StepsFrag : Fragment(), View.OnClickListener {

    private lateinit var mSensorManager: SensorManager
    private lateinit var mTvSteps: TextView
    private var mStepCounter: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_steps, container, false)

//        // set the swipe listener
//        view.setOnTouchListener(object: OnSwipeTouchListener(context) {
//            override fun onSwipeUp() {
//                super.onSwipeUp()
//                Toast.makeText(activity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            override fun onSwipeDown() {
//                super.onSwipeDown()
//                Toast.makeText(activity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            override fun onSwipeRight() {
//                super.onSwipeRight()
//                Toast.makeText(activity, "Swiped right!", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSwipeLeft() {
//                super.onSwipeLeft()
//                Toast.makeText(activity, "Swiped left!", Toast.LENGTH_SHORT).show()
//            }
//        })

        mTvSteps = view.findViewById(R.id.tvSteps)

        mSensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        when {
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED -> {
                    mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACTIVITY_RECOGNITION) -> {
                Toast.makeText(activity, "Please Turn On Activity Recognition", Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
            }
        }

        val btnBack: Button = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener(this)

        return view
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
    {isGranted: Boolean ->
        if (isGranted) {
            mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        }
        else {
            Toast.makeText(activity, "Please Turn On Activity Recognition", Toast.LENGTH_SHORT).show()
        }
    }

    private val stepListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            mTvSteps.text = "${sensorEvent.values[0].roundToInt()}"
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    }

    override fun onResume() {
        super.onResume()
        if(mStepCounter != null){
            registerStepListener()
        }
    }

    private fun registerStepListener() {
        mSensorManager.registerListener(
            stepListener,
            mStepCounter,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        if(mStepCounter != null) {
            mSensorManager.unregisterListener(stepListener)
        }
    }

    override fun onClick(view: View) {
        /**
         * TODO: Save the current step count to the DB
         */

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frag_container, MainFrag(), "Main Fragment")
        transaction.addToBackStack(null)
        transaction.commit()
    }
}