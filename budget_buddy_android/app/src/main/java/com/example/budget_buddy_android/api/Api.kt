package com.example.budget_buddy_android.api

import com.example.budget_buddy_android.dto.ExpenseDto
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.models.LoginRequest
import com.example.budget_buddy_android.models.LoginResponse
import com.example.budget_buddy_android.models.LogoutResponse
import com.example.budget_buddy_android.models.RegisterRequest
import com.example.budget_buddy_android.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8005/"
    private var token: String? = null

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun setToken(newToken: String) {
        token = newToken
    }

    fun getToken(): String? {
        return token
    }
}

interface ApiService {
    @POST("auth/signup")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterRequest>

    @POST("/auth/login")
    suspend fun login(@Body login: LoginRequest): Response<LoginResponse>

    @POST("/auth/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<LogoutResponse>

    @GET("/users/")
    suspend fun allUsers(@Header("Authorization") token: String): Response<List<User>>

    @GET("/users/{id}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<User>

    @GET("/expense/all")
    suspend fun allExpenses(@Header("Authorization") token: String): Response<List<Expense>>

    @GET("/expense/{id}")
    suspend fun singleExpense(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Expense>

    @POST("/expense/add")
    suspend fun addExpense(
        @Header("Authorization") token: String,
        @Body expense: ExpenseDto
    ): Response<ExpenseDto>

    @PUT("/expense/{id}")
    suspend fun updateExpense(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body expense: ExpenseDto
    ): Response<ExpenseDto>

    @DELETE("/expense/{id}")
    suspend fun deleteExpense(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Unit>

}

