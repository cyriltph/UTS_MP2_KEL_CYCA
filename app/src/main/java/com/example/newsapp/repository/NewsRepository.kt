package com.example.newsapp.repository

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.network.RetrofitInstance
import retrofit2.Response

class NewsRepository {
    suspend fun getNews(query: String, from: String? = null, to: String? = null): Response<NewsResponse> {
        return RetrofitInstance.api.getNews(query, from, to)
    }
}
