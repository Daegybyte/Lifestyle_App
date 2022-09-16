package com.example.project

data class WeatherData(val weather: List<Weather>, val sys: List<Sys>, val name: Name){
    data class Weather(val main: String, val description: String, val icon: String)
    data class Sys(val country: String)
    data class Name(val name: String)
}
