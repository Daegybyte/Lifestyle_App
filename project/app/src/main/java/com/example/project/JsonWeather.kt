package com.example.project

data class JsonWeather(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timeZone: Int,
    val id: Int,
    val name: String,
    val cod: Int
){
    data class Coord(val lat: Double, val lon: Double)
    data class Weather(val id: Int, val main: String, val description: String, val icon: String)
    data class Main(val temp: Double, val feels_like: Double, val temp_min: Double, val temp_max: Double,
                    val pressure: Int, val humidity: Int, val sea_level: Int, val grd_level: Int)
    data class Wind(val speed: Double, val deg: Int, val gust: Double)
    data class Rain(val h: Double)
    data class Clouds(val all: Int)
    data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Int, val sunset: Int)

    //Coord
    var lat = coord.lat
    var lon = coord.lon
    //Weather
    var weatherItem = weather[0]
    var weatherId = weatherItem.id
    var weatherMain = weatherItem.main
    var description = weatherItem.description
    var icon = weatherItem.icon
    //Main
    var temp = main.temp - 273.15
    var feelsLike = main.feels_like - 273.15
    var tempMin = main.temp_min - 273.15
    var tempMax = main.temp_max - 273.15
    var pressure = main.pressure
    var humidity = main.humidity
    var seaLevel = main.sea_level
    var grdLevel = main.grd_level
    //Wind
    var h = rain.h
    //Clouds
    var cloudsAll = clouds.all
    //Sys
    var type = sys.type
    var sysId = sys.id
    var country = sys.country
    var sunrise = sys.sunrise
    var sunset = sys.sunset
}
