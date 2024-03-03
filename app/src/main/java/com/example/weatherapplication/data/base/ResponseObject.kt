package com.example.weatherapplication.data.base

interface ResponseObject <out DomainObject : Any?> {
    fun toDomain () : DomainObject?
}