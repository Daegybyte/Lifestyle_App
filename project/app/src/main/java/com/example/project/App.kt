package com.example.project

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
//    val database by lazy { RoomDB.getDatabase(this, applicationScope) }
    fun getDatabase(): RoomDB {
        return RoomDB.getDatabase(this, applicationScope)
    }
    val repository by lazy { SharedRepository.getInstance(this, applicationScope) }

}