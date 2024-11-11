package com.example.budget_buddy_android.api

import com.example.budget_buddy_android.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8005/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {
    @POST("auth/signup")
    suspend fun register(@Body user: User): Response<User>
}

