package com.example.project

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: SharedRepository) : ViewModel() {
    // user livedata
    val userInfo: LiveData<User> = repository.userInfo.asLiveData()
    val numUserRows: LiveData<Int> = repository.numUserRows.asLiveData()
    // weather livedata
    val aveTemp: LiveData<Double> = repository.aveTemp.asLiveData()


    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun insertWeather(dbWeather: DBWeather) = viewModelScope.launch {
        repository.insertWeather(dbWeather)
    }


    // repository
//    private val mRepository = repository

    // all of the User info:
    // todo: make a user class?
//    private val _userInfo: MutableLiveData<User>
//    val userInfo: LiveData<User>
//        get() = _userInfo


    //    init {
////        mRepository = SharedRepository.getInstance(application)
////        _userInfo = repository.userInfo
//
//    }

}

class SharedViewModelFactory(private val repository: SharedRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCKECKED_CAST")
            return SharedViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}