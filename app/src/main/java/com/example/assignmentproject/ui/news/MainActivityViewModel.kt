package com.example.assignmentproject.ui.news

import com.example.assignmentproject.common.extension.request
import com.example.assignmentproject.common.widget.ListLiveData
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.repository.MainRepository
import com.example.assignmentproject.di.scope.ActivityScope
import com.example.assignmentproject.ui.base.viewmodel.BaseActivityViewModel
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository): BaseActivityViewModel() {
    val articlesLiveData = ListLiveData<ArticlesItem>()
    override fun handleCreate() {
        super.handleCreate()
        getArticles()
    }

    private fun getArticles(){
        mainRepository.getArticles()
            .request({

            },{

            },{
               articlesLiveData.addAll(it)
            },{
               Timber.e(it)
            })
    }

}