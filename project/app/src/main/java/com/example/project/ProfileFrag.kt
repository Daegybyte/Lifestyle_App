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

    // All of the elements that will need to be accessed later
    private var mEtFirstName: EditText? = null
    private var mEtLastName: EditText? = null
    private var mNpAge: NumberPicker? = null
    private var mNpHeight: NumberPicker? = null
    private var mNpWeight: NumberPicker? = null
    private var mSpActivityLevel: Spinner? = null
    private var mRgSex: RadioGroup? = null
    private var mRbMale: RadioButton? = null
    private var mRbFemale: RadioButton? = null
    private var mTvLocation: TextView? = null
    private var mIvProfilePic: ImageView? = null

    private var mFirstName: String? = null
    private var mLastName: String? = null
    private var mAge: Int? = null
    private var mHeight: Int? = null
    private var mWeight: Int? = null
    private var mActivityLevel: Int? = null
    private var mIsMale: Boolean? = null
    private var mLocation: String? = null
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

        // Getting all of the elements into the member variables
        mEtFirstName = view.findViewById(R.id.etFirstName)
        mEtLastName = view.findViewById(R.id.etLastName)
        mNpAge = view.findViewById(R.id.np_age)
        mNpHeight = view.findViewById(R.id.np_height)
        mNpWeight = view.findViewById(R.id.np_weight)
        mSpActivityLevel = view.findViewById(R.id.spActivityLevel)
        mRgSex = view.findViewById(R.id.radio_sex)
        mRbMale = view.findViewById(R.id.radio_male)
        mRbFemale = view.findViewById(R.id.radio_female)
        mTvLocation = view.findViewById(R.id.tvLocation)
        mIvProfilePic = view.findViewById(R.id.ivProfilePic)

        // Populate the age NumberPicker
        mNpAge!!.minValue = 12
        mNpAge!!.maxValue = 99
        mNpAge!!.value = 25
        mNpAge!!.wrapSelectorWheel = false

        // Populate the height NumberPicker
        mNpHeight!!.minValue = 140
        mNpHeight!!.maxValue = 220
        mNpHeight!!.value = 160
        mNpHeight!!.wrapSelectorWheel = false

        // Populate the weight NumberPicker
        mNpWeight!!.minValue = 45
        mNpWeight!!.maxValue = 150
        mNpWeight!!.value = 75
        mNpWeight!!.wrapSelectorWheel = false

        // Populate the activity level spinner
        val activityLevels = arrayOf<String?>("Sedentary", "Mild", "Moderate", "Heavy", "Extreme")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(view.context, R.layout.spinner_list_profile, activityLevels)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list_profile)
        mSpActivityLevel!!.adapter = arrayAdapter

        // Get the SharedPreferences to read from/store in
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Check to see if there is user info in the SharedPreferences
        if (sharedPref.contains("hasProfile")) {
            Log.d("sharedPref", "sharedPref was found")
            // Get the name
            mFirstName = sharedPref.getString("firstName", "")
            mLastName = sharedPref.getString("lastName", "")
            // Set the name
//            val etFirstName: EditText = view.findViewById(R.id.etFirstName)
//            val etLastName: EditText = view.findViewById(R.id.etLastName)
            mEtFirstName!!.setText(mFirstName)
            mEtLastName!!.setText(mLastName)
            // Get the age
            mAge = sharedPref.getInt("age", mNpAge!!.value)
            //set the age
            mNpAge!!.value = mAge as Int

            // Get the height
            mHeight = sharedPref.getInt("height", mNpHeight!!.value)
            //set the height
            mNpHeight!!.value = mHeight as Int

            // Get the weight
            mWeight = sharedPref.getInt("weight", mNpWeight!!.value)
            //set the weight
            mNpWeight!!.value = mWeight as Int

            // Get the activity level
            mActivityLevel = sharedPref.getInt("activityLevel", 2)
            //set the activity level
            mSpActivityLevel!!.setSelection(mActivityLevel!!)

            // Get the gender
            mIsMale = sharedPref.getBoolean("isMale", true)
            mRbMale!!.isChecked = mIsMale as Boolean
            mRbFemale!!.isChecked = !mIsMale!!

            // Get the location
            mLocation = sharedPref.getString("location", "Location")
            mTvLocation!!.text = mLocation

            // Get the profile pic
            mProfilePicPath = sharedPref.getString("profilePic", "")
            val bMap = BitmapFactory.decodeFile(mProfilePicPath)
            mIvProfilePic!!.setImageBitmap(bMap)
        }

        // Check to see if there is user info in the savedInstanceState
        // If there is then this should overwrite the info loaded from the SharedPreferences
