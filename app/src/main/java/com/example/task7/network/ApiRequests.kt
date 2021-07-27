package com.example.task7.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {
    @GET("/posts/{id}")
    suspend fun getData(@Path(value = "id") id: Int): NetworkModel
}