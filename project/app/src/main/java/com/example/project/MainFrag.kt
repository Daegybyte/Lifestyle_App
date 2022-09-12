package com.example.project

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import org.w3c.dom.Text


@SuppressLint("MissingPermission")
class MainFrag : Fragment() {

    //
    private var mActivityLevel: String? = null

    // Strings for the activity levels
    private val alChange: String = "Change Activity Level"
    private var alSedentary: String = "Sedentary (1600 kcal/day)"
    private var alMild: String = "Mild (1800 kcal/day)"
    private var alModerate: String = "Moderate (2000 kcal/day)"
    private var alHeavy: String = "Heavy (2200 kcal/day)"
    private var alExtreme: String = "Extreme (2400 kcal/day)"

    // These will be used to get the phone's location
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude = 0.0
    private var mLongitude = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MainFrag", "onCreateView")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("MainFrag", "onCreateView: view inflated successfully")

        // Get the user info from SharedPreferences
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            // Get the name
            val firstName = sharedPref.getString("firstName", "")
            val lastName = sharedPref.getString("lastName", "")
            val tvUsername: TextView = view.findViewById(R.id.tvUsername)
            // Set the name
            tvUsername.text = "$firstName $lastName"
            // TODO picture
            // Get the activity level
            // TODO
        }

        // Add functionality to the edit profile button
        val btnEditProfile: Button = view.findViewById(R.id.btnEditProfile)

        btnEditProfile.setOnClickListener{
            //TODO switch the fragment
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frag_container, ProfileFrag(), "Profile Fragment")
            transaction.addToBackStack(null)
            transaction.commit()
            Log.d("MainFrag", "onCreateView: edit profile button clicked")
        }


        // Fill the dropdown for activity level
        val spinner: Spinner = view.findViewById(R.id.spActivityLevel)
        Log.d("MainFrag", "onCreateView: spinner found successfully")

        val activityLevels = arrayOf<String?>(alChange, alSedentary, alMild, alModerate, alHeavy, alExtreme)
        Log.d("MainFrag", "onCreateView: activityLevels array created successfully")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(view.context, R.layout.spinner_list_main, activityLevels)
        Log.d("MainFrag", "onCreateView: arrayAdapter created successfully")
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list_main)
        spinner.adapter = arrayAdapter


        // Get the FusedLocationProviderClient for GPS
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.context)

        // Add functionality to the Hikes button
        val btnHikes: Button = view.findViewById(R.id.btnHikes)

        btnHikes.setOnClickListener{
            val appPerms = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            Log.d("MainFrag", "onViewCreated: appPerms: $appPerms")
            activityResultLauncher.launch(appPerms)

            val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
            val cancellationTokenSource = CancellationTokenSource()

            mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
                .addOnSuccessListener { location: Location? ->
                    // getting the last known or current location
                    mLatitude = location!!.latitude
                    mLongitude = location.longitude

                    Toast.makeText(activity, "Latitude:$mLatitude\nLongitude:$mLongitude", Toast.LENGTH_SHORT).show()

//                    // New York coordinates
//                    mLatitude = 40.7128
//                    mLongitude = -74.0060
//
//                    //Moscow coordinates
//                    mLatitude = 55.7558
//                    mLongitude = 37.6173
//
//                    //Auckland coordinates
//                    mLatitude = -36.8485
//                    mLongitude = 174.7633
//
//                    //salt lake city coordinates
//                    mLatitude = 40.7608
//                    mLongitude = -111.8910
//
//                    //rio de janeiro coordinates
//                    mLatitude = -22.9068
//                    mLongitude = -43.1729

                    val searchUri = Uri.parse("geo:$mLatitude, $mLongitude?q=" + Uri.encode("hiking trails"))
                    Log.d("MainFrag", "onViewCreated: searchUri created successfully")

                    //Create the implicit intent
                    val mapIntent = Intent(Intent.ACTION_VIEW, searchUri)
                    Log.d("MainFrag", "onViewCreated: mapIntent created successfully")

                    //If there's an activity associated with this intent, launch it
                    try {
                        startActivity(mapIntent)
                        Log.d("MainFrag", "onViewCreated: startActivity(mapIntent) called successfully")
                    } catch (ex: ActivityNotFoundException) {
                        Log.d("MainFrag", "onViewCreated: startActivity(mapIntent) failed")
                        Toast.makeText(activity, "There's no app that can handle this action.", Toast.LENGTH_SHORT).show()
                        //handle errors here
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed on getting current location", Toast.LENGTH_SHORT).show()
                }
        }

        // Add functionality to the Weather button
        val btnWeather: Button = view.findViewById(R.id.btnWeather)

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

                    Toast.makeText(activity, "Latitude:$mLatitude\nLongitude:$mLongitude", Toast.LENGTH_SHORT).show()

                    val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
                    val weatherIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    //If there's an activity associated with this intent, launch it
                    try {
                        startActivity(weatherIntent)
                        Log.d("Main_ButtonFrag", "onViewCreated: startActivity(weatherIntent) called successfully")
                    } catch (ex: ActivityNotFoundException) {
                        Log.e("Main_ButtonFrag", "onViewCreated: startActivity(mapIntent) failed")
                        //If there's no activity associated with this intent, display an error message
                        Toast.makeText(activity, "No activity found to handle this intent", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed on getting current location", Toast.LENGTH_SHORT).show()
                }
        }

        return view
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

}