package com.example.project

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SharedAppData::class], version=1, exportScema = false)
abstract class SharedRoomDatabase : RoomDatabase() {
    // Need a dao for each entity. Abstract because Room will fill it in
    abstract fun weatherDao(): WeatherDao
    //TODO stuff for User Dao
    // abstract fun userDao(): UserDao

}