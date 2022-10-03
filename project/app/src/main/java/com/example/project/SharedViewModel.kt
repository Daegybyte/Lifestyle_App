package com.example.project

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SharedViewModel(application: Application) : ViewModel() {
//class SharedViewModel(repository: SharedRepository) : ViewModel() {

    // repository
    private val mRepository: SharedRepository

    // all of the User info:
    // todo: make a user class?
    private val _userInfo: MutableLiveData<User>
    val userInfo: LiveData<User>
        get() = _userInfo

    init {
        mRepository = SharedRepository.getInstance(application)
        _userInfo = mRepository.userInfo

//        _firstName = mRepository.firstName
//        _lastName = mRepository.lastName
//        _age = mRepository.age
//        _height = mRepository.height
//        _weight = mRepository.weight
//        _activityLevel = mRepository.activityLevel
//        _isMale = mRepository.isMale
//        _location = mRepository.location
//        _profilePicPath = mRepository.profilePicPath
    }

//    private val _firstName: MutableLiveData<String>
//    val firstName: LiveData<String>
//        get() = _firstName
//
//    private val _lastName: MutableLiveData<String>
//    val lastname: LiveData<String>
//        get() = _lastName
//
//    private val _age: MutableLiveData<Int>
//    val age: LiveData<Int>
//        get() = _age
//
//    private val _height: MutableLiveData<Int>
//    val height: LiveData<Int>
//        get() = _height
//
//    private val _weight: MutableLiveData<Int>
//    val weight: LiveData<Int>
//        get() = _weight
//
//    private val _activityLevel: MutableLiveData<Int>
//    val activityLevel: LiveData<Int>
//        get() = _activityLevel
//
//    private val _isMale: MutableLiveData<Boolean>
//    val isMale: LiveData<Boolean>
//        get() = _isMale
//
//    private val _location: MutableLiveData<String>
//    val location: LiveData<String>
//        get() = _location
//
//    private val _profilePicPath: MutableLiveData<String>
//    val profilePicPath: LiveData<String>
//        get() = _profilePicPath

    fun updateUser(user: User?) {
        mRepository.updateUser(user)
    }

//    fun updateUser(firstName: String?, lastName: String?, age: Int?, height: Int?, weight: Int?,
//                   activityLevel: Int?, isMale: Boolean?, location: String?, profilePicPath: String?) {
//        mRepository.updateUser(firstName, lastName, age, height, weight, activityLevel, isMale, location, profilePicPath)
//    }

    // This factory class allows us to define custom constructors for the view model
//    class SharedViewModelFactory(private val repository: SharedRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return SharedViewModel(repository) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
}

class SharedViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            SharedViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}