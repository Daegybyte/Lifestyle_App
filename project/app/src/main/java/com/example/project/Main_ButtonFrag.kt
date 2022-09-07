package com.example.project

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.NonDisposableHandle.parent

@SuppressLint("MissingPermission")
class Main_ButtonFrag : Fragment(), View.OnClickListener {

    private var mHikesButton: Button? = null
    private var mWeatherButton: Button? = null

    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private var mLatitude = 0.0
    private var mLongitude = 0.0

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_button, container, false)

        // get the buttons
        val btnHikes: Button = view.findViewById(R.id.btnHikes)
        Log.d("Main_ButtonFrag", "btnHikes: created successfully")
        val btnWeather: Button = view.findViewById(R.id.btnWeather)
        Log.d("Main_ButtonFrag", "btnWeather: created successfully")
        btnHikes.setOnClickListener(this)
        btnWeather.setOnClickListener(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Main_ButtonFrag", "onViewCreated: called")
        super.onViewCreated(view, savedInstanceState)
        val btnHikes: Button = view.findViewById(R.id.btnHikes)
        val btnWeather: Button = view.findViewById(R.id.btnWeather)

        btnHikes.setOnClickListener{
            val appPerms = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            Log.d("Main_ButtonFrag", "onViewCreated: appPerms: $appPerms")
            activityResultLauncher.launch(appPerms)

            val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
            val cancellationTokenSource = CancellationTokenSource()

            mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
                .addOnSuccessListener { location: Location? ->
                    // getting the last known or current location
                    mLatitude = location!!.latitude
                    mLongitude = location.longitude

                    Toast.makeText(
                        activity,
                        "Latitude:$mLatitude\nLongitude:$mLongitude",
                        Toast.LENGTH_SHORT
                    ).show()

                    val searchUri = Uri.parse("geo:$mLatitude, $mLongitude?q=" + Uri.encode("hiking trails"))
                    Log.d("Main_ButtonFrag", "onViewCreated: searchUri created successfully")

                    //Create the implicit intent
                    val mapIntent = Intent(Intent.ACTION_VIEW, searchUri)
                    Log.d("Main_ButtonFrag", "onViewCreated: mapIntent created successfully")

                    //If there's an activity associated with this intent, launch it
                    try {
                        startActivity(mapIntent)
                        Log.d("Main_ButtonFrag", "onViewCreated: startActivity(mapIntent) called successfully")
                    } catch (ex: ActivityNotFoundException) {
                        Log.d("Main_ButtonFrag", "onViewCreated: startActivity(mapIntent) failed")
                        //handle errors here
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed on getting current location",
                        Toast.LENGTH_SHORT).show()
                }

//            Toast.makeText(
//                activity,
//                "Latitude:$mLatitude\nLongitude:$mLongitude",
//                Toast.LENGTH_SHORT
//            ).show()
//
//            val searchUri = Uri.parse("geo:$mLatitude, $mLongitude?q=" + Uri.encode("hiking trails"))
//            Log.d("Main_ButtonFrag", "onViewCreated: searchUri created successfully")
//
//            //Create the implicit intent
//            val mapIntent = Intent(Intent.ACTION_VIEW, searchUri)
//            Log.d("Main_ButtonFrag", "onViewCreated: mapIntent created successfully")
//
//            //If there's an activity associated with this intent, launch it
//            try {
//                startActivity(mapIntent)
//                Log.d("Main_ButtonFrag", "onViewCreated: startActivity(mapIntent) called successfully")
//            } catch (ex: ActivityNotFoundException) {
//                Log.d("Main_ButtonFrag", "onViewCreated: startActivity(mapIntent) failed")
//                //handle errors here
//            }

        }
        btnWeather.setOnClickListener{
            val appPerms = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            activityResultLauncher.launch(appPerms)

            val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
            val cancellationTokenSource = CancellationTokenSource()

            mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
                .addOnSuccessListener { location: Location? ->
                    // getting the last known or current location
                    mLatitude = location!!.latitude
                    mLongitude = location!!.longitude

                    Toast.makeText(
                        activity,
                        "Latitude:$mLatitude\nLongitude:$mLongitude",
                        Toast.LENGTH_SHORT
                    ).show()

                    val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
                    val weatherIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    //If there's an activity associated with this intent, launch it
                    try {
                        startActivity(weatherIntent)
                        Log.d("Main_ButtonFrag", "onViewCreated: startActivity(weatherIntent) called successfully")
                    } catch (ex: ActivityNotFoundException) {
                        Log.d("Main_ButtonFrag", "onViewCreated: startActivity(weatherIntent) failed")
                        //handle errors here
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed on getting current location",
                        Toast.LENGTH_SHORT).show()
                }

//            Toast.makeText(
//                activity,
//                "Latitude:$mLatitude\nLongitude:$mLongitude",
//                Toast.LENGTH_SHORT
//            ).show()
//
//            val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
//            val weatherIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            //If there's an activity associated with this intent, launch it
//            try {
//                startActivity(weatherIntent)
//                Log.d("Main_ButtonFrag", "onViewCreated: startActivity(weatherIntent) called successfully")
//            } catch (ex: ActivityNotFoundException) {
//                Log.d("Main_ButtonFrag", "onViewCreated: startActivity(weatherIntent) failed")
//                //handle errors here
//            }
        }
    }

    // adapted from https://stackoverflow.com/questions/40760625/how-to-check-permission-in-fragment
    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { result ->
            var allAreGranted = true
            for(b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if(allAreGranted) {
//                val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
//                val cancellationTokenSource = CancellationTokenSource()
//
//                mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
//                    .addOnSuccessListener { location: Location? ->
//                        // getting the last known or current location
//                        mLatitude = location!!.latitude
//                        mLongitude = location!!.longitude
//                    }
//                    .addOnFailureListener {
//                        Toast.makeText(activity, "Failed on getting current location",
//                            Toast.LENGTH_SHORT).show()
//                    }
//
//                Toast.makeText(
//                    activity,
//                    "Latitude:$mLatitude\nLongitude:$mLongitude",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }

    override fun onClick(view: View) {
//        if (ActivityCompat.checkSelfPermission(
//                view.context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                view.context,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
////            ActivityCompat.requestPermissions(this, String[]{ Manifest.permission.ACCESS_FINE_LOCATION}, 0)
//            return
//        }



//        val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
//        val cancellationTokenSource = CancellationTokenSource()
//
//        mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
//            .addOnSuccessListener { location: Location? ->
//                // getting the last known or current location
//                mLatitude = location!!.latitude
//                mLongitude = location!!.longitude
//            }
//            .addOnFailureListener {
//                Toast.makeText(activity, "Failed on getting current location",
//                    Toast.LENGTH_SHORT).show()
//            }
//
//        Toast.makeText(
//            activity,
//            "Latitude:$mLatitude\nLongitude:$mLongitude",
//            Toast.LENGTH_SHORT
//        ).show()

//        WEB coordinates
//        val latitude = 40.767778
//        val longitude = -111.845205

////        new york coordinates
//        val latitude = 40.7128
//        val longitude = -74.0060

//        when (view.id) {
//            R.id.btnHikes -> {
////                val searchUri = Uri.parse("geo:$latitude, $longitude?q=" + Uri.encode("hiking trails"))
//                val searchUri = Uri.parse("geo:$mLatitude, $mLongitude?q=" + Uri.encode("hiking trails"))
//
//                //Create the implicit intent
//                val mapIntent = Intent(Intent.ACTION_VIEW, searchUri)
//
//                //If there's an activity associated with this intent, launch it
//                try {
//                    startActivity(mapIntent)
//                } catch (ex: ActivityNotFoundException) {
//                    //handle errors here
//                }
//            }
//
//            R.id.btnWeather -> {
////                val url = "https://www.timeanddate.com/weather/usa/salt-lake-city"
////                val url = "https://forecast.weather.gov/MapClick.php?textField1=$latitude&textField2=$longitude"
//                val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
//                val weatherIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                //If there's an activity associated with this intent, launch it
//                try {
//                    startActivity(weatherIntent)
//                } catch (ex: ActivityNotFoundException) {
//                    //handle errors here
//                }
//            }
//        }
    }
}