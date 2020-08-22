package com.example.assignmentproject.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.assignmentproject.data.remote.model.ArticlesItem

@Dao
interface NewsDao: BaseDao<ArticlesItem>  {
    @Query("SELECT * FROM ArticlesItem")
    fun getAllItems(): LiveData<List<ArticlesItem>>
}