package com.example.weatherapplication.presentation.module

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}


fun createMyidOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient
        .Builder()
        .connectTimeout(Network.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Network.READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Network.WRITE_TIMEOUT, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
    }


    return builder.build()
}





