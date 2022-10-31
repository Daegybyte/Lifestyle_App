package com.example.project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class, DBWeather::class), version = 1, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun DBWeatherDao(): DBWeatherDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
                        ):RoomDB {
            // if the INSTANCE in null, create DB, else return existing DB
            return INSTANCE?: synchronized(this){
                createDatabase(context, scope)
            }
        }

        fun refreshDatabase (context: Context,
                             scope: CoroutineScope
        ){
            synchronized(this) {
                createDatabase(context, scope)
            }
        }

        private fun createDatabase (context: Context, scope: CoroutineScope) : RoomDB {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java,
                "room_database"
            )
                .addCallback(RoomDBCallback(scope))
                .build()
            INSTANCE = instance
            //return instance
            return instance
        }

    }

    private class RoomDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.UserDao(), database.DBWeatherDao())
                }
            }
        }

        suspend fun populateDatabase(userDao:UserDao, dbWeatherDao: DBWeatherDao){


            userDao.deleteAll()
            dbWeatherDao.deleteAll()
        }
    }
}