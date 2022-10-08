package com.example.project

import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

object NetworkUtils {
    //api.openweathermap.org/data/2.5/weather?lat={LAT}&lon={LON}&appid=37f67a96d9d9b030b54b6ad18b69d26e
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather?lat="
    private const val LONQUERY = "&lon="
    private const val APPIDQUERY = "&appid="
    private const val app_id = "37f67a96d9d9b030b54b6ad18b69d26e"

//    //http://openweathermap.org/img/wn/10d@2x.png
//    private const val BASE_IMAGE_URL = "http://openweathermap.org/img/wn/"
//    private const val SUFFIX = "@2x.png"

    fun buildURLFromString(lat: Double, lon: Double): URL? {
        var myURL : URL? = null
        try {
            myURL = URL(BASE_URL + lat + LONQUERY + lon + APPIDQUERY + app_id)
        } catch (e: MalformedURLException){
            e.printStackTrace()
        }
        return myURL
    }

    fun getDataFromURL(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        return try {
            val inputStream = urlConnection.getInputStream()

            val scanner = Scanner(inputStream)
            scanner.useDelimiter("\\A")
            val hasInput = scanner.hasNext()
            if (hasInput){
                scanner.next()
            }
            else {
                null
            }
        }
        finally {
            urlConnection.disconnect()
        }

    }

}