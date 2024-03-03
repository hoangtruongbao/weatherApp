package com.example.weatherapplication.presentation.viewmodel

import com.example.weatherapplication.domain.base.ExceptionHandler
import com.example.weatherapplication.domain.base.ParamUsecase
import com.example.weatherapplication.domain.usecase.WeatherUsecase
import com.example.weatherapplication.presentation.base.BaseViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.base.Error
import com.example.weatherapplication.domain.entity.WeatherObj
import com.example.weatherapplication.domain.entity.weather.WeatherResult
import com.example.weatherapplication.presentation.helper.SingleLiveEvent


@ObsoleteCoroutinesApi
class WeatherViewModel(private val weatherUsecase: WeatherUsecase, private val exceptionHandler: ExceptionHandler) : BaseViewModel(exceptionHandler) {
    override val receivedChannel: ReceiveChannel<Result<Any, Error>>
        get() = weatherUsecase.receiveChannel

    private val _getWeatherByCity = SingleLiveEvent<WeatherResult>()
    val getWeatherByCity = _getWeatherByCity

    private val _loading = SingleLiveEvent<Boolean>()
    val loading = _loading

    private val _errorMessage = SingleLiveEvent<Error>()
    val errorMessage = _errorMessage



    fun getWeatherByCity(cityName : String) {
        _loading.postValue(true)
        weatherUsecase(ParamUsecase(WeatherUsecase.GET_WEATHER_BY_CITY,WeatherObj(cityName)))
    }



    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFail, ::handleState)
    }

    private fun handleSuccess(data: Any) {
        _loading.postValue(false)
        when (data) {
            is WeatherResult -> {
                _getWeatherByCity.postValue(data)
            }


        }
    }

        private fun handleFail(err: Error) {
            _loading.postValue(false)
            _errorMessage.postValue(err)
        }

        private fun handleState(state: Result.State) {}

    }