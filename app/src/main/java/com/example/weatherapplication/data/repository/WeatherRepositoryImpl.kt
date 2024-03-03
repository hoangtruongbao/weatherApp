package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.datasource.WeatherDataSource
import com.example.weatherapplication.domain.base.Error
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.entity.weather.WeatherResult
import com.example.weatherapplication.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val apiSource: WeatherDataSource) : WeatherRepository {
    override suspend fun getWeather(cityName : String): Result<WeatherResult, Error> {
        return apiSource.getWeather(cityName)
    }
}