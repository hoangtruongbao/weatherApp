package com.example.weatherapplication.data.datasource

import com.example.weatherapplication.domain.base.Error
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.entity.weather.WeatherResult

interface WeatherDataSource {

    suspend fun getWeather(cityName : String) : Result<WeatherResult, Error>
}