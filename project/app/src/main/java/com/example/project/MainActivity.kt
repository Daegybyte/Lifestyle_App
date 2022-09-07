package com.example.project
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource

class MainActivity : FragmentActivity() {
//    private val alChange: String = "Change Activity Level"
//    private var alSedentary: String = "Sedentary (1600 kcal/day)"
//    private var alMild: String = "Mild (1800 kcal/day)"
//    private var alModerate: String = "Moderate (2000 kcal/day)"
//    private var alHeavy: String = "Heavy (2200 kcal/day)"
//    private var alExtreme: String = "Extreme (2400 kcal/day)"

//    private var mHikesButton: Button? = null
//    private var mWeatherButton: Button? = null
//
//    private lateinit var mFusedLocationClient: FusedLocationProviderClient
//
//    private var mLatitude = 0.0
//    private var mLongitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val spinner: Spinner = findViewById(R.id.spActivityLevel)
//        val activityLevels = arrayOf<String?>(alChange, alSedentary, alMild, alModerate, alHeavy, alExtreme)
//        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, activityLevels)
//        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
//        spinner.adapter = arrayAdapter

//        mHikesButton = findViewById(R.id.btnHikes)
//        mWeatherButton = findViewById(R.id.btnWeather)
//
//        mHikesButton!!.setOnClickListener(this)
//        mWeatherButton!!.setOnClickListener(this)
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

//    override fun onClick(view: View) {
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
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
//            return
//        }
//
//        val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
//        val cancellationTokenSource = CancellationTokenSource()
//
//        mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
//            .addOnSuccessListener { location: Location? ->
//                // getting the last known or current location
//                mLatitude = location!!.latitude
//                mLongitude = location!!.longitude
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Failed on getting current location",
//                    Toast.LENGTH_SHORT).show()
//            }
//
//        Toast.makeText(
//            this@MainActivity,
//            "Latitude:$mLatitude\nLongitude:$mLongitude",
//            Toast.LENGTH_SHORT
//        ).show()
//
////        WEB coordinates
////        val latitude = 40.767778
////        val longitude = -111.845205
//
////        new york coordinates
//        val latitude = 40.7128
//        val longitude = -74.0060
//
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
//    }
}

//val spinner: Spinner = findViewById(R.id.spActivityLevel)
//// Create an ArrayAdapter using the string array and a default spinner layout
//ArrayAdapter.createFromResource(
//this,
//R.array.activityLevelsArray,
//android.R.layout.simple_spinner_item
//).also { adapter ->
//    // Specify the layout to use when the list of choices appears
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//    // Apply the adapter to the spinner
//    spinner.adapter = adapter
//}