package com.example.newsapp.network

import com.example.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "425fa1a49c244d1d840fbccd62bfde1c"
    ): Response<NewsResponse>
}
