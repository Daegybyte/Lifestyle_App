package com.example.project

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.example.project.NetworkUtils.buildURLFromString
import com.example.project.NetworkUtils.getDataFromURL
import org.json.JSONObject
import kotlin.math.roundToInt


@SuppressLint("MissingPermission")
class MainFrag : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    // A SharedPreferences instance to be used in many places to store and load user info
    private var mSharedPref: SharedPreferences? = null

    // String Array for the activity levels
    private var mActivityLevels = arrayOf<String?>(
        "Change Activity Level",
        "Sedentary",
        "Mild",
        "Moderate",
        "Heavy",
        "Extreme"
    )

    // For showing a thumbnail of the user's profile picture
    private var mThumbnailPath : String? = null

    // These will be used to get the phone's location
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude = 0.0
    private var mLongitude = 0.0

    private var mBoxWeather: RelativeLayout? = null
    private var mTvWeather: TextView? = null

    private var mCityID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MainFrag", "onCreateView")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("MainFrag", "onCreateView: view inflated successfully")

        // Get the SharedPreferences to load user info from (if it exists and is populated)
        mSharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        Log.d("MainFrag", "onCreateView: got sharedPreferences successfully")

        // If they are a brand new user, redirect immediately to the ProfileFrag for profile creation
        if (!mSharedPref!!.contains("hasProfile")) {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frag_container, ProfileFrag(), "Profile Fragment")
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Load the user data and populate onto the Fragment
        else {
            // Get the name
            val firstName = mSharedPref!!.getString("firstName", "")
            val lastName = mSharedPref!!.getString("lastName", "")
            val tvUsername: TextView = view.findViewById(R.id.tvUsername)
            // Set the name
            tvUsername.text = "$firstName $lastName"
            Log.d("MainFrag", "onCreateView: set the name TextView successfully")

            val ivThumbnail : ImageView = view.findViewById(R.id.ivThumbnail)
            mThumbnailPath = mSharedPref!!.getString("profilePic", "")
            val bMap = BitmapFactory.decodeFile(mThumbnailPath)
            ivThumbnail.setImageBitmap(bMap)

            // Get the BMR
            val tvBMR : TextView = view.findViewById(R.id.tvBMR)
            val activityLevelIndex = mSharedPref!!.getInt("activityLevel", 0)
            val bmr = BMR()
            val baseBMR = bmr.calculateBMR(
                mSharedPref!!.getInt("weight", 0),
                mSharedPref!!.getInt("height", 0),
                mSharedPref!!.getInt("age", 0),
                mSharedPref!!.getBoolean("isMale", true)
            )
            val adjustedBMR = bmr.calculateAdjustedBMR(baseBMR, activityLevelIndex)
            tvBMR.text = "$adjustedBMR kcal/day"

            // Set the activity level
            val activityLevels = arrayOf<String?>("Sedentary", "Mild", "Moderate", "Heavy", "Extreme")
            val tvActivityLevel: TextView = view.findViewById(R.id.tvActivityLevel)
            tvActivityLevel.text = activityLevels[activityLevelIndex]
            Log.d("MainFrag", "onCreateView: set the activity level TextView successfully")

            // update the spinner
            for (i in 1 until mActivityLevels.size){
                mActivityLevels[i] = activityLevels[i-1] + " (" + bmr.calculateAdjustedBMR(baseBMR, i-1) + " kcal/day)"
            }
        }


        // Fill the dropdown for activity level
        val spinner: Spinner = view.findViewById(R.id.spActivityLevel)
        Log.d("MainFrag", "onCreateView: spinner found successfully")

        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(view.context, R.layout.spinner_list_main, mActivityLevels)
        Log.d("MainFrag", "onCreateView: arrayAdapter created successfully")
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list_main)
        spinner.adapter = arrayAdapter
        Log.d("MainFrag", "onCreateView: spinner populated successfully")

        spinner.onItemSelectedListener = this
        Log.d("MainFrag", "onCreateView: onItemSelectedListener added successfully")


        // Add functionality to the edit profile button
        val btnEditProfile: Button = view.findViewById(R.id.btnEditProfile)
        btnEditProfile.setOnClickListener(this)


        // Get the FusedLocationProviderClient for GPS
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.context)
        Log.d("MainFrag", "onCreateView: got the FusedLocationProviderClient")


        // Add functionality to the Hikes button
        val btnHikes: Button = view.findViewById(R.id.btnHikes)
        btnHikes.setOnClickListener(this)


        // these will both be needed to make more details appear on click
        mBoxWeather = view.findViewById(R.id.boxWeather)
        mTvWeather = view.findViewById(R.id.tvWeather)
        // Add functionality to the Weather button
        val btnWeather: Button = view.findViewById(R.id.btnWeather)
        btnWeather.setOnClickListener(this)


        // Add functionality to the Details Weather button
        val btnMoreWeather: Button = view.findViewById(R.id.btnMoreWeather)
        btnMoreWeather.setOnClickListener(this)

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
        }



    // handles the "change activity level" spinner being changed
    override fun onItemSelected(parent: AdapterView<*>?, other: View?, pos: Int, id: Long) {
        Log.d("SpinnerListener", "Selected Something From the Spinner")
        val spString = parent!!.getItemAtPosition(pos).toString()
        Log.d("SpinnerListener", spString)

        // if anything other than "Change Activity Level" is selected then update the user's info and display
        if (spString != "Change Activity Level"){
            Log.d("SpinnerListener", "SUCCESS!")

            // get TextViews
            val tvBmr : TextView = view?.findViewById(R.id.tvBMR) as TextView
            val tvActLvl : TextView = view?.findViewById(R.id.tvActivityLevel) as TextView

            // split the string and put into Textviews
            val strArr = spString.split(" ")

            tvActLvl.text = strArr[0]

            val strBmr = strArr[1].removePrefix("(") + " " + strArr[2].dropLast(1)
            tvBmr.text = strBmr

            // update in the SharedPreferences as well
            with (mSharedPref!!.edit()) {
                putInt("activityLevel", pos-1)
                apply()
            }

            // reset spinner display to "Change Activity Level"
            parent.setSelection(0)
        }
    }



    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Do nothing (this method is required by AdapterView.OnItemSelectedListener)
    }



    // handles any of the buttons being clicked
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnEditProfile -> {
                // changes the ProfileFrag to be displayed
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.frag_container, ProfileFrag(), "Profile Fragment")
                transaction.addToBackStack(null)
                transaction.commit()
                Log.d("MainFrag", "onCreateView: edit profile button clicked")
            }
            R.id.btnHikes -> {
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
            R.id.btnWeather -> {

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
                        mLongitude = location.longitude

                        // make weather information visible
                        mBoxWeather!!.visibility = View.VISIBLE
                        mTvWeather!!.text = "Loading..."
//
//                    Toast.makeText(activity, "Latitude:$mLatitude\nLongitude:$mLongitude", Toast.LENGTH_SHORT).show()
//
//                    val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
//                    val weatherIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                    //If there's an activity associated with this intent, launch it
//                    try {
//                        startActivity(weatherIntent)
//                        Log.d("Main_ButtonFrag", "onViewCreated: startActivity(weatherIntent) called successfully")
//                    } catch (ex: ActivityNotFoundException) {
//                        Log.e("Main_ButtonFrag", "onViewCreated: startActivity(mapIntent) failed")
//                        //If there's no activity associated with this intent, display an error message
//                        Toast.makeText(activity, "No activity found to handle this intent", Toast.LENGTH_SHORT).show()
//                    }

                        val weatherDataURL = buildURLFromString(mLatitude, mLongitude)
                        var jsonWeatherData: String? = null
                        Thread{
                            try{
                                assert(weatherDataURL != null)
                                Log.d("WEATHER", "requesting weather")
                                jsonWeatherData = getDataFromURL(weatherDataURL!!)
                                Log.d("WEATHER", jsonWeatherData ?: "Didn't Get Data!!")

                                // get data out of json object
                                val jsonObject = JSONObject(jsonWeatherData!!)
                                val cityName = jsonObject.getString("name")
                                Log.d("WEATHER", cityName)
                                mCityID = jsonObject.getInt("id").toString()
                                Log.d("WEATHER", mCityID!!)

                                val jsonSys = jsonObject.getJSONObject("sys")
                                val countryName = jsonSys.getString("country")
                                Log.d("WEATHER", countryName)

                                val jsonMain = jsonObject.getJSONObject("main")
                                val temperature = jsonMain.getDouble("temp")
                                val feelsLike = jsonMain.getDouble("feels_like")
                                Log.d("WEATHER", (temperature - 273.15).toString())
                                Log.d("WEATHER", (feelsLike - 273.15).toString())


                                val jsonWeatherArray = jsonObject.getJSONArray("weather")
                                val jsonWeather = jsonWeatherArray.getJSONObject(0)
                                val weatherMain = jsonWeather.getString("main")
                                Log.d("WEATHER", weatherMain)
                                val weatherDescription = jsonWeather.getString("description")
                                Log.d("WEATHER", weatherDescription)
                                val weatherIcon = jsonWeather.getString("icon")
                                Log.d("WEATHER", weatherIcon)

                                // add weather data to textview
                                val outStr = "Current Weather for " + cityName + ", " + countryName + "\nTemp: " + (temperature - 273.15).roundToInt() + " C\nFeels Like: " + (feelsLike - 273.15).roundToInt() + " C\nWeather: " + weatherMain
                                mTvWeather!!.text = outStr

//                            Log.d("WEATHER", "Done")
                            }
                            catch (e: Exception){
                                mTvWeather!!.text = "Error Fetching Weather Data"
                                e.printStackTrace()
                            }
                        }.start()
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "Failed on getting current location", Toast.LENGTH_SHORT).show()
                    }
            }
            R.id.btnMoreWeather -> {
//                val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
                val url = "https://openweathermap.org/city/$mCityID"
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
        }
    }
}