//        if (savedInstanceState != null) {
//            if (savedInstanceState.getString("firstName") != mFirstName) {
//                mEtFirstName!!.setText(savedInstanceState.getString("firstName"))
//            }
////            mEtFirstName!!.setText(savedInstanceState.getString("firstName", ""))
//            if (savedInstanceState.getString("lastName") != mLastName) {
//                mEtLastName!!.setText(savedInstanceState.getString("lastName"))
//            }
//            if (savedInstanceState.getInt("age") != mAge) {
//                mNpAge!!.value = savedInstanceState.getInt("age")
//            }
//            if (savedInstanceState.getInt("height") != mHeight) {
//                mNpHeight!!.value = savedInstanceState.getInt("height")
//            }
//            if (savedInstanceState.getInt("weight") != mWeight) {
//                mNpWeight!!.value = savedInstanceState.getInt("weight")
//            }
//            if (savedInstanceState.getInt("activityLevel") != mActivityLevel) {
//                mSpActivityLevel!!.setSelection(savedInstanceState.getInt("activityLevel"))
//            }
//            if (savedInstanceState.getBoolean("isMale") != mIsMale) {
//                mRbMale!!.isChecked = savedInstanceState.getBoolean("isMale", true)
//                mRbFemale!!.isChecked = !savedInstanceState.getBoolean("isMale", true)
//            }
//            if (savedInstanceState.getString("location") != mLocation) {
//                mTvLocation!!.text = savedInstanceState.getString("location")
//            }
//            if (savedInstanceState.getString("profilePic") != mProfilePicPath) {
//                val bMap = BitmapFactory.decodeFile(savedInstanceState.getString("profilePic"))
//                mIvProfilePic!!.setImageBitmap(bMap)
//            }
//        }

//        // Get all the profile info values
//        // These will keep the values from before any changes were made
//        mFirstName = mEtFirstName!!.text.toString()
//        mLastName = mEtLastName!!.text.toString()
//        mAge = mNpAge!!.value
//        mHeight = mNpHeight!!.value
//        mWeight = mNpWeight!!.value
//        mActivityLevel = mSpActivityLevel!!.selectedItemPosition
//        // get selected radio button from radioGroup
//        val radioButton: RadioButton = view.findViewById(mRgSex!!.checkedRadioButtonId)
//        // store a boolean (for less space + ease) representing whether they are male or not
//        mIsMale = radioButton.text.toString() == "Male"
//        mLocation = mTvLocation!!.text.toString()

        // Adding functionality to the location button
        val btnLocation: Button = view.findViewById(R.id.btnLocation)
        btnLocation.setOnClickListener {

            // get current GPS location
            val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view.context)

            val appPerms = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            Log.d("ProfileFrag", "onViewCreated: appPerms: $appPerms")
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

                    mTvLocation!!.text = address
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed on getting current location", Toast.LENGTH_SHORT).show()
                }

        }

        // get the picture button
        val picButton : Button = view.findViewById(R.id.btnPic)
        // add functionality to the picture button to open camera
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
                putString("firstName", mEtFirstName!!.text.toString())
                putString("lastName", mEtLastName!!.text.toString())
                putInt("age", mNpAge!!.value)
                putInt("height", mNpHeight!!.value)
                putInt("weight", mNpWeight!!.value)
                putInt("activityLevel", mSpActivityLevel!!.selectedItemPosition)
                // store a boolean (for less space + ease) representing whether they are male or not
                putBoolean("isMale", mRbMale!!.isChecked)
                putString("location", mTvLocation!!.text.toString())
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

    // to be run when the add profile picture button is clicked
    private var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {

            val ivProfilePic : ImageView = requireView().findViewById(R.id.ivProfilePic)

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
        }

    // adapted from https://stackoverflow.com/questions/59095837/convert-from-latlang-to-address-using-geocoding-not-working-android-kotlin
    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(context)
        val list = geocoder.getFromLocation(lat, lng, 1)
//        return list[0].getAddressLine(0)
        return list[0].locality + ", " + list[0].adminArea + ", " + list[0].countryName
    }

    // This will save all of the current state
    // Any checking to see if the state actually changed will be done when loading the data
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        outState.putString("firstName", mEtFirstName!!.text.toString())
//        outState.putString("lastName", mEtLastName!!.text.toString())
//        outState.putInt("age", mNpAge!!.value)
//        outState.putInt("height", mNpHeight!!.value)
//        outState.putInt("weight", mNpWeight!!.value)
//        outState.putInt("activityLevel", mSpActivityLevel!!.selectedItemPosition)
//        outState.putBoolean("isMale", mRbMale!!.isChecked)
//        outState.putString("location", mTvLocation!!.text.toString())
//        outState.putString("profilePic", mProfilePicPath)
//    }

}