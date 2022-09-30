package com.example.project

import android.app.Application
import android.location.Location
import androidx.lifecycle.MutableLiveData

class SharedRepository private constructor(application: Application) {

    // all of the user info:
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val age = MutableLiveData<Int>()
    val height = MutableLiveData<Int>()
    val weight = MutableLiveData<Int>()
    val activityLevel = MutableLiveData<Int>()
    val isMale = MutableLiveData<Boolean>()
    val location = MutableLiveData<String>()
    val profilePicPath = MutableLiveData<String>()

    // getInstance of Singleton
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