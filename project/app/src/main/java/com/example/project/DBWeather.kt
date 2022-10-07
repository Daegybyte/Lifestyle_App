package com.example.project

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.SimpleTimeZone

@Entity(tableName = "weather_table")
data class DBWeather(
    val id: Int,
    val name: String,
    //Coord
    val lat: Double,
    val lon: Double,
    //Weather
    val weatherMain: String,
    val description: String,
    val icon: String,
    //Main
    val temp: Double,
    //Sys
    val country: String,
){
    @PrimaryKey(autoGenerate = true) var key : Int = 0
}
