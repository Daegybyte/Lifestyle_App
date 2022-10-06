package com.example.project

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SharedRepository (private val userDao: UserDao, private val dbWeatherDao: DBWeatherDao) {
    // user flow
    val userInfo: Flow<User> = userDao.getUser()
    val numUserRows: Flow<Int> = userDao.getNumUserRows()
    // weather flow
    val aveTemp: Flow<Double> = dbWeatherDao.getAveTemp()


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

    @WorkerThread
    suspend fun insertWeather(dbWeather: DBWeather){
        dbWeatherDao.insert(dbWeather)
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