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


}

//data class JsonWeather(
//    //Primary Constructor
//    //Coord
//    val lat: Double,
//    val lon: Double,
//    //Weather
//    val weatherId: Int,
//    val weatherMain: String,
//    val description: String,
//    val icon: String,
//    val base: String,
//    //Main
//    val temp: Double,
//    val feelsLike: Double,
//    val tempMin: Double,
//    val tempMax: Double,
//    val pressure: Int,
//    val humidity: Int,
//    val seaLevel: Int,
//    val grdLevel: Int,
//    val visibility: Int,
//    //Wind
//    val speed: Double,
//    val deg: Int,
//    val gust: Double,
//    //Rain
//    val h: Double,
//    //Clouds
//    val cloudsAll: Int,
//    val dt: Int,
//    //Sys
//    val type: Int,
//    val sysId: Int,
//    val country: String,
//    val sunrise: Int,
//    val sunset: Int,
//    val timeZone: Int,
//    val id: Int,
//    val name: String,
//    val cod: Int
//){
//    data class Coord(val lat: Double, val lon: Double)
//    data class Weather(val id: Int, val main: String, val description: String, val icon: String)
//    data class Main(val temp: Double, val feels_like: Double, val temp_min: Double, val temp_max: Double,
//                    val pressure: Int, val humidity: Int, val sea_level: Int, val grd_level: Int)
//    data class Wind(val speed: Double, val deg: Int, val gust: Double)
//    data class Rain(val h: Double)
//    data class Clouds(val all: Int)
//    data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Int, val sunset: Int)
//
//
//    constructor(
//        coord: Coord,
//        weather: List<Weather>,
//        base: String,
//        main: Main,
//        visibility: Int,
//        wind: Wind,
//        rain: Rain,
//        clouds: Clouds,
//        dt: Int,
//        sys: Sys,
//        timeZone: Int,
//        id: Int,
//        name: String,
//        cod: Int
//    ) : this (
//        //Coord
//        lat = coord.lat,
//        lon = coord.lon,
//        //Weather
//        weatherId = weather[0].id,
//        weatherMain = weather[0].main,
//        description = weather[0].description,
//        icon = weather[0].icon,
//        base,
//        //Main
//        temp = main.temp - 273.15,
//        feelsLike = main.feels_like - 273.15,
//        tempMin = main.temp_min - 273.15,
//        tempMax = main.temp_max - 273.15,
//        pressure = main.pressure,
//        humidity = main.humidity,
//        seaLevel = main.sea_level,
//        grdLevel = main.grd_level,
//        visibility,
//        //Wind
//        speed = wind.speed,
//        deg = wind.deg,
//        gust = wind.gust,
//        //Rain
//        h = rain.h,
//        //Clouds
//        cloudsAll = clouds.all,
//        dt,
//        //Sys
//        type = sys.type,
//        sysId = sys.id,
//        country = sys.country,
//        sunrise = sys.sunrise,
//        sunset = sys.sunset,
//        timeZone,
//        id,
//        name,
//        cod
//        )
//}






//    companion object {
//        operator fun invoke(
//            coord: Coord,
//            weather: List<Weather>,
//            base: String,
//            main: Main,
//            visibility: Int,
//            wind: Wind,
//            rain: Rain,
//            clouds: Clouds,
//            dt: Int,
//            sys: Sys,
//            timeZone: Int,
//            id: Int,
//            name: String,
//            cod: Int
//        ): JsonWeather {
//            //Coord
//            val lat = coord.lat
//            val lon = coord.lon
//            //Weather
//            val weatherItem = weather[0]
//            val weatherId = weatherItem.id
//            val weatherMain = weatherItem.main
//            val description = weatherItem.description
//            val icon = weatherItem.icon
//            //Main
//            val temp = main.temp - 273.15
//            val feelsLike = main.feels_like - 273.15
//            val tempMin = main.temp_min - 273.15
//            val tempMax = main.temp_max - 273.15
//            val pressure = main.pressure
//            val humidity = main.humidity
//            val seaLevel = main.sea_level
//            val grdLevel = main.grd_level
//            //Wind
//            val speed = wind.speed
//            val deg = wind.deg
//            val gust = wind.gust
//            //Rain
//            val h = rain.h
//            //Clouds
//            val cloudsAll = clouds.all
//            //Sys
//            val type = sys.type
//            val sysId = sys.id
//            val country = sys.country
//            val sunrise = sys.sunrise
//            val sunset = sys.sunset
//
//
//            return JsonWeather(lat,
//                lon,
//                weatherId,
//                weatherMain,
//                description,
//                icon,
//                base,
//                temp,
//                feelsLike,
//                tempMin,
//                tempMax,
//                pressure,
//                humidity,
//                seaLevel,
//                grdLevel,
//                visibility,
//                speed,
//                deg,
//                gust,
//                h,
//                cloudsAll,
//                dt,
//                type,
//                sysId,
//                country,
//                sunrise,
//                sunset,
//                timeZone,
//                id,
//                name,
//                cod
//            )
//        }
//    }
//}

//data class JsonWeather(
//    val coord: Coord,
//    val weather: List<Weather>,
//    val base: String,
//    val main: Main,
//    val visibility: Int,
//    val wind: Wind,
//    val rain: Rain,
//    val clouds: Clouds,
//    val dt: Int,
//    val sys: Sys,
//    val timeZone: Int,
//    val id: Int,
//    val name: String,
//    val cod: Int
//){
//    data class Coord(val lat: Double, val lon: Double)
//    data class Weather(val id: Int, val main: String, val description: String, val icon: String)
//    data class Main(val temp: Double, val feels_like: Double, val temp_min: Double, val temp_max: Double,
//                    val pressure: Int, val humidity: Int, val sea_level: Int, val grd_level: Int)
//    data class Wind(val speed: Double, val deg: Int, val gust: Double)
//    data class Rain(val h: Double)
//    data class Clouds(val all: Int)
//    data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Int, val sunset: Int)
//
//    //Coord
//    var lat = coord.lat
//    var lon = coord.lon
//    //Weather
//    var weatherItem = weather[0]
//    var weatherId = weatherItem.id
//    var weatherMain = weatherItem.main
//    var description = weatherItem.description
//    var icon = weatherItem.icon
//    //Main
//    var temp = main.temp - 273.15
//    var feelsLike = main.feels_like - 273.15
//    var tempMin = main.temp_min - 273.15
//    var tempMax = main.temp_max - 273.15
//    var pressure = main.pressure
//    var humidity = main.humidity
//    var seaLevel = main.sea_level
//    var grdLevel = main.grd_level
//    //Wind
//    var h = rain.h
//    //Clouds
//    var cloudsAll = clouds.all
//    //Sys
//    var type = sys.type
//    var sysId = sys.id
//    var country = sys.country
//    var sunrise = sys.sunrise
//    var sunset = sys.sunset
//}