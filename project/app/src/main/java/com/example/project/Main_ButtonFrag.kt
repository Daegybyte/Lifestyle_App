package com.example.project

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource

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
        val btnWeather: Button = view.findViewById(R.id.btnWeather)
        btnHikes.setOnClickListener(this)
        btnWeather.setOnClickListener(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.context)

        return view
    }

    override fun onClick(view: View) {
        if (ActivityCompat.checkSelfPermission(
                view.context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                view.context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        val cancellationTokenSource = CancellationTokenSource()

        mFusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
            .addOnSuccessListener { location: Location? ->
                // getting the last known or current location
                mLatitude = location!!.latitude
                mLongitude = location!!.longitude
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Failed on getting current location",
                    Toast.LENGTH_SHORT).show()
            }

        Toast.makeText(
            activity,
            "Latitude:$mLatitude\nLongitude:$mLongitude",
            Toast.LENGTH_SHORT
        ).show()

//        WEB coordinates
//        val latitude = 40.767778
//        val longitude = -111.845205

//        new york coordinates
        val latitude = 40.7128
        val longitude = -74.0060

        when (view.id) {
            R.id.btnHikes -> {
//                val searchUri = Uri.parse("geo:$latitude, $longitude?q=" + Uri.encode("hiking trails"))
                val searchUri = Uri.parse("geo:$mLatitude, $mLongitude?q=" + Uri.encode("hiking trails"))

                //Create the implicit intent
                val mapIntent = Intent(Intent.ACTION_VIEW, searchUri)

                //If there's an activity associated with this intent, launch it
                try {
                    startActivity(mapIntent)
                } catch (ex: ActivityNotFoundException) {
                    //handle errors here
                }
            }

            R.id.btnWeather -> {
//                val url = "https://www.timeanddate.com/weather/usa/salt-lake-city"
//                val url = "https://forecast.weather.gov/MapClick.php?textField1=$latitude&textField2=$longitude"
                val url = "https://forecast.weather.gov/MapClick.php?textField1=$mLatitude&textField2=$mLongitude"
                val weatherIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                //If there's an activity associated with this intent, launch it
                try {
                    startActivity(weatherIntent)
                } catch (ex: ActivityNotFoundException) {
                    //handle errors here
                }
            }
        }
    }
}