package com.example.project

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope

class SharedRepository private constructor(application: Application) {

    // all of the user info:
    val userInfo = MutableLiveData<User>()

    private var mSharedPref: SharedPreferences = application.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

    fun updateUser(user: User?) {
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
        userInfo.value = user!!
    }

//    private fun getUser() {
//        val user = User(
//            mSharedPref.getString("firstName", "").toString(),
//            mSharedPref.getString("lastName", "").toString(),
//            mSharedPref.getInt("age", 25),
//            mSharedPref.getInt("height", 180),
//            mSharedPref.getInt("weight", 80),
//            mSharedPref.getInt("activityLevel", 0),
//            mSharedPref.getBoolean("isMale", true),
//            mSharedPref.getString("location", "").toString(),
//            mSharedPref.getString("profilePic", "").toString()
//        )
//        userInfo.value = user
//    }

//    init {
//        getUser()
//    }

//     getInstance of SharedRepository Singleton
    companion object {
        @Volatile
        private var instance: SharedRepository? = null
        @Synchronized
        fun getInstance(application: Application): SharedRepository {
            if (instance == null) {
                instance = SharedRepository(application)
            }
            return instance as SharedRepository
        }
    }

    /**
     * Will need to switch over to this getInstance() version at some point
     */
//    companion object {
//        private var mInstance: SharedRepository? = null
//        private lateinit var mScope: CoroutineScope
//        @Synchronized
//        fun getInstance(sharedDao: SharedDao,
//                        scope: CoroutineScope
//        ): SharedRepository {
//            mScope = scope
//            return mInstance?: synchronized(this){
//                val instance = SharedRepository(sharedDao)
//                mInstance = instance
//                instance
//            }
//        }
//    }
}