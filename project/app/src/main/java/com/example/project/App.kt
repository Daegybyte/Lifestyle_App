package com.example.project

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { SharedRepository(database.UserDao(), database.DBWeatherDao()) }

}