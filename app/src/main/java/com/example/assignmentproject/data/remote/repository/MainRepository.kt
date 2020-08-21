package com.example.assignmentproject.data.remote.repository

import com.example.assignmentproject.data.remote.api.MainInterface
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.response.ApiResponse
import com.example.assignmentproject.data.remote.response.NewsResponse
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainInterface: MainInterface){
    fun getArticles(): Single<ApiResponse<List<ArticlesItem>>> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        return mainInterface.getArticles(from = formatter.format(date))
    }
}