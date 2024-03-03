package com.example.weatherapplication.domain.base

interface ExceptionHandler {
    fun onException(ex: Throwable)
}