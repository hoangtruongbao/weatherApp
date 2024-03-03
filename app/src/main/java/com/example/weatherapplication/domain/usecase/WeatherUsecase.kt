package com.example.weatherapplication.domain.usecase

import com.example.weatherapplication.domain.base.BaseUseCase
import com.example.weatherapplication.domain.base.ExceptionHandler
import com.example.weatherapplication.domain.base.ParamUsecase
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.entity.WeatherObj
import com.example.weatherapplication.domain.repository.WeatherRepository


class WeatherUsecase(private val weatherRepository: WeatherRepository,
                     private val exceptionHandler: ExceptionHandler
) : BaseUseCase<ParamUsecase>(exceptionHandler) {
    private suspend fun getWeatherByCity(cityName : String) {
        resultChannel.send(Result.State.Loading)
        resultChannel.send(weatherRepository.getWeather(cityName))
        resultChannel.send(Result.State.Loaded)
    }



    companion object{
        const val GET_WEATHER_BY_CITY = 1
    }

    override suspend fun run(params: ParamUsecase) {
        if (params.type == GET_WEATHER_BY_CITY && params.data != null) {
            val data = params.data as WeatherObj
                getWeatherByCity(data.cityName)
        }

    }

}