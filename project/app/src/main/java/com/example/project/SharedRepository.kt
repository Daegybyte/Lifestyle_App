package com.example.project

import android.location.Location
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//class SharedRepository (private val userDao: UserDao, private val dbWeatherDao: DBWeatherDao) {
class SharedRepository (private val app: App) {

    // user flow
    val userInfo: Flow<User> = app.database.UserDao().getUser()
    // weather flow
    val aveTemp: Flow<Double> = app.database.DBWeatherDao().getAveTemp()
    //weather data
    val liveWeather = MutableLiveData<JsonWeather>()

    var mJsonWeatherData: JsonWeather? = null

    var rNumSteps: Int = 0
    var rCounterOn = false

    @WorkerThread
    suspend fun updateUser(user: User) {
        app.database.UserDao().insert(user)
//        userDao.insert(user)    // <<--- inserts a new row for the user on every update
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
//                Log.d("JSON", mJsonString.toString())
                val wd = (Gson()).fromJson(it, JsonWeather::class.java)

                mJsonWeatherData = wd

//                Log.d("JsonWeather", wd.toString())

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

//                Log.d("dbWeather", dbWeatherData.toString())


                insertWeather(dbWeatherData)
            }
        }
    }

    @WorkerThread
    suspend fun insertWeather(dbWeather: DBWeather){
//        dbWeatherDao.insert(dbWeather)
        app.database.DBWeatherDao().insert(dbWeather)
    }

    @WorkerThread
    suspend fun getJsonWeatherString(location: Location){
        val mLatitude = location.latitude
        val mLongitude = location.longitude

        val weatherDataURL = NetworkUtils.buildURLFromString(mLatitude, mLongitude)

        weatherDataURL?.let{
            val jsonWeatherData = NetworkUtils.getDataFromURL(it)

            jsonWeatherData?.let{
                mJsonString = jsonWeatherData
            }
        }
    }

    fun getCityId() : Int {
        return mJsonWeatherData!!.id
    }

    companion object {
        private var mInstance: SharedRepository? = null
        private lateinit var mScope: CoroutineScope
        @Synchronized
        fun getInstance(application: App,
                        scope: CoroutineScope
        ): SharedRepository {
            mScope = scope
            return mInstance?: synchronized(this){
                val instance = SharedRepository(application)
                mInstance = instance
                instance
            }
        }
    }
}