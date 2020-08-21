package com.example.assignmentproject.data.remote.api

import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.response.ApiResponse
import com.example.assignmentproject.data.remote.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainInterface {
    @GET("/v2/everything")
    fun getArticles(
        @Query("q") q: String = "bitcoin",
        @Query("from") from: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "7d5d1e3a090a4978bd50bea948760242"
    ):Single<ApiResponse<List<ArticlesItem>>>
}