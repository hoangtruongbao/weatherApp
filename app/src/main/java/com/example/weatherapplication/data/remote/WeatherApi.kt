package com.example.weatherapplication.data.remote

import WeatherResponse
import com.example.weatherapplication.data.base.APIHelper
import com.example.weatherapplication.domain.base.Error
import com.example.weatherapplication.domain.entity.weather.Weather
import com.example.weatherapplication.domain.entity.weather.WeatherResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather?appid=${APIHelper.APP_ID}")
    suspend fun getWeatherByCity(
        @Query("q") cityName : String
    ) : Response<WeatherResponse>
}