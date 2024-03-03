package com.example.weatherapplication.data.datasource

import com.example.weatherapplication.data.base.APIHelper
import com.example.weatherapplication.data.remote.WeatherApi
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.base.*
import com.example.weatherapplication.domain.entity.weather.WeatherResult
import java.lang.Exception

class WeatherDataSourceImpl(private val weatherApi: WeatherApi) :  WeatherDataSource {
    override suspend fun getWeather(cityName : String): Result<WeatherResult, Error> {
        return try {
            val response = weatherApi.getWeatherByCity(cityName)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!.toDomain())
            } else {
                Result.Failuare(APIHelper.toResponseError(response))
            }
        } catch (err : Exception) {
            Result.Failuare(APIHelper.toResponseError(err))
        }
    }
}