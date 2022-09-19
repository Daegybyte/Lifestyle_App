package com.example.project

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
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
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ProfileFrag : Fragment() {

    private var mProfilePicPath : String? = null

    private var mLatitude = 0.0
    private var mLongitude = 0.0

    @SuppressLint("MissingPermission")
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

        val rgSex: RadioGroup = view.findViewById(R.id.radio_sex)

        // TODO
        // get the location TextView
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)

        // get the location button
        val btnLocation: Button = view.findViewById(R.id.btnLocation)
        // add the click functionality
        btnLocation.setOnClickListener {

            // get current GPS location
            val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view.context)

            val appPerms = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            Log.d("MainFrag", "onViewCreated: appPerms: $appPerms")
            activityResultLauncher.launch(appPerms)

            val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token)
                .addOnSuccessListener { location: Location? ->
                    // getting the last known or current location
                    mLatitude = location!!.latitude
                    mLongitude = location.longitude

                    Toast.makeText(activity, "Latitude:$mLatitude\nLongitude:$mLongitude", Toast.LENGTH_SHORT).show()

                    val address = getAddress(mLatitude, mLongitude)

                    tvLocation.text = address
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed on getting current location", Toast.LENGTH_SHORT).show()
                }

        }

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
            val age = sharedPref.getInt("age", npAge.value)
            //set the age
            npAge.value = age

            // Get the height
            val height = sharedPref.getInt("height", npHeight.value)
            //set the height
            npHeight.value = height

            // Get the weight
            val weight = sharedPref.getInt("weight", npWeight.value)
            //set the weight
            npWeight.value = weight

            // Get the activity level
            val activityLevelIndex = sharedPref.getInt("activityLevel", 2)
            //set the activity level
            spActivityLvl.setSelection(activityLevelIndex)

            // Get the gender
            val radioMale: RadioButton = view.findViewById(R.id.radio_male)
            radioMale.isChecked = sharedPref.getBoolean("isMale", true)
            val radioFemale: RadioButton = view.findViewById(R.id.radio_female)
            radioFemale.isChecked = !sharedPref.getBoolean("isMale", true)

            // Get the location
            tvLocation.text = sharedPref.getString("location", "Location")

            // Get the profile pic
            val profilePicView : ImageView = view.findViewById(R.id.profilePic)
            mProfilePicPath = sharedPref.getString("profilePic", "")
            val bMap = BitmapFactory.decodeFile(mProfilePicPath)
            profilePicView.setImageBitmap(bMap)

        }

        val picButton : Button = view.findViewById(R.id.btnPic)
        picButton.setOnClickListener{
            val camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try{
                cameraLauncher.launch(camIntent)
            }catch(ex: ActivityNotFoundException){
                Toast.makeText(activity, "Error Opening Camera", Toast.LENGTH_SHORT).show()
            }
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
                putString("location", tvLocation.text.toString())
                putString("profilePic", mProfilePicPath)

                putBoolean("hasProfile", true)
                apply()
            }

            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frag_container, MainFrag(), "Main Fragment")
            transaction.addToBackStack(null)
            transaction.commit()
        }



        return view
    }

    private var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {

            val ivProfilePic : ImageView = requireView().findViewById(R.id.profilePic)

            val extras = result.data!!.extras
            val mProfilePic = extras!!["data"] as Bitmap?

            //Open a file and write to it
            if (isExternalStorageWritable) {
                mProfilePicPath = saveImage(mProfilePic)
                ivProfilePic.setImageBitmap(mProfilePic)
            } else {
                Toast.makeText(activity, "External storage not writable.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveImage(finalBitmap: Bitmap?): String {
        val root = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val myDir = File("$root/saved_images")
        myDir.mkdirs()
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fname = "Thumbnail_$timeStamp.jpg"
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            finalBitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            Toast.makeText(activity, "file saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file.absolutePath
    }

    private val isExternalStorageWritable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
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

            }
        }

    // adapted from https://stackoverflow.com/questions/59095837/convert-from-latlang-to-address-using-geocoding-not-working-android-kotlin
    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(context)
        val list = geocoder.getFromLocation(lat, lng, 1)
//        return list[0].getAddressLine(0)
        return list[0].locality + ", " + list[0].adminArea + ", " + list[0].countryName
    }

}