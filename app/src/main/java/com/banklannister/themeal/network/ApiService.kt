package com.banklannister.themeal.network

import com.banklannister.themeal.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<CategoryResponse>
}