package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchNews(query: String, from: String? = null, to: String? = null) {
        viewModelScope.launch {
            try {
                val response = repository.getNews(query, from, to)
                if (response.isSuccessful) {
                    _articles.postValue(response.body()?.articles ?: emptyList())
                } else {
                    _error.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _error.postValue("Exception: ${e.message}")
            }
        }
    }
}
