package com.example.assignmentproject.ui.news

import com.example.assignmentproject.data.remote.model.ArticlesItem

interface MainNavigator {
    fun onClickItem(item:ArticlesItem)
}