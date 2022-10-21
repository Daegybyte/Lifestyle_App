package com.example.project

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.lang.System.currentTimeMillis

class MainActivity : FragmentActivity() {

    private lateinit var mSensorManager: SensorManager
    private var mGyroscope: Sensor? = null
    private val mThreshold = 5

    private var lastRotate = currentTimeMillis()
    private var cooldown = 1000; //wait 1S between shakes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    private val rotateListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {

            //Get the rotation rates along the x,y and z axes
//            val xrot = sensorEvent.values[0].toDouble()
            val yrot = sensorEvent.values[1].toDouble()
//            val zrot = sensorEvent.values[2].toDouble()

            //Compute the squared magnitude of the rotation.  No reason for us to take the sqrt
//            val sq_magnitude = Math.pow(xrot, 2.0) + Math.pow(yrot, 2.0) + Math.pow(zrot, 2.0)
            val sq_magnitude = Math.pow(yrot, 2.0)

            //this is another way of measuring "magnitude" called the "L1 Norm"
            //val magnitude = Math.max(Math.max(Math.abs(xrot), Math.abs(yrot)), Math.abs(zrot))

            //Check if cooldown has passed and squared magnitude is greater than some threshold
            var now = currentTimeMillis()
            if ((now >= (lastRotate + cooldown)) && (sq_magnitude > mThreshold)) {
                switchFragment()
                lastRotate = now
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {
        }
    }

    private fun getCurrentFragment(): Fragment {
        return supportFragmentManager.fragments.last()
    }

    private fun switchFragment() {
        when (getCurrentFragment()) {
            is MainFrag -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frag_container, StepsFrag(), "Steps Fragment")
                transaction.addToBackStack(null)
                transaction.commit()
            }

            is StepsFrag -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frag_container, MainFrag(), "Main Fragment")
                transaction.addToBackStack(null)
                transaction.commit()
            }

            is ProfileFrag -> {}
        }

    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(
            rotateListener,
            mGyroscope,
            SensorManager.SENSOR_DELAY_NORMAL
        )

    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(rotateListener)
    }
}