package com.example.assignmentproject.data.remote.repository

import androidx.lifecycle.LiveData
import com.example.assignmentproject.data.db.dao.NewsDao
import com.example.assignmentproject.data.remote.api.MainInterface
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.response.ApiResponse
import com.example.assignmentproject.data.remote.response.NewsResponse
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainInterface: MainInterface,private val newsDao: NewsDao){
    fun getArticles(): Single<ApiResponse<List<ArticlesItem>>> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        return mainInterface.getArticles(from = formatter.format(date))
    }

    fun getAllPostsFromLocal(): LiveData<List<ArticlesItem>> =
        newsDao.getAllItems()

    fun insert(items:List<ArticlesItem>){
        newsDao.insert(items)
    }

    fun clearNewsFromLocal() = newsDao.clear()

}