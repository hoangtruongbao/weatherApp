package com.example.weatherapplication.presentation.module

import android.app.Application
import com.example.weatherapplication.data.datasource.WeatherDataSource
import com.example.weatherapplication.data.datasource.WeatherDataSourceImpl
import com.example.weatherapplication.data.remote.WeatherApi
import com.example.weatherapplication.data.repository.WeatherRepositoryImpl
import com.example.weatherapplication.domain.base.ExceptionHandler
import com.example.weatherapplication.domain.repository.WeatherRepository
import com.example.weatherapplication.domain.usecase.WeatherUsecase
import com.example.weatherapplication.presentation.helper.ExceptionHandlerImp
import com.example.weatherapplication.presentation.helper.SharedPreferenceHelper
import com.example.weatherapplication.presentation.viewmodel.WeatherViewModel
import org.koin.android.BuildConfig

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModules = module {
    single<ExceptionHandler> { ExceptionHandlerImp() }
}

val repositoryModules = module {


    fun provideRepositoryWeather(weatherDataSource: WeatherDataSource): WeatherRepository {
        return WeatherRepositoryImpl(weatherDataSource)
    }

    single(named("remotedWeather")) {
        provideRepositoryWeather(get(named(MYID_TOKEN_API_DATA_SOURCE)))
    }

}

val usecaseModules = module {

    factory(named(WEATHER_USECASE)) {
        WeatherUsecase(get(named("remotedWeather")), get())
    }

}

val networkModule = module {

    single(named(API_WEATHER)) {
        createService<WeatherApi>(get(named(OK_HTTP_INSTANCE)), BASE_URL_MYID)
    }


    single(named(OK_HTTP_MYID)) {
        createMyidOkHttpClient()
    }


    single(named(API_WEATHER_USER_SERVICE)) {
        createService<WeatherApi>(get(named(OK_HTTP_MYID)), BASE_URL_MYID)
    }
}

val viewmodels = module {

    viewModel {
        WeatherViewModel(get(named(WEATHER_USECASE)), get())
    }

}

val dataSourceModule = module {


    fun provideWeatherDataSource(weatherApi: WeatherApi): WeatherDataSource {
        return WeatherDataSourceImpl(weatherApi)
    }


    single(named(MYID_TOKEN_API_DATA_SOURCE)) {
        provideWeatherDataSource(get(named(API_WEATHER_USER_SERVICE)))
    }

}

val prefModule = module {
    fun providePref(application: Application): SharedPreferenceHelper {
        return SharedPreferenceHelper(application)
    }
    single(named(PREF_HELPER)) {
        providePref(androidApplication())
    }
}

private const val API_WEATHER = "ApiWeather"
private const val API_WEATHER_USER_SERVICE = "ApiWeatherIdUserServiceWithInterceptor"

private const val OK_HTTP_INSTANCE = "OkHttp"
private const val OFINDO_USECASE = "ofindoUsecase"
private const val WEATHER_USECASE = "weatherUsecase"
private const val MYID_TOKEN_API_DATA_SOURCE = "getMyidTokenApiDataSource"
private const val GCP_API_DATA_SOURCE = "getGCPApiDataSource"
private const val PREF_HELPER = "prefHelper"

private const val BASE_URL = ""
private const val BASE_URL_MYID = "https://api.openweathermap.org/"
private const val BASE_GCP = ""
private const val OK_HTTP_OFINDO = "OkHttpOfindo"
private const val OK_HTTP_MYID = "OkHttpMyid"

private const val GCP_REPO = "remoteGCP"
