package com.example.project

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

class SharedRepository private constructor(application: Application) {

    // all of the user info:
    val userInfo = MutableLiveData<User>()

    private var mSharedPref: SharedPreferences? = null

//    val firstName = MutableLiveData<String>()
//    val lastName = MutableLiveData<String>()
//    val age = MutableLiveData<Int>()
//    val height = MutableLiveData<Int>()
//    val weight = MutableLiveData<Int>()
//    val activityLevel = MutableLiveData<Int>()
//    val isMale = MutableLiveData<Boolean>()
//    val location = MutableLiveData<String>()
//    val profilePicPath = MutableLiveData<String>()

    fun updateUser(user: User?) {
        // TODO: Room stuff
    }

//    fun updateUser(firstName: String?, lastName: String?, age: Int?, height: Int?, weight: Int?,
//                   activityLevel: Int?, isMale: Boolean?, location: String?, profilePicPath: String?) {
//        // TODO: Room stuff
//    }

    // getInstance of SharedRepository Singleton
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
}