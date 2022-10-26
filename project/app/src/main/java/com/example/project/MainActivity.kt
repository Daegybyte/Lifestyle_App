package com.example.project

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.StorageException
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.amplifyframework.storage.result.StorageDownloadFileResult
import com.amplifyframework.storage.result.StorageTransferProgress
import com.amplifyframework.storage.result.StorageUploadFileResult
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.System.currentTimeMillis
import kotlin.math.roundToInt


class MainActivity : FragmentActivity() {

    private val mSharedViewModel: SharedViewModel by viewModels {
        SharedViewModelFactory((this.application as App).repository)
    }

    private lateinit var mSensorManager: SensorManager
    private var mStepCounter: Sensor? = null

    private var mGyroscope: Sensor? = null
    private val mThreshold = 5

    private var lastRotate = currentTimeMillis()
    private var cooldown = 1000; //wait 1S between shakes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        // set the swipe listener
        val view: View = findViewById(R.id.entire_view)
        view.setOnTouchListener(object: OnSwipeTouchListener(this) {
            override fun onSwipeRight() {
                super.onSwipeRight()
//                Toast.makeText(this@MainActivity, "Swiped right!", Toast.LENGTH_SHORT).show()
                if(getCurrentFragment() is MainFrag) {
                    if(!mSharedViewModel.getCounterOn()) {
                        when {
                            ActivityCompat.checkSelfPermission(
                                this@MainActivity,
                                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED -> {
                                startCounter()
                            }
                            shouldShowRequestPermissionRationale(Manifest.permission.ACTIVITY_RECOGNITION) -> {
                                Toast.makeText(this@MainActivity, "Please Turn On Activity Recognition", Toast.LENGTH_SHORT).show()
                                stepRequestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                            }
                            else -> {
                                stepRequestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                            }
                        }
                    }
                }
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
//                Toast.makeText(this@MainActivity, "Swiped left!", Toast.LENGTH_SHORT).show()

                if(getCurrentFragment() is MainFrag) {
                    if (mSharedViewModel.getCounterOn()) {
                        stopCounter()
//                        mSensorManager.unregisterListener(stepListener)
//                        MediaPlayer.create(this@MainActivity, Settings.System.DEFAULT_ALARM_ALERT_URI).start()
//                        mainFrag.counterOff()
                    }
                }
            }
        })

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
            Amplify.Auth.signInWithWebUI(
                this,
                { result: AuthSignInResult -> Log.i("AuthQuickStart", result.toString()) },
                { error: AuthException -> Log.e("AuthQuickStart", error.toString()) }
            )
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }

    }

    private fun startCounter() {
//        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        mSharedViewModel.setCounterOn(true)
        registerStepListener()
        MediaPlayer.create(this@MainActivity, Settings.System.DEFAULT_NOTIFICATION_URI).start()
        val mainFrag = getCurrentFragment() as MainFrag
        mainFrag.counterOn()
    }

    private fun stopCounter() {
        mSensorManager.unregisterListener(stepListener)
        mSharedViewModel.setCounterOn(false)
        MediaPlayer.create(this@MainActivity, Settings.System.DEFAULT_NOTIFICATION_URI).start()
        val mainFrag = getCurrentFragment() as MainFrag
        mainFrag.counterOff()
    }

    private val stepRequestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
    {isGranted: Boolean ->
        if (isGranted) {
//            mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
//            mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
//            registerStepListener()
            startCounter()
        }
        else {
            Toast.makeText(this, "Please Turn On Activity Recognition", Toast.LENGTH_SHORT).show()
        }
    }

    private val stepListener: SensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(sensorEvent: SensorEvent) {

            val curNumSteps = mSharedViewModel.getNumSteps()

            // get all the current user info
            val user: User? = mSharedViewModel.userInfo.value

            // increment the step counter
            val steps = sensorEvent.values[0].roundToInt()
            user!!.steps = curNumSteps + steps

            // save the change to the step numbers
            mSharedViewModel.updateUser(user)
            mSharedViewModel.setNumSteps(steps)

            if(getCurrentFragment() is MainFrag) {
                val mainFrag = getCurrentFragment() as MainFrag
                mainFrag.updateStepCounter(steps)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    }


    private val rotateListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {


            //Get the rotation rates along the x,y and z axes
//            val xrot = sensorEvent.values[0].toDouble()
//            val yrot = sensorEvent.values[1].toDouble()
//            val zrot = sensorEvent.values[2].toDouble()
            // Get the rotation rate from the appropriate axis (depending on device orientation)
            val rot =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) sensorEvent.values[1].toDouble() else sensorEvent.values[0].toDouble()

            //Compute the squared magnitude of the rotation.  No reason for us to take the sqrt
//            val sq_magnitude = Math.pow(xrot, 2.0) + Math.pow(yrot, 2.0) + Math.pow(zrot, 2.0)
            val sq_magnitude = Math.pow(rot, 2.0)
//            val sq_magnitude = Math.pow(yrot, 2.0)


            //this is another way of measuring "magnitude" called the "L1 Norm"
            //val magnitude = Math.max(Math.max(Math.abs(xrot), Math.abs(yrot)), Math.abs(zrot))

            //Check if cooldown has passed and squared magnitude is greater than some threshold
            var now = currentTimeMillis()
            if ((now >= (lastRotate + cooldown)) && (sq_magnitude > mThreshold)) {
//                switchFragment()
                if (!mSharedViewModel.getCounterOn()) startCounter() else stopCounter()
                lastRotate = now
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {
        }
    }

    private fun getCurrentFragment(): Fragment {
        return supportFragmentManager.fragments.last()
    }

//    private fun switchFragment() {
//        when (getCurrentFragment()) {
//            is MainFrag -> {
//                val transaction = supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.frag_container, StepsFrag(), "Steps Fragment")
//                transaction.addToBackStack(null)
//                transaction.commit()
//            }
//
//            is StepsFrag -> {
//                val transaction = supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.frag_container, MainFrag(), "Main Fragment")
//                transaction.addToBackStack(null)
//                transaction.commit()
//            }
//
//            is ProfileFrag -> {}
//        }
//
//    }

    override fun onResume() {
        super.onResume()
        if (mSharedViewModel.getCounterOn()){
            registerStepListener()
        }

        mSensorManager.registerListener(
            rotateListener,
            mGyroscope,
            SensorManager.SENSOR_DELAY_NORMAL
        )

    }

    private fun registerStepListener(){
        mSensorManager.registerListener(
            stepListener,
            mStepCounter,
            SensorManager.SENSOR_DELAY_NORMAL,
            0
        )
//        mSensorManager.registerListener(
//            stepListener,
//            mStepCounter,
//            SensorManager.SENSOR_DELAY_NORMAL
//        )
    }

    override fun onPause() {
        super.onPause()
        if (mSharedViewModel.getCounterOn()){
            mSensorManager.unregisterListener(stepListener)
        }

        mSensorManager.unregisterListener(rotateListener)
    }
    
    private fun uploadFile() {
        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
        try {
            val writer = BufferedWriter(FileWriter(exampleFile))
            writer.append("Example file contents")
            writer.close()
        } catch (exception: Exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception)
        }
        Amplify.Storage.uploadFile(
            "ExampleKey",
            exampleFile,
            { result: StorageUploadFileResult ->
                Log.i(
                    "MyAmplifyApp",
                    "Successfully uploaded: " + result.key
                )
            },
            { storageFailure: StorageException? ->
                Log.e(
                    "MyAmplifyApp",
                    "Upload failed",
                    storageFailure
                )
            }
        )
    }

    private fun downloadFile() {
        Amplify.Storage.downloadFile(
            "ExampleKey",
            File(applicationContext.filesDir.toString() + "/download.txt"),
            StorageDownloadFileOptions.defaultInstance(),
            { progress: StorageTransferProgress ->
                Log.i(
                    "MyAmplifyApp",
                    "Fraction completed: " + progress.fractionCompleted
                )
            },
            { result: StorageDownloadFileResult ->
                Log.i(
                    "MyAmplifyApp",
                    "Successfully downloaded: " + result.file.name
                )
            },
            { error: StorageException? -> Log.e("MyAmplifyApp", "Download Failure", error) }
        )
    }
}