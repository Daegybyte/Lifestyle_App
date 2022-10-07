package com.example.project

import android.location.Location
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.math.roundToInt

class SharedRepository (private val userDao: UserDao, private val dbWeatherDao: DBWeatherDao) {
    // user flow
    val userInfo: Flow<User> = userDao.getUser()
    // weather flow
    val aveTemp: Flow<Double> = dbWeatherDao.getAveTemp()
    //weather data
    val liveWeather = MutableLiveData<JsonWeather>()


//    private var mSharedPref: SharedPreferences = application.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

    @WorkerThread
    suspend fun updateUser(user: User) {
        // TODO: Room stuff
//        with(mSharedPref.edit()) {
//            putString("firstName", user!!.firstName)
//            putString("lastName", user.lastName)
//            putInt("age", user.age)
//            putInt("height", user.height)
//            putInt("weight", user.weight)
//            putInt("activityLevel", user.activityLevel)
//            // store a boolean (for less space + ease) representing whether they are male or not
//            putBoolean("isMale", user.isMale)
//            putString("location", user.location)
//            putString("profilePic", user.imagePath)
//            apply()
//        }
//        userInfo.value = user!!
        userDao.insert(user)    // <<--- inserts a new row for the user on every update
    }

    private var mJsonString: String? = null

    fun getWeather(location: Location) {

//                // getting the last known or current location
//                val mLatitude = location!!.latitude
//                val mLongitude = location.longitude

        mScope.launch(Dispatchers.IO) {
            getJsonWeatherString(location)

//            if (mJsonString != null){
            mJsonString?.let {
                Log.d("JSON", mJsonString.toString())
                val wd = (Gson()).fromJson(it, JsonWeather::class.java)

                Log.d("JsonWeather", wd.toString())

                liveWeather.postValue(wd)

                val dbWeatherData = DBWeather(
                    wd.id,
                    wd.name,
                    wd.coord.lat,
                    wd.coord.lon,
                    wd.weather[0].main,
                    wd.weather[0].description,
                    wd.weather[0].icon,
                    wd.main.temp,
                    wd.sys.country
                )

                Log.d("dbWeather", dbWeatherData.toString())


                insertWeather(dbWeatherData)
            }
        }
    }

    @WorkerThread
    suspend fun insertWeather(dbWeather: DBWeather){
        dbWeatherDao.insert(dbWeather)
    }

    @WorkerThread
    suspend fun getJsonWeatherString(location: Location){
        val mLatitude = location.latitude
        val mLongitude = location.longitude

        val weatherDataURL = NetworkUtils.buildURLFromString(mLatitude, mLongitude)

        weatherDataURL?.let{
            val jsonWeatherData = NetworkUtils.getDataFromURL(it)

//            Log.d("JSON", jsonWeatherData.toString())

            jsonWeatherData?.let{
                mJsonString = jsonWeatherData
            }
        }
        // This should be the same as the above three lines of code
//        if (weatherDataURL != null){
//            val jsonWeatherData = NetworkUtils.getDataFromURL(weatherDataURL)
//            if (jsonWeatherData != null){
//                mJsonString = jsonWeatherData
//            }
//        }
    }




////     getInstance of SharedRepository Singleton
//    companion object {
//        @Volatile
//        private var instance: SharedRepository? = null
//        @Synchronized
//        fun getInstance(application: Application): SharedRepository {
//            if (instance == null) {
//                instance = SharedRepository(application)
//            }
//            return instance as SharedRepository
//        }
//    }

    /**
     * Will need to switch over to this getInstance() version at some point
     */
    companion object {
        private var mInstance: SharedRepository? = null
        private lateinit var mScope: CoroutineScope
        @Synchronized
        fun getInstance(userDao: UserDao,
                        dbWeatherDao: DBWeatherDao,
                        scope: CoroutineScope
        ): SharedRepository {
            mScope = scope
            return mInstance?: synchronized(this){
                val instance = SharedRepository(userDao, dbWeatherDao)
                mInstance = instance
                instance
            }
        }
    }
}