package com.example.weatherapplication.domain.repository

import com.example.weatherapplication.domain.base.Error
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.entity.weather.WeatherResult

interface WeatherRepository {

    suspend fun getWeather(
        cityName : String
    ) : Result<WeatherResult,Error>
}